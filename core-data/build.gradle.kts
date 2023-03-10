plugins {
    id("toymarvel.android.library")
    kotlin("kapt")
}

dependencies {
    implementation(project(":core-network"))
    implementation(project(":core-database"))
    implementation(project(":core-model"))
    implementation(project(":core-common"))

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
