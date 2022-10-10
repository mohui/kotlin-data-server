import com.google.cloud.tools.jib.gradle.JibExtension
import com.google.cloud.tools.jib.gradle.JibPlugin
import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

plugins {
    kotlin("jvm")
    kotlin("kapt") apply false
    kotlin("plugin.jpa") apply false
    kotlin("plugin.spring") apply false
    id("io.spring.dependency-management") apply false
    id("com.google.cloud.tools.jib") apply false
}

allprojects {
    fun getMavenArtifactRepo(
        url: String, repoName: String = "DEFAULT_REPO_NAME", _username: String = "bjknrt", _password: String = "bjknrt"
    ): (MavenArtifactRepository).() -> Unit {
        return {
            name = repoName
            isAllowInsecureProtocol = true
            setUrl(url)
            credentials {
                username = _username
                password = _password
            }
        }
    }

    apply<JavaPlugin>()
    apply<JavaLibraryPlugin>()
    apply<MavenPublishPlugin>()
    apply<DependencyManagementPlugin>()
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    group = "" + group + (if (project.parent == null) "" else ".${(project.parent as Project).name}")
    version = "" + version + (if (System.getenv("MAVEN_RELEASE") == null) "-SNAPSHOT" else "")
    val isSnapshot = version.toString().endsWith("SNAPSHOT")
    val ciUsername: String = System.getenv("NEXUS_CI_UN") ?: ""
    val ciPassword: String = System.getenv("NEXUS_CI_PD") ?: ""

    java.sourceCompatibility = JavaVersion.VERSION_1_8
    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
        all {
            resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.MINUTES)
            resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.HOURS)
        }
    }

    repositories {
        // mavenLocal()
        maven(getMavenArtifactRepo("https://repo.gate.bjknrt.com/repository/maven-public/"))
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        inputs.files(tasks.named("processResources"))
        options.compilerArgs.add("-parameters")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            javaParameters = true
            jvmTarget = "1.8"
        }
        inputs.files(tasks.named("processResources"))
    }

    tasks.withType<Copy> {
        filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to mapOf("projectName" to project.name))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        // kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    }

    configure<org.jetbrains.kotlin.gradle.plugin.KaptExtension> {
        showProcessorStats = true
        keepJavacAnnotationProcessors = true
    }

    configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
        imports {
            mavenBom("com.bjknrt:cloud-dependencies:${project.property("bjknrtCloudDependenciesVer")}")
        }
    }

    configure<JavaPluginExtension> {
        withSourcesJar()
    }

    configure<PublishingExtension> {
        repositories {
            maven {
                val repoUrl =
                    "http://repo.gate.bjknrt.com/repository/${if (isSnapshot) "maven-snapshot" else "maven-release"}/"
                // 读取环境变量的值 ./gradlew publishAllPublicationsToBjknrtRepository
                if (ciUsername != "" && ciPassword != "") {
                    maven(
                        getMavenArtifactRepo(
                            repoUrl, "bjknrt", ciUsername, ciPassword
                        )
                    )
                }
            }
        }
        publications {
            create<MavenPublication>("java") {
                from(components["java"])
                pom {
                    name.set(project.name)
                    description.set("${project.name} component")
                }
            }
        }
    }

    // docker打包配置
    if (project.parent != null) {
        apply<JibPlugin>()
        configure<JibExtension> {
            setAllowInsecureRegistries(true)
            from {
                image = "192.168.3.201:8082/openjdk:8-jdk"
                // image = "docker://192.168.3.201:8082/openjdk:8-jdk"
                auth {
                    username = ciUsername
                    password = ciPassword
                }
            }
            to {
                image = "192.168.3.201:8082/${project.rootProject.name}/${project.name}"
                auth {
                    username = ciUsername
                    password = ciPassword
                }
            }
            container {
//                user = "bjknrt:bjknrt"
                appRoot = "/bjknrt"
                workingDirectory = "/bjknrt"
                expandClasspathDependencies = true
                creationTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                filesModificationTime = creationTime
                jvmFlags = mutableListOf(
//                    "-Xmx512m",  jdk 会根据容器环境自动配置
//                    "-Xms512m",
//                    "-XX:+HeapDumpBeforeFullGC",
//                    "-XX:+HeapDumpAfterFullGC",
                    "-XX:+HeapDumpOnOutOfMemoryError",
                    "-XX:HeapDumpPath=./",
                    "-Dspring.backgroundpreinitializer.ignore=true", // spring优化线程
                    "-Duser.timezone=Asia/Shanghai",
                    "-Dproject.name=${project.name}"
                )
                ports = mutableListOf("8080")
                format = com.google.cloud.tools.jib.api.buildplan.ImageFormat.Docker
            }
        }
    }
}