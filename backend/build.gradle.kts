
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    alias(libs.plugins.jib)
}

group = "com.telpral"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(21)
}
dependencies {
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(libs.logback.classic)

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}

jib {
    from {
        image = "eclipse-temurin:21-jre"
    }

    to {
        val containerImageName = "netobserver-backend"
        val containerRegistry = "ghcr.io/telpral"
        val targetImage = System.getProperty("imageTag") ?: "$containerImageName:local"
        image = "$containerRegistry/$targetImage"
    }

    container {
        mainClass = "io.ktor.server.netty.EngineMain"

        ports = listOf("8080")

        jvmFlags = listOf(
            "-XX:MaxRAMPercentage=75.0"
        )
    }
}
