plugins {
    alias(libs.plugins.codehong.android.library)
    alias(libs.plugins.codehong.android.library.compose)
    alias(libs.plugins.codehong.android.library.publishing)
}

android {
    namespace = "com.codehong.library.widget"
}

dependencies {
    implementation(libs.material)
    api(libs.timber)

//    api(libs.gson)
//    api(libs.bundles.retrofit)
//
//    api(platform(libs.okhttp.bom))
//    api(libs.okhttp)
//    api(libs.okhttp.logging.interceptor)
//    api(libs.okhttp.urlconnection)

    api(libs.androidx.media3.exoplayer)
    api(libs.androidx.media3.ui)
    api(libs.androidx.media3.exoplayer.hls)
    api(libs.androidx.media3.datasource)
    api(libs.threetenapb)
    api(libs.haze)
}
