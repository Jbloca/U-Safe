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

}
