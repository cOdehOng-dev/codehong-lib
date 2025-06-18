
import java.util.Properties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get

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

                    // ✅ Maven Repository 설정
                    repositories {
                        maven {
                            val props = Properties().apply {
                                load(rootProject.file("github.properties").inputStream())
                            }

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
                            from(components["release"])
                        }
                    }
                }
            }

//            extensions.configure<PublishingExtension> {
//                repositories {
//                    maven {
//                        val githubProperties = Properties().apply {
//                            load(rootProject.file("github.properties").inputStream())
//                        }
//
//                        name = "GitHubPackages"
//                        url = uri(githubProperties["url"] as String)
//                        credentials {
//                            username = githubProperties["username"] as String
//                            password = githubProperties["token"] as String
//                        }
//                    }
//                }
//                publications {
//                    create<MavenPublication>("release") {
//                        groupId = project.properties["GROUP_ID"] as String
//                        artifactId = project.properties["ARTIFACT_ID"] as String
//                        version =  project.properties["VERSION_NAME"] as String
//                        from(components.findByName("release"))
//                    }
//                    create<MavenPublication>("debug") {
//                        groupId = project.properties["GROUP_ID"] as String
//                        artifactId = project.properties["ARTIFACT_ID"] as String
//                        version =  project.properties["VERSION_NAME"] as String
//                        from(components.findByName("debug"))
//                    }
//                }
//            }

//            afterEvaluate {
//                extensions.configure<PublishingExtension>("publishing") {
//                    publications {
//                        create<MavenPublication>("release") {
//                            from(components.findByName("release"))
//                            groupId = project.properties["GROUP_ID"] as String
//                            artifactId = project.properties["ARTIFACT_ID"] as String
//                            version =  project.properties["VERSION_NAME"] as String
//                        }
//                        create<MavenPublication>("debug") {
//                            from(components.findByName("debug"))
//                            groupId = project.properties["GROUP_ID"] as String
//                            artifactId = project.properties["ARTIFACT_ID"] as String
//                            version =  project.properties["VERSION_NAME"] as String
//                        }
//                    }
//                }
//            }
        }
    }
}