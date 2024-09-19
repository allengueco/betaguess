import com.github.gradle.node.npm.task.NpxTask

/*
 * This file was generated by the Gradle 'init' task.
 */

description = "frontend"

plugins {
    java
    alias(libs.plugins.node.gradle)
}

val buildAngular = tasks.register<NpxTask>("buildAngular") {
    dependsOn(tasks.npmInstall)
    command.set("ng")
    args.set(listOf("build"))
    inputs.dir(project.fileTree("src").exclude("**/*.spec.ts"))
    inputs.dir("node_modules")
    inputs.files("angular.json", ".browserslistrc", "tsconfig.json", "tsconfig.app.json")
    outputs.dir("build/browser")
}

tasks.named<Jar>("jar") {
    dependsOn(buildAngular)
    from("build/browser")
    into("static")
}