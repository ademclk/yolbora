plugins {
    base
}

tasks.register("buildWeb") {
    group = "build"
    description = "TODO: Placeholder web build replace with Vite build."
    doLast {
        logger.lifecycle("web/: placeholder build")
    }
}

tasks.named("build") {
    dependsOn("buildWeb")
}