plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
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
    implementation(projects.shared)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback)
    implementation(libs.kotlinx.rpc.krpc.server)

    implementation(libs.ktor.client.cio.jvm)
    implementation(libs.kotlinx.rpc.krpc.client.v051)
    implementation(libs.kotlinx.rpc.krpc.ktor.client.v051)
    implementation(libs.ktor.server.netty.jvm)
    implementation(libs.kotlinx.rpc.krpc.server.v051)
    implementation(libs.kotlinx.rpc.krpc.ktor.server)
    implementation(libs.kotlinx.rpc.krpc.serialization.json.v051)
    implementation(libs.logback.classic.v156)


    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}