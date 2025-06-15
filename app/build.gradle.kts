plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.shops"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.shops"
        minSdk = 33
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.compose.bom))        // androidx.compose:compose-bom:2025.06.00 :contentReference[oaicite:0]{index=0}

    // Core UI
    implementation(libs.compose.ui)                   // androidx.compose.ui:ui
    implementation(libs.compose.material3)           // androidx.compose.material3:material3
    implementation(libs.compose.ui.tooling.preview)  // design-time previews

    // Activity ↔️ Compose bridge (gives you ComponentActivity.setContent)
    implementation(libs.activity.compose)             // androidx.activity:activity-compose:1.9.0 :contentReference[oaicite:1]{index=1}

    // Optional but handy while coding
    debugImplementation(libs.compose.ui.tooling)      // layout inspector, etc.

    implementation(project(":feature-shops-list"))
    implementation(project(":feature-shop-detail"))
    implementation(project(":core-data"))
    implementation(libs.hilt.android.lib)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}