plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.ideacollector"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ideacollector"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    //Dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    //Room
    kapt(libs.androidx.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    //Preference
    implementation(libs.androidx.preference.ktx)

    implementation(project(":modules:core:core_factory"))
    implementation(project(":modules:base:ui_kit"))
    implementation(project(":modules:features:home_screen:home_screen_api"))
    implementation(project(":modules:features:home_screen:home_screen_impl"))
    implementation(project(":modules:features:settings:settings_api"))
    implementation(project(":modules:features:settings:settings_impl"))
}