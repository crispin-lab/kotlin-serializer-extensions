import org.jmailen.gradle.kotlinter.tasks.InstallPreCommitHookTask
import org.jmailen.gradle.kotlinter.tasks.InstallPrePushHookTask

val libraryVersion = "0.0.1"

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    id("org.jmailen.kotlinter") version "5.0.1"
    id("jacoco")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

group = "com.crispin-lab"
version = libraryVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.27.3")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = false
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

kotlin {
    jvmToolchain(21)
}

if (!rootProject.extra.has("install-git-hooks")) {
    rootProject.extra["install-git-hooks"] = true

    val preCommit: InstallPreCommitHookTask by project.rootProject.tasks.creating(
        InstallPreCommitHookTask::class
    ) {
        group = "build setup"
        description = "Installs Kotlinter Git pre-commit hook"
    }

    val prePush: InstallPrePushHookTask by project.rootProject.tasks.creating(
        InstallPrePushHookTask::class
    ) {
        group = "build setup"
        description = "Installs Kotlinter Git pre-push hook"
    }

    project.rootProject.tasks.getByName("prepareKotlinBuildScriptModel") {
        dependsOn(preCommit, prePush)
    }
}
