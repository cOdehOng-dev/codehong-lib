package com.codehong.library.widget.util

import android.Manifest
import android.app.Activity
import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.codehong.library.widget.extensions.safeLaunch

object PermissionManager {

    private var rationaleAlert: AlertDialog? = null
    private var settingAlert: AlertDialog? = null

    fun isAllGranted(
        context: Context?,
        manifestPermissions: Array<String>
    ): Boolean {
        if (context == null) return false
        for (manifestPermission in manifestPermissions) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    manifestPermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    fun getScreenCapturePermissionGroup(): Array<String> {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES
            )

            else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    fun checkPermission(
        activity: Activity?,
        manifestPermissions: Array<String>,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        isShowRationaleAlert: Boolean? = true,
        rationaleCancel: (() -> Unit)? = null
    ): Boolean {
        return checkPermission(
            activity,
            manifestPermissions,
            shouldShowPermissionRationale = { a, list ->
                if (isShowRationaleAlert == true) {
                    showPermissionRationaleDialog(a, list, permissionLauncher, rationaleCancel)
                } else {
                    permissionLauncher.safeLaunch(activity, list)
                }
            },
            requestPermission = { _, list ->
                permissionLauncher.safeLaunch(activity, list)
            }
        )
    }

    private fun checkPermission(
        activity: Activity?,
        manifestPermissions: Array<String>,
        shouldShowPermissionRationale: (Activity, Array<String>) -> Unit,
        requestPermission: (Activity, Array<String>) -> Unit
    ): Boolean {
        if (activity == null) return false
        if (manifestPermissions.isEmpty()) return true

        val deniedPermissionList = ArrayList<String>()
        var grantedPermissionCnt = 0
        manifestPermissions.forEach { p ->
            if (ContextCompat.checkSelfPermission(
                    activity,
                    p
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                grantedPermissionCnt++
            } else {
                deniedPermissionList.add(p)
            }
        }

        val rationaleInfo = isShouldShowRequestPermissionRationale(
            activity,
            deniedPermissionList.toTypedArray()
        )

        return when {
            grantedPermissionCnt == manifestPermissions.size -> true

            rationaleInfo.first -> {
                shouldShowPermissionRationale(activity, rationaleInfo.second)
                false
            }

            deniedPermissionList.size != 0 -> {
                requestPermission(activity, deniedPermissionList.toTypedArray())
                false
            }

            else -> true
        }
    }

    fun isShouldShowRequestPermissionRationale(
        activity: Activity?,
        manifestPermissions: Array<String>?
    ): Pair<Boolean, Array<String>> {
        if (activity == null) return Pair(false, emptyArray())
        if (manifestPermissions.isNullOrEmpty()) return Pair(false, emptyArray())

        val shouldShowPermissionRationaleList = ArrayList<String>()
        manifestPermissions.forEach { p ->
            if (p.isNotEmpty()
                && ActivityCompat.shouldShowRequestPermissionRationale(activity, p)
            ) {
                shouldShowPermissionRationaleList.add(p)
            }
        }

        return Pair(
            shouldShowPermissionRationaleList.isNotEmpty(),
            shouldShowPermissionRationaleList.toTypedArray()
        )
    }

    fun showPermissionRationaleDialog(
        activity: Activity?,
        manifestPermission: Array<String>,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        cancel: (() -> Unit?)? = null
    ) {
        if (activity == null) return

        val requestPermissionNames = getPermissionName(manifestPermission)
        val message = "이 기능을 사용하려면 $requestPermissionNames 앱 권한이 필요합니다. 권한을 허용해주세요."

        if (rationaleAlert != null) {
            rationaleAlert?.showDialog(activity)
            rationaleAlert?.setMessage(message)

            settingAlert?.dismiss()
            settingAlert = null
        } else {
            rationaleAlert = createAndShowPermissionRationaleDialog(
                activity,
                message,
                clickPositiveButton = {
                    permissionLauncher.safeLaunch(activity, manifestPermission)
                },
                clickNegativeButton = {
                    cancel?.invoke()
                }
            )
        }
    }

    fun checkPhotoAccessIsLimited(context: Context?): Boolean {
        if (context == null) return false

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val mode = appOpsManager.unsafeCheckOpNoThrow(
                "android:read_media_images",
                android.os.Process.myUid(),
                context.packageName
            )

            when (mode) {
                AppOpsManager.MODE_ALLOWED -> true
                else -> false
            }
        } else {
            true
        }
    }

    private fun AlertDialog?.showDialog(activity: Activity?) {
        if (activity == null) return

        if (!activity.isFinishing
            && (activity as? FragmentActivity)?.supportFragmentManager != null
            && (activity as? FragmentActivity)?.supportFragmentManager?.isDestroyed == false
            && this?.isShowing != true
        ) {
            try {
                this?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun createAndShowPermissionRationaleDialog(
        activity: Activity,
        message: String,
        clickPositiveButton: () -> Unit,
        clickNegativeButton: () -> Unit
    ): AlertDialog {
        val dialog = AlertDialog.Builder(activity)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
                clickPositiveButton()
                rationaleAlert = null
            }.setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
                clickNegativeButton()
                rationaleAlert = null
            }.create()
        dialog.showDialog(activity)

        settingAlert?.dismiss()
        settingAlert = null

        return dialog
    }

    fun getPermissionName(manifestPermissions: Array<String>): String {
        val names = arrayListOf<String>()
        manifestPermissions.forEach { permission ->
            getPermissionName(permission).takeIf { it.isNotEmpty() }?.let {
                names.add(it)
            }
        }
        return names.distinct().joinToString(", ")
    }

    private fun getPermissionName(manifestPermission: String): String {
        val name = when (manifestPermission) {
            Manifest.permission.POST_NOTIFICATIONS -> "알림"

            Manifest.permission.CAMERA -> "카메라"

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES -> "사진 및 동영상(저장공간)"

            Manifest.permission.ACCESS_FINE_LOCATION -> "위치"
            Manifest.permission.READ_PHONE_STATE -> "전화"
            else -> ""
        }

        return name
    }
}
