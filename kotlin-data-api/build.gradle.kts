plugins {
}

dependencies {
    api("com.bjknrt:framework-webapi") {
        isChanging = true
    }
    // web
    implementation("org.springframework:spring-webmvc")
    implementation("org.apache.tomcat.embed:tomcat-embed-core")
}