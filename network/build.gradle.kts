plugins {
    alias(libs.plugins.codehong.android.library)
    alias(libs.plugins.codehong.android.library.publishing)
}

android {
    namespace = "com.codehong.library.network"
}

dependencies {
    implementation(libs.timber)

    api(libs.gson)
    api(libs.bundles.retrofit)

    api(platform(libs.okhttp.bom))
    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)
    api(libs.okhttp.urlconnection)
}
