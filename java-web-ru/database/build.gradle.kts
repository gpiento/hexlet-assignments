import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.github.ben-manes.versions") version "0.51.0"
    application
    id("io.freefair.lombok") version "8.6"
}

application {
    mainClass.set("exercise.App")
}

group = "exercise"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // BEGIN
    implementation("com.h2database:h2:2.2.224")
    implementation("com.zaxxer:HikariCP:5.1.0")
    // END
    implementation("io.javalin:javalin:6.1.4")
    implementation("gg.jte:jte:3.1.9")
    implementation("io.javalin:javalin-rendering:6.1.3")
    implementation("io.javalin:javalin-bundle:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.13")
    implementation("net.datafaker:datafaker:2.2.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

    testImplementation(platform("org.junit:junit-bom:5.11.0-M1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

tasks.test {
    useJUnitPlatform()
    // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}
