rootProject.name = "job-ads-service"

pluginManagement {
    val kotlinVersion: String by settings
    val kotestVersion: String by settings
    val slf4jVersion: String by settings
    val kotlinSerializationVersion: String by settings
    val ktorVersion: String by settings
    val config4kVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
    }

    dependencyResolutionManagement {
        versionCatalogs {
            create("libs") {
                version("kotest", kotestVersion)
                version("slf4j", slf4jVersion)
                version("kotlinx", kotlinSerializationVersion)
                version("ktor", ktorVersion)
                version("config4k", config4kVersion)

                library("kotest-runner-junit5", "io.kotest", "kotest-runner-junit5").versionRef("kotest")
                library("kotest-assertions-core", "io.kotest", "kotest-assertions-core").versionRef("kotest")
                bundle("test", listOf("kotest-runner-junit5", "kotest-assertions-core"))

                library("slf4j-simple", "org.slf4j", "slf4j-simple").versionRef("slf4j")
                bundle("logging", listOf("slf4j-simple"))

                library("kotlinx-serialization-json", "org.jetbrains.kotlinx", "kotlinx-serialization-json").versionRef("kotlinx")
                bundle("json", listOf("kotlinx-serialization-json"))

                library("ktor-client-core", "io.ktor", "ktor-client-core").versionRef("ktor")
                library("ktor-serialization-kotlinx-json-jvm", "io.ktor", "ktor-serialization-kotlinx-json-jvm").versionRef("ktor")
                library("ktor-client-json", "io.ktor", "ktor-client-json").versionRef("ktor")
                library("ktor-client-cio", "io.ktor", "ktor-client-cio").versionRef("ktor")
                library("ktor-client-content-negotiation", "io.ktor", "ktor-client-content-negotiation").versionRef("ktor")
                bundle("ktorClient", listOf("ktor-client-core", "ktor-serialization-kotlinx-json-jvm", "ktor-client-json", "ktor-client-cio", "ktor-client-content-negotiation"))

                library("config4k", "io.github.config4k", "config4k").versionRef("config4k")
                bundle("config", listOf("config4k"))

            }
        }
    }

}


