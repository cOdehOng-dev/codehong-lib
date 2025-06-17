
import com.android.build.api.dsl.LibraryExtension
import com.codehong.convention.configureAndroid
import com.codehong.convention.getPluginId
import com.codehong.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            apply(plugin = libs.getPluginId("codehong-lib-android-library"))

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroid(
                commonExtension = extension,
                isCompose = true
            )
        }
    }
}