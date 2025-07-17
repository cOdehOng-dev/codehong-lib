package com.codehong.library.widget.dynamicisland

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.codehong.library.widget.databinding.HonglibViewDynamicIslandBinding
import com.codehong.library.widget.extensions.dpToPx
import java.lang.ref.WeakReference

class DynamicIslandService : Service() {

    companion object {
        var isRunning = false
        private var _instance: WeakReference<DynamicIslandService>? = null
        val instance: DynamicIslandService?
            get() = _instance?.get()
    }

    private lateinit var binding: HonglibViewDynamicIslandBinding

    private var params: WindowManager.LayoutParams? = null
    private var windowManager: WindowManager? = null
    private var dynamicIslandView: View? = null

    private var productStartTime = 0L
    private var dispEndTime = 0L
    private var link: String? = null

    private var isExpanded = false

    private val handler = Handler(Looper.getMainLooper())

    private val checkRunnable = object : Runnable {
        override fun run() {
            val now = System.currentTimeMillis()
            if (now >= dispEndTime) {
                remove()
                if (isExpanded) {
                    collapseExpandDynamicIsland {
                        removeDynamicIsland()
                    }
                } else {
                    collapseSmallDynamicIsland()
                }
            } else {
                val remaining = productStartTime - now
                binding.tvSmallState.text = getStateMessage(remaining, true)
                binding.tvExpandState.text = getStateMessage(remaining, false)
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        _instance = WeakReference(this)
        this.windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        binding = HonglibViewDynamicIslandBinding.inflate(LayoutInflater.from(this))

        this.isExpanded = false
        binding.clExpandedContent.visibility = View.GONE
        binding.llSmallContent.visibility = View.VISIBLE
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(
                DynamicIslandConst.DYNAMIC_ISLAND_INFO,
                DynamicIslandInfo::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent?.getParcelableExtra(DynamicIslandConst.DYNAMIC_ISLAND_INFO)
        } ?: return START_NOT_STICKY

        this.productStartTime = info.startTime
        this.dispEndTime = info.dispEndTime
        this.link = info.link

        if (dynamicIslandView == null) {
            showDynamicIsland(info)
        }

        handler.post(checkRunnable)

        return START_STICKY
    }

    override fun onDestroy() {
        isRunning = false
        _instance = null
        handler.removeCallbacks(checkRunnable)
        remove()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            stopForeground(STOP_FOREGROUND_DETACH)
        } else {
            @Suppress("DEPRECATION")
            stopForeground(true)
        }
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    fun reset(info: DynamicIslandInfo?) {
        if (info == null) return

        this.productStartTime = info.startTime
        this.dispEndTime = info.dispEndTime
        this.link = info.link

        setDynamicIslandContent(info)

        handler.removeCallbacks(checkRunnable)
        handler.post(checkRunnable)
    }

    private fun showDynamicIsland(info: DynamicIslandInfo) {
        this.params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            this.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            this.y = 10
        }
        dynamicIslandView = binding.root
        windowManager?.addView(dynamicIslandView, params)

        // 컨텐츠 세팅
        setDynamicIslandContent(info)

        with(binding.llSmallContent) {
            setOnClickListener {
                executeLandingLink()
            }
            setOnLongClickListener {
                expandDynamicIsland()
                true
            }
        }

        binding.clExpandedContent.setOnClickListener {
            collapseExpandDynamicIsland {
                executeLandingLink()
            }
        }

        binding.flRoot.setOnClickListener {
            collapseExpandDynamicIsland()
        }
    }

    private fun setDynamicIslandContent(info: DynamicIslandInfo) {
        binding.tvAppName.text = "HONG AIR"
        binding.tvFrom.text = info.fromCity
        binding.tvTo.text = info.toCity

        val requestOptions = RequestOptions()
            .transform(RoundedCorners(this.dpToPx(10f)))

        Glide.with(this)
            .load(info.thumbnailUrl)
            .apply(requestOptions)
            .into(binding.ivThumbnail)
    }

    private fun executeLandingLink() {
        if (link.isNullOrEmpty()) return

        val uri = Uri.parse(link)
        val launchIntent = Intent(Intent.ACTION_VIEW, uri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(launchIntent)
    }

    // 확장 애니메이션: 위에서 아래로 양쪽으로 퍼지며 바운스 효과
    private fun expandDynamicIsland() {
        if (isExpanded) return
        this.isExpanded = true

        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        windowManager?.updateViewLayout(dynamicIslandView, params)
        binding.llSmallContent.visibility = View.GONE
        binding.clExpandedContent.visibility = View.VISIBLE

        binding.clExpandedContent.apply {
            scaleX = 0f
            scaleY = 0f
            pivotX = (width / 2).toFloat()
            pivotY = 0f
            alpha = 0f
            visibility = View.VISIBLE
        }

        binding.clExpandedContent.animate()
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .setDuration(400)
            .setInterpolator(OvershootInterpolator(1f))
            .start()
    }

    private fun collapseExpandDynamicIsland(
        onEnd: (() -> Unit)? = null
    ) {
        if (!isExpanded) return
        this.isExpanded = false

        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        windowManager?.updateViewLayout(dynamicIslandView, params)
        binding.clExpandedContent.apply {
            pivotX = (width / 2).toFloat()
            pivotY = 0f // 상단 중앙 기준
        }

        binding.clExpandedContent.animate()
            .scaleX(0f)
            .scaleY(0f)
            .alpha(0f)
            .setDuration(400)
            .setInterpolator(AnticipateInterpolator())
            .withEndAction {
                binding.clExpandedContent.visibility = View.GONE
                binding.llSmallContent.visibility = View.VISIBLE
                binding.llSmallContent.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setInterpolator(OvershootInterpolator(1f))
                    .withEndAction {
                        onEnd?.invoke()
                    }
                    .start()
            }
            .start()
    }

    private fun collapseSmallDynamicIsland() {
        binding.llSmallContent.animate()
            .scaleX(0f)
            .scaleY(0f)
            .alpha(0f)
            .setDuration(300)
            .withEndAction {
                removeDynamicIsland()
            }
            .start()
    }

    private fun remove() {
        dynamicIslandView?.let {
            windowManager?.removeView(it)
            dynamicIslandView = null
        }
    }

    private fun getStateMessage(
        diffMillis: Long,
        isSmall: Boolean
    ): String {
        val diffMinutes = diffMillis / (60 * 1000)

        val betweenOneMinOneHourMessage = if (isSmall) {
            "탑승 전"
        } else {
            "${diffMinutes}분 뒤 탑승 예정"
        }

        val underOneMinMessage = if (isSmall) {
            "탑승 준비"
        } else {
            "잠시 후 탑승 예정"
        }

        val startMessage = if (isSmall) {
            "탑승 시작"
        } else {
            "지금 탑승 하세요!"
        }

        return when {
            diffMinutes in 1..60 -> betweenOneMinOneHourMessage
            diffMinutes < 1 && diffMillis > 0 -> underOneMinMessage
            else -> startMessage
        }
    }

    private fun removeDynamicIsland() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            stopForeground(STOP_FOREGROUND_DETACH)
        } else {
            @Suppress("DEPRECATION")
            stopForeground(true)
        }
        stopSelf()
    }
}
