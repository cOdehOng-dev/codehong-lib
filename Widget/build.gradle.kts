plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("maven-publish")
}

android {
    namespace = "com.codehong.library.widget"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    hongImpl(
        implLibs = arrayOf(
            Libs.MULTIDEX,
            Libs.FRAGMENT,
            Libs.CORE,
            Libs.APPCOMPAT,
            Libs.LIFECYCLE_RUNTIME
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

//    implementation("androidx.core:core-ktx:1.9.0")
//    implementation("androidx.appcompat:appcompat:1.7.0")
//    implementation("com.google.android.material:material:1.12.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.2.1")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

//group = "com.github.cOdehOng-dev"

task("assembleToPublish") {
    dependsOn("assembleRelease")
    group = "github"
    finalizedBy("publish")
}


afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.cOdehOng-dev"
                artifactId = "widget"
                version = "0.0.1"
            }
            create<MavenPublication>("debug") {
                from(components["debug"])
                groupId = "com.github.cOdehOng-dev"
                artifactId = "widget"
                version = "0.0.1"
            }
        }
    }
}