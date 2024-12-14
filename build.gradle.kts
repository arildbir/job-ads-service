val kotlinSerializationVersion: String by project
val slf4jVersion: String by project
val jvmTargetVersion: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.bundles.test)
    implementation(libs.bundles.logging)
    implementation(libs.bundles.json)
    implementation(libs.bundles.ktorClient)
    implementation(libs.bundles.config)

}

application {
    mainClass.set("application.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(jvmTargetVersion.toInt())
}