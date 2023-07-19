plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("kotlin-kapt")


}

android {
    namespace = "com.mbah1.jephthah.fieldservicereporter"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mbah1.jephthah.fieldservicereporter"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {

        resources {
            excludes += "/META-INF/**/*"

        }
    }
    kotlin {
        jvmToolchain(8)
    }

}







dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.accompansit.pager)
    implementation(libs.joda)

    implementation(libs.hilt.ext.compiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.kotlinx.coroutines.android)

    // Splash API
    implementation(libs.androidx.core.splashscreen)

    coreLibraryDesugaring(libs.android.desugarJdkLibs)

    // Compose Window Size Class
    implementation(libs.androidx.compose.material3.windowSizeClass)

    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.compose.material.iconsExtended)


    // Sheets Compose Dialogs for time picker
    implementation ("com.maxkeppeler.sheets-compose-dialogs:core:1.0.2")
    // CALENDAR
    implementation ("com.maxkeppeler.sheets-compose-dialogs:calendar:1.0.2")

    // CLOCK
    implementation ("com.maxkeppeler.sheets-compose-dialogs:clock:1.0.2")

    implementation ("com.airbnb.android:lottie-compose:5.2.0")



}

