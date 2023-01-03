package com.example.toymarvel

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        compileSdk = 33

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=kotlin.Experimental",
                // Enable experimental kotlinx serialization APIs
                "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )


            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
    }
}



private fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}