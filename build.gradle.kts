import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

println("Starting build.gradle.kts processing...")

//val logback_version: String by project
//val ktor_version: String by project
//val kotlin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.3.30"
    id("java")
}

//group = "gdps.com.kraken"
//version = "0.0.1-SNAPSHOT"

application {
    mainClassName = "ch.vorburger.openshift.s2i.example.Server"
}
/*
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}*/

repositories {
    jcenter()
}

dependencies {
    //compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    //compile("io.ktor:ktor-server-jetty:$ktor_version")
    //compile("ch.qos.logback:logback-classic:$logback_version")
    //compile("io.ktor:ktor-server-core:$ktor_version")
    //compile("io.ktor:ktor-locations:$ktor_version")

    testCompile("junit:junit:4.12")
}
/*
configurations.compile.forEach { println("${it.name}") }

tasks {
    withType<Jar> {
        manifest {
            attributes(mapOf("Main-Class" to application.mainClassName))
            attributes(mapOf("Class-Path" to (configurations.compile.map { it.name }).joinToString(" ")))
        }
    }
}*/

