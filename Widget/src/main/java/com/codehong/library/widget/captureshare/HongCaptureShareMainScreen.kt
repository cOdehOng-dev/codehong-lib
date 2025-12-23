package com.codehong.library.widget.captureshare

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.codehong.library.widget.extensions.isAppForeground
import com.codehong.library.widget.util.PermissionManager
import java.io.File
import java.io.FileOutputStream

@Composable
fun CaptureShareMainScreen(
    activity: Activity,
    shareLink: String?
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showCaptureView by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val granted = result.values.all { it }
        if (!granted || !PermissionManager.checkPhotoAccessIsLimited(activity)) {
            HongCaptureShareManager.showSettingDialog(activity)
        }
    }

    fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
        val file = File(context.cacheDir, "shared_image.png").apply { parentFile?.mkdirs() }
        FileOutputStream(file).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    fun shareImageUri(context: Context, uri: Uri, shareLink: String?) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            shareLink?.let { putExtra(Intent.EXTRA_TEXT, it) }
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "캡처 공유하기"))
    }

    // 스크린샷 감지 ContentObserver
    DisposableEffect(Unit) {
        val observer = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean) {
                if (!activity.isAppForeground()) return

                val granted = PermissionManager.isAllGranted(
                    activity, PermissionManager.getScreenCapturePermissionGroup()
                )
                val limited = PermissionManager.checkPhotoAccessIsLimited(activity)

                if (granted && limited) {
                    HongCaptureShareManager.observeScreenshot(activity) { bitmap ->
                        val uri = saveBitmapToCache(activity, bitmap)
                        imageUri = uri
                        showCaptureView = true
                    }
                } else {
                    HongCaptureShareManager.showPermissionDialog(activity, permissionLauncher)
                }
            }
        }

        activity.contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            observer
        )

        onDispose {
            activity.contentResolver.unregisterContentObserver(observer)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            "샘플 캡처 공유 Compose 화면",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        if (showCaptureView && imageUri != null) {
            HongCaptureShareCompose(
                imageUri = imageUri!!,
                onDismiss = { showCaptureView = false },
                onShareClicked = {
                    shareImageUri(activity, imageUri!!, shareLink)
                }
            )
        }
    }
}
