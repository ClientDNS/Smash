plugins {
    java
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public")
    maven("https://eldonexus.de/repository/maven-public") // sadu
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("com.google.code.gson:gson:2.10.1")
    //implementation("de.chojo.sadu:sadu:1.2.0")
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
