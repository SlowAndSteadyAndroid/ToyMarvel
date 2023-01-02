plugins {
    id("toymarvel.android.library")
    id("toymarvel.android.library.compose")
    kotlin("kapt")
    alias(libs.plugins.ksp)
}

dependencies {

    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-common"))
    implementation(project(":core-data"))
    implementation(project(":core-database"))
    implementation(project(":core-model"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.landscapist.coil)

    implementation("com.google.accompanist:accompanist-pager:0.13.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.13.0")
}