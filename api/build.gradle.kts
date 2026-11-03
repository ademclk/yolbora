plugins {
    `java-library`
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(platform(libs.spring.boot.bom))

    implementation(project(":mission"))
    implementation(project(":sim"))
    implementation(project(":ingest"))

    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Testcontainers smoke test lives in its own source set and is not part of `build`,
// so ordinary local builds do not require Docker.
val smokeTest = sourceSets.create("smokeTest") {
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
}

configurations[smokeTest.implementationConfigurationName]
    .extendsFrom(configurations.testImplementation.get())
configurations[smokeTest.runtimeOnlyConfigurationName]
    .extendsFrom(configurations.testRuntimeOnly.get())

dependencies {
    "smokeTestImplementation"(platform(libs.testcontainers.bom))
    "smokeTestImplementation"("org.testcontainers:testcontainers-junit-jupiter")
    "smokeTestImplementation"("org.testcontainers:testcontainers-postgresql")
    "smokeTestImplementation"("org.testcontainers:testcontainers-kafka")
    "smokeTestImplementation"("org.apache.kafka:kafka-clients")
    "smokeTestRuntimeOnly"("org.postgresql:postgresql")
}

tasks.register<Test>("smokeTest") {
    description = "Runs the Testcontainers infrastructure smoke test (requires Docker)."
    group = "verification"
    testClassesDirs = smokeTest.output.classesDirs
    classpath = smokeTest.runtimeClasspath
    useJUnitPlatform()
    shouldRunAfter(tasks.test)
}