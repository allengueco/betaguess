/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    api(libs.org.springframework.boot.spring.boot.starter.actuator)
    api(libs.org.springframework.boot.spring.boot.starter.web)
    api(libs.org.springframework.boot.spring.boot.configuration.processor)
    api(libs.org.eclipse.collections.eclipse.collections)
    api(libs.org.eclipse.collections.eclipse.collections.api)
    api(libs.org.springframework.boot.spring.boot.starter.data.redis)
    api(libs.org.springframework.session.spring.session.data.redis)
    api(libs.com.fasterxml.jackson.datatype.jackson.datatype.eclipse.collections)
    testImplementation(libs.org.springframework.boot.spring.boot.starter.test)
    testImplementation(libs.org.testcontainers.junit.jupiter)
    testImplementation(libs.org.springframework.boot.spring.boot.testcontainers)
    testImplementation(libs.com.redis.testcontainers.testcontainers.redis.junit.jupiter)
}

description = "backend"
