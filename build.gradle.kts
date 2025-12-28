// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // --- [ ANDROID & KOTLIN CORE ] ---
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // --- [ UI & COMPOSE ] ---
    alias(libs.plugins.kotlin.compose) apply false

    // --- [ DATA & UTILITIES ] ---
    alias(libs.plugins.kotlin.parcelize) apply false

    // --- [ DEPENDENCY INJECTION ] ---
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.hilt.android) apply false
}