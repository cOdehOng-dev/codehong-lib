plugins {
    alias(libs.plugins.codehong.android.application)
    alias(libs.plugins.codehong.android.application.compose)
    alias(libs.plugins.codehong.android.build.type)
    alias(libs.plugins.codehong.android.flavor)
    alias(libs.plugins.codehong.android.hilt)
}
android {
    namespace = project.properties["APP_ID"].toString()

    defaultConfig {
        applicationId = project.properties["APP_ID"].toString()
        versionName = project.properties["VERSION_NAME"].toString()
    }
}

dependencies {
    implementation(project(":network"))
    implementation(project(":Widget"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.material)
}
