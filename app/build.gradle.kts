plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.codehong.lib.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.codehong.lib"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":Widget"))

    hongImpl(
        implLibs = arrayOf(
            Libs.MULTIDEX,
            Libs.FRAGMENT,
            Libs.CORE,
            Libs.APPCOMPAT,
            Libs.LIFECYCLE_RUNTIME,
            Libs.CONSTRAINT_LAYOUT,
            Libs.ACTIVITY_KTX,
            Libs.FRAGMENT_KTX
        )
    )

    hongImpl(
        implLibs = arrayOf(
            platform(Libs.COMPOSE_BOM),
            Libs.COMPOSE_CONSTRAINTLAYOUT,
            Libs.HILT_NAVIGATION_COMPOSE,
            Libs.COMPOSE_MATERIAL3,
//            Libs.COMPOSE_MATERIAL,
            Libs.COMPOSE_FOUNDATION,
            Libs.COMPOSE_UI,
            Libs.COMPOSE_RUNTIME,
            Libs.COMPOSE_TOOLING_PREVIEW,
            Libs.COMPOSE_LIFECYCLE_VIEWMODEL,
            Libs.COMPOSE_ACTIVITY,
            Libs.COMPOSE_LIVEDATA,
            Libs.COMPOSE_RXJAVA2,
            Libs.COMPOSE_MATERIAL_ADAPTIVE,
            Libs.ACCOMPANIST_PAGER,
            Libs.ACCOMPANIST_PAGER_INDICATORS,
            Libs.COMPOSE_COIL,
            Libs.COMPOSE_UI_GRAPHICS,
            Libs.THREETENAPB
        )
    )

    debugHongImpl(
        implLibs = arrayOf(
            Libs.COMPOSE_TOOLING,
            Libs.COMPOSE_UI_TEST_MANIFEST
        )
    )

    // -------------------------------------------------
    // TEST
    // -------------------------------------------------
    testImpl(
        testLibs = arrayOf(
            Libs.JUNIT
        ),
        androidTestLibs = arrayOf(
            Libs.ESPRESSO_CORE,
            Libs.COMPOSE_BOM,
            Libs.COMPOSE_JUNIT4,
            Libs.EXT_JUNIT
        )
    )
}
