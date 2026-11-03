pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "ideate"

include(
    "lib:core",
    "lib:spatial",
    "lib:dispatch",
    "lib:planner",
    "lib:mission",
    "lib:ingest",
    "sim",
    "api",
    "web",
    "arch",
)