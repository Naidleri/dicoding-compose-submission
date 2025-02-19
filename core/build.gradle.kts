plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ned.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        buildConfigField("String", "BASE_URL", "\"https://api.disneyapi.dev/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.leakcanary.android.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.androidx.paging.runtime.ktx)
    implementation (libs.retrofit2.retrofit)
    implementation(libs.logging.interceptor)
    implementation (libs.converter.gson)
    implementation (libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation (libs.androidx.navigation.compose)

    implementation (libs.android.database.sqlcipher)
    implementation (libs.androidx.sqlite)
    //implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}