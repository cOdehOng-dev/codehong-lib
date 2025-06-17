import com.android.build.api.dsl.ApplicationExtension
import com.codehong.convention.configureAndroid
import com.codehong.convention.getPluginId
import com.codehong.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            apply(plugin = libs.getPluginId("android-application"))
            apply(plugin = libs.getPluginId("kotlin-android"))
            apply(plugin = libs.getPluginId("kotlin-kapt"))
            apply(plugin = libs.getPluginId("kotlin-parcelize"))
            apply(plugin = libs.getPluginId("ksp"))

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroid(
                commonExtension = extension,
                isCompose = true
            )
        }
    }
}