package com.codehong.library.widget.captureshare

import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import com.codehong.library.widget.dialog.HongDialog
import com.codehong.library.widget.util.PermissionManager
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object HongCaptureShareManager {
    private const val DATA_STORE = "DATA_STORE"
    private const val KEY_ONE_MONTH_LATER = "one_month_later"

    fun observeScreenshot(
        activity: Activity,
        screenShot: (Bitmap) -> Unit
    ) {
        if (activity.contentResolver == null) return
        Handler(Looper.getMainLooper()).postDelayed({
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN
            )

            // 스크린샷 디렉토리 내 이미지를 대상으로만 제한
            val selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
            val selectionArgs = arrayOf("%Screenshots%")

            val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

            val cursor = activity.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder
            )

            cursor?.use {
                if (it.moveToFirst()) {
                    val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                    val id = it.getLong(idColumn)

                    val uri =
                        ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    val inputStream = activity.contentResolver.openInputStream(uri)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream?.close()
                    screenShot.invoke(bitmap)
                }
            }
        }, 200)
    }

    fun checkPermission(
        activity: Activity?,
        launcher: ActivityResultLauncher<Array<String>>
    ) {
        if (activity == null) return

        val isSystemGranted = PermissionManager.isAllGranted(
            activity,
            PermissionManager.getScreenCapturePermissionGroup()
        )
        val isAccessAllGranted = PermissionManager.checkPhotoAccessIsLimited(activity)

        when {
            !isSystemGranted -> {
                if (PermissionManager.checkPermission(
                        activity,
                        PermissionManager.getScreenCapturePermissionGroup(),
                        launcher
                    )
                ) {
                    if (!PermissionManager.checkPhotoAccessIsLimited(activity)) {
                        showSettingDialog(activity)
                    }
                }
            }

            !isAccessAllGranted -> showSettingDialog(activity)
            else -> {}
        }
    }

    fun showPermissionDialog(
        activity: Activity?,
        launcher: ActivityResultLauncher<Array<String>>
    ) {
        if (activity == null || !isReachedOneMonthLater(activity)) return
        HongDialog.showDoubleButtonDialog(
            activity,
            "알림",
            "스크린샷 공유기능을 사용하고 싶으면 설정에서 권한을 켜주세요.",
            "허용",
            "취소",
            positiveClick = { dialog, _ ->
                dialog.dismiss()
                checkPermission(activity, launcher)
            },
            negativeClick = { dialog, _ ->
                saveOneMonthLater(activity)
                dialog.dismiss()
            }
        )
    }

    fun showSettingDialog(activity: Activity?) {
        if (activity == null || !isReachedOneMonthLater(activity)) return

        HongDialog.showDoubleButtonDialog(
            activity,
            "알림",
            "사진 전체 접근해야지만 사용이 가능해요.",
            "설정 이동",
            "취소",
            positiveClick = { dialog, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", activity.packageName, null)
                }
                activity.startActivity(intent)
                dialog.dismiss()
            },
            negativeClick = { dialog, _ ->
                saveOneMonthLater(activity)
                dialog.dismiss()
            }
        )
    }

    fun saveOneMonthLater(context: Context?) {
        if (context == null) return

        val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
        val oneMonthLater = now.plusMonths(1)

        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val formattedDate = oneMonthLater.format(formatter)

        val prefs = context.getSharedPreferences(DATA_STORE, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_ONE_MONTH_LATER, formattedDate).apply()
    }

    fun isReachedOneMonthLater(context: Context?): Boolean {
        if (context == null) return false
        val prefs = context.getSharedPreferences(DATA_STORE, Context.MODE_PRIVATE)
        val dateString = prefs.getString(KEY_ONE_MONTH_LATER, "") ?: return false

        if (dateString.isEmpty()) return true

        return try {
            val savedDate = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME)
            val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
            !now.isBefore(savedDate)
        } catch (e: Exception) {
            false
        }
    }
}
