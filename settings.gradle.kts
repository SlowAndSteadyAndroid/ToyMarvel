pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ToyMarvel"
include(":app")
include(":core-ui")
include(":core-navigation")
include(":core-common")
include(":core-data")
include(":core-database")
include(":core-network")
include(":core-model")
include(":feature-marvel")
