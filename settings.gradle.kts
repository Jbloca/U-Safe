pluginManagement {
    repositories {
        gradlePluginPortal()
        google()// Asegúrate de que este esté aquí
        mavenCentral()
    }
}

dependencyResolutionManagement {
    this.repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()      // Este repositorio se gestiona desde aquí
        mavenCentral() // Maven Central
    }
}

rootProject.name = "PooFinal"
include(":app")
