buildscript {
    repositories {
        google()  // Asegúrate de tener este repositorio
        mavenCentral()
    }
    dependencies {
        classpath(libs.google.services)  // Usa la última versión disponible
    }
}

allprojects {
    repositories {
        google()  // Asegúrate de tener este repositorio también
        mavenCentral()
    }
}

