plugins {
    java
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("com.google.code.gson:gson:2.10")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

group = "de.clientdns"
version = "1.0"
description = "The plugin for Super Smash Bros but in Minecraft."

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
