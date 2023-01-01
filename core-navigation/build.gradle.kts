plugins {
    id("toymarvel.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}


dependencies {
    api(libs.androidx.hilt.navigation.compose)
    api(libs.androidx.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}