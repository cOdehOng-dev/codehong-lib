plugins {
    alias(libs.plugins.codehong.lib.android.application.compose)
}

android {
    namespace = project.properties["APP_ID"].toString()

    defaultConfig {
        applicationId = project.properties["APP_ID"].toString()
        versionName = project.properties["VERSION_NAME"].toString()
    }
}

dependencies {

//    debugImplementation(project(":Widget"))
    implementation(project(":Widget"))
//    implementation(codehonglibs.widget)

    implementation(libs.androidx.multidex)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity)

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


    implementation(libs.google.accompanist.pager)
    implementation(libs.google.accompanist.pager.indicators)
    implementation(libs.compose.coil)
    implementation(libs.andoridx.compose.ui.graphics)
    implementation(libs.threetenapb)


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
