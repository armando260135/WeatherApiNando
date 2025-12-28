plugins {
    // --- [ ANDROID & KOTLIN CORE ] ---
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // --- [ UI & COMPOSE ] ---
    alias(libs.plugins.kotlin.compose)

    // --- [ DATA & SERIALIZATION ] ---
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)

    // --- [ DEPENDENCY INJECTION ] ---
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    id("app.cash.paparazzi")
}

android {
    namespace = "com.example.weatherapinando"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.weatherapinando"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "WEATHER_API_KEY", "\"de5553176da64306b86153651221606\"")
        buildConfigField("String", "BASE_URL", "\"https://api.weatherapi.com/v1/\"")

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
        viewBinding = true
        buildConfig = true
    }
    kapt {
        useBuildCache = true
        correctErrorTypes = true
        arguments {
            arg("dagger.fastInit", "enabled")
            arg("dagger.formatGeneratedSource", "disabled")
        }
        javacOptions {
            option("--add-opens", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
            option("--add-opens", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED")
            option("--add-opens", "jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED")
            option("--add-opens", "jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED")
            option("--add-opens", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED")
            option("--add-opens", "jdk.compiler/com.sun.tools.javac.jvm=ALL-UNNAMED")
            option("--add-opens", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED")
            option("--add-opens", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED")
        }
    }
}

dependencies {

    // --- [ CORE & ANDROIDX ] ---
    // Essential Kotlin extensions, Lifecycle management, and Splash Screen support
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.core.splashscreen)

    // --- [ JETPACK COMPOSE ] ---
    // Core declarative UI libraries and Material Design 3 components
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Compose integration with Lifecycle, ViewModels, and LiveData observers
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // --- [ NAVIGATION ] ---
    // Type-safe navigation management and JSON serialization
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // --- [ DEPENDENCY INJECTION (HILT) ] ---
    // Dagger Hilt for dependency injection and Hilt-Navigation integration
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // --- [ NETWORK & API (RETROFIT) ] ---
    // REST API consumption, logging, and data parsing (GSON)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)
    implementation(libs.gson)

    // --- [ MEDIA & ANIMATIONS ] ---
    // Image loading from URL (Coil) and vector animations (Lottie)
    implementation(libs.coil.compose)
    implementation(libs.lottie)

    // --- [ TESTING - UNIT ] ---
    // Local unit tests for Business Logic, Use Cases, and ViewModels
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.arch.core.testing)

    // --- [ TESTING - INSTRUMENTATION ] ---
    // UI and Integration tests on physical devices or emulators
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // --- [ DEBUG TOOLS ] ---
    // UI Inspection tools and test manifest for debugging
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
