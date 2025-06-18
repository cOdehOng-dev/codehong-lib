package com.codehong.library.widget.dynamicisland

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher

object DynamicIslandManager {

    fun isRunning() = DynamicIslandService.isRunning

    fun reset(info: DynamicIslandInfo?) {
        DynamicIslandService.instance?.reset(info)
    }

    fun isGranted(activity: Activity?): Boolean {
        if (activity == null) return false

        return Settings.canDrawOverlays(activity)
    }


    fun checkPermission(
        activity: Activity?,
        launcher: ActivityResultLauncher<Intent>,
        granted: () -> Unit
    ) {
        if (activity == null) return

        if (isGranted(activity)) {
            granted.invoke()
        } else {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${activity.packageName}")
            )
            launcher.launch(intent)
        }
    }

    fun schedule(
        activity: Activity?,
        info: DynamicIslandInfo?
    ) {
        if (activity == null || info == null) return

        val serviceIntent = Intent(activity, DynamicIslandService::class.java).apply {
            putExtra(DynamicIslandConst.DYNAMIC_ISLAND_INFO, info)
        }
        activity.startService(serviceIntent)
    }

    fun setPermission(
        activity: Activity?,
        isChecked: Boolean,
        launcher: ActivityResultLauncher<Intent>,
        start: () -> Unit
    ) {
        if (activity == null) return
        if (isChecked) {
            checkPermission(
                activity,
                launcher = launcher
            ) {
                start.invoke()
            }
        } else {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${activity.packageName}")
            )
            launcher.launch(intent)
        }
    }
}