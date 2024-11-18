// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    // Agrega aquí el plugin de Google Services, si es necesario, más abajo
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Aquí agregamos las dependencias necesarias en formato Kotlin DSL
    classpath(libs.google.services)  // Esto es para Google Services Plugin
}
