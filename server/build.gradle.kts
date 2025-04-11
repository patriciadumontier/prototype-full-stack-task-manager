plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinxRpc)
    application
}

group = "org.example.ktor"
version = "1.0.0"
application {
    mainClass.set("org.example.ktor.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor")
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.javatime)
    implementation(libs.hikaricp)
    implementation(libs.kotlinx.rpc.krpc.client)
    implementation(libs.kotlinx.rpc.krpc.ktor.client)
    implementation(libs.kotlinx.rpc.krpc.server)
    implementation(libs.kotlinx.rpc.krpc.serialization.json)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.logback)
    implementation(libs.postgresql)
    implementation(projects.shared)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(kotlin("test"))
}