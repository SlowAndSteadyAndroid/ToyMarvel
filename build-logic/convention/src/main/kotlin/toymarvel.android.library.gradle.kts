import com.example.toymarvel.configureKotlinAndroid

plugins{
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
}

android {

    configureKotlinAndroid(this)

    defaultConfig {
        targetSdk = 32
    }

}