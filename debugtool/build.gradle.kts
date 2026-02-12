plugins {
    alias(libs.plugins.codehong.android.library)
    alias(libs.plugins.codehong.android.library.publishing)
}

android {
    namespace = "com.codehong.library.debugtool"
}

dependencies {
    implementation(libs.timber)
}
