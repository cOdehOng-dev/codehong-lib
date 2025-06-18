
import com.android.build.api.dsl.LibraryExtension
import com.codehong.convention.configureAndroid
import com.codehong.convention.getPluginId
import com.codehong.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            apply(plugin = libs.getPluginId("android-library"))
            apply(plugin = libs.getPluginId("kotlin-android"))
            apply(plugin = libs.getPluginId("kotlin-kapt"))
            apply(plugin = libs.getPluginId("kotlin-parcelize"))
            apply(plugin = libs.getPluginId("ksp"))

            // TODO HONG 찾지를 못 함
//            apply(plugin = libs.getPluginId("kotlin-compose"))
//            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            extensions.configure<LibraryExtension> {
                configureAndroid(
                    commonExtension = this,
                    isCompose = true
                )
            }
        }
    }
}