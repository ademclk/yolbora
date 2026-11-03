plugins {
    `java-library`
}

dependencies {
    testImplementation(libs.archunit)

    // The architecture test must see every product module on its classpath.
    testImplementation(project(":lib:core"))
    testImplementation(project(":lib:spatial"))
    testImplementation(project(":lib:dispatch"))
    testImplementation(project(":lib:planner"))
    testImplementation(project(":lib:mission"))
    testImplementation(project(":sim"))
    testImplementation(project(":lib:ingest"))
    testImplementation(project(":api"))
}
