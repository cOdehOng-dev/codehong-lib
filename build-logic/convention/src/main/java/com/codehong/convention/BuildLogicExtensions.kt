package com.codehong.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.getVersion(alias: String): Int {
    return this.findVersion(alias).get().toString().toInt()
}

fun VersionCatalog.getLibrary(alias: String): Provider<MinimalExternalModuleDependency> {
    return this.findLibrary(alias).get()
}

fun VersionCatalog.getPluginId(alias: String): String {
    return this.findPlugin(alias).get().get().pluginId
}