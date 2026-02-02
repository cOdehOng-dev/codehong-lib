plugins {
    alias(libs.plugins.codehong.android.library)
    alias(libs.plugins.codehong.android.library.compose)
    alias(libs.plugins.codehong.android.library.publishing)
}

android {
    namespace = "com.codehong.library.util"
}

dependencies {
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    api(libs.timber)
    api(libs.threetenapb)
    api(libs.haze)
}
