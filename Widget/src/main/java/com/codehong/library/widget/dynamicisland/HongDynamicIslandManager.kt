package com.codehong.library.widget.dynamicisland

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher

object HongDynamicIslandManager {

    fun isRunning() = HongDynamicIslandService.isRunning

    fun reset(info: DynamicIslandInfo?) {
        HongDynamicIslandService.instance?.reset(info)
    }

    fun isGranted(context: Context?): Boolean {
        if (context == null) return false

        return Settings.canDrawOverlays(context)
    }

    fun checkPermission(
        context: Context?,
        launcher: ActivityResultLauncher<Intent>,
        granted: () -> Unit
    ) {
        if (context == null) return

        if (isGranted(context)) {
            granted.invoke()
        } else {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${context.packageName}")
            )
            launcher.launch(intent)
        }
    }

    fun schedule(
        context: Context?,
        info: DynamicIslandInfo?
    ) {
        if (context == null || info == null) return

        val serviceIntent = Intent(context, HongDynamicIslandService::class.java).apply {
            putExtra(HongDynamicIslandConst.DYNAMIC_ISLAND_INFO, info)
        }
        context.startService(serviceIntent)
    }

    fun setPermission(
        context: Context?,
        isChecked: Boolean,
        launcher: ActivityResultLauncher<Intent>,
        start: () -> Unit
    ) {
        if (context == null) return
        if (isChecked) {
            checkPermission(
                context,
                launcher = launcher
            ) {
                start.invoke()
            }
        } else {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${context.packageName}")
            )
            launcher.launch(intent)
        }
    }
}