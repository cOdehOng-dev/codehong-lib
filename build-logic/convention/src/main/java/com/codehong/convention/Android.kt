package com.codehong.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = libs.getVersion("projectCompileSdkVersion")

        defaultConfig {
            minSdk = libs.getVersion("projectMinSdkVersion")
        }

        buildFeatures {
            buildConfig = true
            viewBinding = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
) {

    commonExtension.run {

        when (extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
//                        debug {
//                            configureDebugBuildType(apiKey)
//                        }
//                        create("staging") {
//                            configureStagingBuildType(apiKey)
//                        }
                        release {
                            isMinifyEnabled = true
                            proguardFiles(
                                commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
//                        debug {
//                            configureDebugBuildType(apiKey)
//                        }
//                        create("staging") {
//                            configureStagingBuildType(apiKey)
//                        }
                        release {
                            isMinifyEnabled = true
                            proguardFiles(
                                commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }
                }
            }
        }
    }
}

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        buildFeatures {
            compose = true
        }

        composeOptions {
//            kotlinCompilerExtensionVersion = libs.findVersion("composeCompiler").get().toString()
            kotlinCompilerExtensionVersion = "1.5.10"
        }

        dependencies {
            val bom = libs.findLibrary("androidx.compose.bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
        }
    }
}