import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.20-RC2"
    kotlin("plugin.spring") version "1.6.20-RC2"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.liquibase:liquibase-core:4.8.0")
    implementation("org.postgresql:postgresql:42.3.3")
    runtimeOnly("mysql:mysql-connector-java:8.0.28")
    implementation(kotlin("stdlib-jdk8"))
    implementation("javax.validation:validation-api")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

group = "com.rustem"
version = "0.0.1-SNAPSHOT"
description = "SimpleCRUDApplication"
java.sourceCompatibility = JavaVersion.VERSION_11

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}
tasks.withType<Test> {
    useJUnitPlatform()
}