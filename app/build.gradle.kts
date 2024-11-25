plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") version "4.4.2" // Apply Google Services plugin
}

android {
    namespace = "com.example.poofinal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.poofinal"
        minSdk = 32
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.material.v180)
    implementation(libs.firebase.firestore)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.recyclerview)
    implementation(libs.transportation.consumer)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase and related dependencies
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.google.firebase.database)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)
    implementation(libs.osmdroid.android)
    implementation (libs.github.glide)
    annotationProcessor (libs.compiler)
    implementation (libs.recyclerview.v131)
    implementation (libs.play.services.maps)
}
