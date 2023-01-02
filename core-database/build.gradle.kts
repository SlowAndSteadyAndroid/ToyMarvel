@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("toymarvel.android.library")
    kotlin("kapt")
    alias(libs.plugins.ksp)
}

dependencies {

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}
