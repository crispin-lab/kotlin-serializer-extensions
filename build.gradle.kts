val libraryVersion = "0.0.2"

plugins {
    kotlin("jvm") version "2.1.0"
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
