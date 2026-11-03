plugins {
    `java-library`
}

dependencies {
    testImplementation(libs.archunit)

    // The architecture test must see every product module on its classpath.
    testImplementation(project(":core"))
    testImplementation(project(":spatial"))
    testImplementation(project(":dispatch"))
    testImplementation(project(":planner"))
    testImplementation(project(":mission"))
    testImplementation(project(":sim"))
    testImplementation(project(":ingest"))
    testImplementation(project(":api"))
}
