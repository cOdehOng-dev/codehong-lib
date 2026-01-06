plugins {
    alias(libs.plugins.codehong.lib.android.library.compose)
    alias(libs.plugins.codehong.lib.android.library.publishing)
}

android {
    namespace = "com.codehong.library.widget"
}

dependencies {

    implementation(libs.androidx.multidex)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.glide)
    implementation(libs.glide.transformation)
    ksp(libs.glide.ksp)

    implementation(libs.material)

    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.rxjava2)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewpager2)

    implementation(libs.google.accompanist.pager)
    implementation(libs.google.accompanist.pager.indicators)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.andoridx.compose.ui.graphics)
    implementation(libs.threetenapb)

    implementation(libs.google.accompanist.drawablepainter)
    implementation(libs.google.accompanist.flowlayout)


    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.exoplayer.hls)
    implementation(libs.androidx.media3.datasource)

    implementation(libs.timber)
    implementation("dev.chrisbanes.haze:haze:1.3.0")

    debugImplementation(libs.androidx.compose.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // -------------------------------------------------
    // TEST
    // -------------------------------------------------
    testImplementation(libs.junit)

    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.compose.junit4)
    androidTestImplementation(libs.ext.junit)
}
