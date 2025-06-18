
import java.util.Properties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
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

            extensions.configure<PublishingExtension> {
                repositories {
                    maven {
                        val props = Properties().apply {
                            load(rootProject.file("github.properties").inputStream())
                        }

                        name = "GitHubPackages"
                        url = uri(props.getProperty("url"))
                        credentials {
                            username = props.getProperty("username")
                            password = props.getProperty("token")
                        }
                    }
                }

                // ✅ Maven Publication 설정
                publications {
                    create<MavenPublication>("release") {
                        groupId = project.properties["GROUP_ID"] as String
                        artifactId = project.properties["ARTIFACT_ID"] as String
                        version =  project.properties["VERSION_NAME"] as String
                    }
                }
            }
        }
    }
}