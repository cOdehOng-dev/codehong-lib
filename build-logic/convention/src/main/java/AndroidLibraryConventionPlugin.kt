
import com.android.build.api.dsl.LibraryExtension
import com.codehong.convention.ExtensionType
import com.codehong.convention.configureAndroid
import com.codehong.convention.configureBuildTypes
import com.codehong.convention.getPluginId
import com.codehong.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            apply(plugin = libs.getPluginId("android-library"))
            apply(plugin = libs.getPluginId("kotlin-android"))
            apply(plugin = libs.getPluginId("kotlin-kapt"))
            apply(plugin = libs.getPluginId("kotlin-parcelize"))
            apply(plugin = libs.getPluginId("ksp"))

            extensions.configure<LibraryExtension> {
                configureAndroid(
                    this,
                    false
                )

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.LIBRARY
                )

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
            }
        }
    }
}