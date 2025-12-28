plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // TODO agregar comentario
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // TODO agregar comentario
    implementation(libs.lottie)

    // TODO agregar comentario
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.splashscreen)

    // TODO agregar comentario
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)


    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.coil.compose)

    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}