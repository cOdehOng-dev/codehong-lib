plugins {
    `kotlin-dsl`
}

group = "com.codehong.core.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.android.tools.common)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "codehong.lib.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "codehong.lib.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "codehong.lib.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "codehong.lib.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("publishingLibrary") {
            id = "codehong.lib.library.publishing"
            implementationClass = "PublishingLibraryConventionPlugin"
        }
    }
}