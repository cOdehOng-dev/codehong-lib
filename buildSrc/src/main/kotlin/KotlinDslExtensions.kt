import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

fun DependencyHandler.implementation(vararg libs: Any) {
    libs.forEach { dependency -> add("implementation", dependency) }
}

fun String.hongImplOption(
    dependencyConfiguration: Action<ExternalModuleDependency>
) = Pair(this, dependencyConfiguration)

fun DependencyHandler.hongImpl(implLibs: Array<Any>, kaptLibs: Array<Any>? = null) {
    implLibs.forEach { dependency ->
        if (dependency is Pair<*, *>) {
            addDependencyTo(
                this,
                "implementation",
                dependency.first as String,
                dependency.second as Action<ExternalModuleDependency>
            )
        } else {
            add("implementation", dependency)
        }
    }
    kaptLibs?.forEach { dependency -> add("kapt", dependency) }
}

fun DependencyHandler.debugHongImpl(
    implLibs: Array<Any>,
    kaptLibs: Array<Any>? = null
) {
    implLibs.forEach { dependency ->
        if (dependency is Pair<*, *>) {
            addDependencyTo(
                this,
                "debugImplementation",
                dependency.first as String,
                dependency.second as Action<ExternalModuleDependency>
            )
        } else {
            add("debugImplementation", dependency)
        }
    }
    kaptLibs?.forEach { dependency -> add("kaptDebug", dependency) }
}

fun DependencyHandler.hongClasspath(vararg libs: String) {
    libs.forEach { dependency -> add("classpath", dependency) }
}

fun DependencyHandler.testImpl(
    testLibs: Array<Any>? = null,
    androidTestLibs: Array<String>? = null
) {
    testLibs?.forEach { dependency -> add("testImplementation", dependency) }
    androidTestLibs?.forEach { dependency -> add("androidTestImplementation", dependency) }
}
