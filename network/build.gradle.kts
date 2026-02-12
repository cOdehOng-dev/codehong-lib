plugins {
    alias(libs.plugins.codehong.android.library)
    alias(libs.plugins.codehong.android.network)
    alias(libs.plugins.codehong.android.library.publishing)
}

android {
    namespace = "com.codehong.library.network"
}

dependencies {
    implementation(codehonglibs.debugtool)

    api(libs.kotlinx.serialization)
}
