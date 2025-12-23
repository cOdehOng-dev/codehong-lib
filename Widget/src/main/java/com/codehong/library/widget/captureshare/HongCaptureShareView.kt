package com.codehong.library.widget.captureshare

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.activity.result.ActivityResultLauncher
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.codehong.library.widget.databinding.HonglibViewCaptureShareBinding
import com.codehong.library.widget.extensions.isAppForeground
import com.codehong.library.widget.util.PermissionManager
import java.io.File
import java.io.FileOutputStream

class HongCaptureShareView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        HonglibViewCaptureShareBinding.inflate(LayoutInflater.from(context), this, true)

    private var shareCaptureUri: Uri? = null

    private val autoDismissHandler = Handler(Looper.getMainLooper())
    private val autoDismissRunnable = Runnable {
        hideScreenShotView()
    }

    private lateinit var screenshotObserver: ContentObserver
    private var lastScreenshotCheckTime = 0L
    private val debounceInterval = 3000L

    private var shareLink: String? = null

    @SuppressLint("ClickableViewAccessibility")
    fun initialScreenShotShareView(
        activity: Activity?,
        launcher: ActivityResultLauncher<Array<String>>,
        shareLink: String? = null
    ) {
        if (activity == null) return

        this.shareLink = shareLink

        binding.clCaptureShare.visibility = GONE
        val gestureDetector = GestureDetector(
            context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    if (e1 != null) {
                        val deltaY = e1.y - e2.y
                        if (deltaY > 100 && velocityY < -1000) { // 위로 스와이프 감지
                            hideScreenShotView()
                            return true
                        }
                    }
                    return false
                }
            }
        )

        binding.clCaptureShare.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        binding.tvShare.setOnClickListener {
            share()
            hideScreenShotView()
        }
        registerScreenshotObserver(activity, launcher)
    }

    private fun registerScreenshotObserver(
        activity: Activity?,
        launcher: ActivityResultLauncher<Array<String>>
    ) {
        if (activity == null) return

        this.screenshotObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)
                val now = System.currentTimeMillis()
                if (now - lastScreenshotCheckTime < debounceInterval) {
                    return
                }
                lastScreenshotCheckTime = now

                if (!activity.isAppForeground()) {
                    return
                }
                val isSystemGranted = PermissionManager.isAllGranted(
                    activity,
                    PermissionManager.getScreenCapturePermissionGroup()
                )
                val isAccessAllGranted = PermissionManager.checkPhotoAccessIsLimited(activity)
                if (isAccessAllGranted && isSystemGranted) {
                    HongCaptureShareManager.observeScreenshot(activity) { bitmap ->
                        val cachePath = File(context.cacheDir, "images").apply { mkdirs() }
                        val file = File(cachePath, "shared_image.png")
                        FileOutputStream(file).use { stream ->
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        }
                        shareCaptureUri = FileProvider.getUriForFile(
                            context,
                            "${context.packageName}.fileprovider",
                            file
                        )
                        Glide.with(context)
                            .asBitmap()
                            .load(shareCaptureUri)
                            .into(binding.ivCapture)

                        binding.clCaptureShare.post {
                            slideDown()
                        }
                    }
                } else {
                    if (!isAccessAllGranted && !isSystemGranted) {
                        HongCaptureShareManager.showPermissionDialog(
                            activity,
                            launcher
                        )
                    } else {
                        HongCaptureShareManager.showSettingDialog(activity)
                    }
                }
            }
        }

        activity.contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            screenshotObserver
        )
    }

    fun setShareLink(shareLink: String?) {
        this.shareLink = shareLink
    }

    private fun share() {
        if (shareCaptureUri == null) {
            return
        }
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, shareCaptureUri)
            shareLink?.let { putExtra(Intent.EXTRA_TEXT, it) }
            type = "image/png"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "캡처 공유하기"))
    }

    private fun slideDown() {
        binding.clCaptureShare.visibility = View.VISIBLE
        binding.clCaptureShare.translationY = -100f
        binding.clCaptureShare.animate()
            .translationY(0f)
            .setDuration(300)
            .setInterpolator(DecelerateInterpolator())
            .start()

        // 10초 후 자동 사라짐 예약
        autoDismissHandler.removeCallbacks(autoDismissRunnable)
        autoDismissHandler.postDelayed(autoDismissRunnable, 5000)
    }

    private fun hideScreenShotView() {
        autoDismissHandler.removeCallbacks(autoDismissRunnable)
        binding.clCaptureShare.animate()
            .translationY(-binding.clCaptureShare.height.toFloat())
            .setDuration(300)
            .withEndAction {
                binding.clCaptureShare.visibility = GONE
            }
            .start()
    }

    fun removeAutoDismissHandler(activity: Activity?) {
        activity?.contentResolver?.unregisterContentObserver(screenshotObserver)
        autoDismissHandler.removeCallbacks(autoDismissRunnable)
    }
}
