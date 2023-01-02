@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("toymarvel.android.library")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.converter)
    implementation(libs.serialization.json)
}