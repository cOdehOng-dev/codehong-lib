
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType

class PublishingLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("maven-publish")

            task("assembleToPublish") {
                group = "github"
                dependsOn("assembleRelease")
                finalizedBy("publish")
            }

            afterEvaluate {
                val publishing = extensions.getByType<PublishingExtension>()
                publishing.publications {
                    create<MavenPublication>("release") {
                        from(components["release"])
                        groupId = project.properties["GROUP_ID"] as String
                        artifactId = project.properties["ARTIFACT_ID"] as String // "widget"
                        version =  project.properties["VERSION_NAME"] as String // "0.0.3.8"
                    }
                    create<MavenPublication>("debug") {
                        from(components["debug"])
                        groupId = project.properties["GROUP_ID"] as String
                        artifactId = project.properties["ARTIFACT_ID"] as String // "widget"
                        version =  project.properties["VERSION_NAME"] as String // "0.0.3.8"
                    }
                }
            }

        }
    }
}