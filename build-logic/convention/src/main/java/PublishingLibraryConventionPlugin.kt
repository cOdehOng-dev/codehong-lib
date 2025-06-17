
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create

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
                extensions.configure<PublishingExtension>("publishing") {
                    publications {
                        create<MavenPublication>("release") {
                            from(components.findByName("release"))
                            groupId = project.properties["GROUP_ID"] as String
                            artifactId = project.properties["ARTIFACT_ID"] as String
                            version =  project.properties["VERSION_NAME"] as String
                        }
                        create<MavenPublication>("debug") {
                            from(components.findByName("debug"))
                            groupId = project.properties["GROUP_ID"] as String
                            artifactId = project.properties["ARTIFACT_ID"] as String
                            version =  project.properties["VERSION_NAME"] as String
                        }
                    }
                }
            }
        }
    }
}