package com.codehong.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    isCompose: Boolean,
) {
    commonExtension.apply {
        compileSdk = libs.getVersion("compileSdk")

        defaultConfig {
            minSdk = libs.getVersion("minSdk")
        }

        buildFeatures {
            buildConfig = true
            viewBinding = true
            dataBinding {
                isEnabled = true
            }
            compose = isCompose
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        if (isCompose) {
            composeOptions {
                kotlinCompilerExtensionVersion = "1.5.10"
            }
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

        lint {
            checkReleaseBuilds = false
            abortOnError = false
            ignoreWarnings = true
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }

    tasks.matching { it.name.contains("lint", ignoreCase = true) }.configureEach {
        enabled = false
    }

    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinExtension.jvmToolchain(17)

    dependencies {
        if (isCompose) {
            val bom = libs.getLibrary("androidx-compose-bom")
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
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
