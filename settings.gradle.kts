// gradle 插件修改
pluginManagement {
    val sqlexVer: String by settings
    val kotlinPluginVer: String by settings
    val springDependencyManagementVer: String by settings
    val jibVer: String by settings
    val springBootVer: String by settings

    // 统一插件版本管理
    plugins {
        id("com.google.cloud.tools.jib") version jibVer
        id("org.springframework.boot") version springBootVer
        id("io.spring.dependency-management") version springDependencyManagementVer

        kotlin("jvm") version kotlinPluginVer
        kotlin("kapt") version kotlinPluginVer
        kotlin("plugin.spring") version kotlinPluginVer
        kotlin("plugin.jpa") version kotlinPluginVer

        id("me.danwi.sqlex") version sqlexVer
    }

    repositories {
        fun getMavenArtifactRepo(
            url: String,
            repoName: String = "DEFAULT_REPO_NAME",
            _username: String = "bjknrt",
            _password: String = "bjknrt"
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
        maven(getMavenArtifactRepo("https://repo.gate.bjknrt.com/repository/maven-public/"))
    }
}

rootProject.name = "kotlin-data"
include("kotlin-data-api")
include("kotlin-data-server")
