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
import android.view.WindowManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.codehong.library.widget.dynamicisland.DynamicIslandType.Companion.toStateMessage
import com.codehong.library.widget.dynamicisland.DynamicIslandType.Companion.toType
import java.lang.ref.WeakReference

class DynamicIslandService :
    Service(), SavedStateRegistryOwner, ViewModelStoreOwner {

    companion object {
        var isRunning = false
        private var _instance: WeakReference<DynamicIslandService>? = null
        val instance: DynamicIslandService?
            get() = _instance?.get()
    }

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }
    private val savedStateRegistryController by lazy { SavedStateRegistryController.create(this) }
    override val viewModelStore by lazy { ViewModelStore() }

    override val lifecycle get() = lifecycleRegistry
    override val savedStateRegistry: SavedStateRegistry get() = savedStateRegistryController.savedStateRegistry

    private var windowManager: WindowManager? = null
    private var params: WindowManager.LayoutParams? = null
    private var composeView: ComposeView? = null

    private var islandInfo by mutableStateOf<DynamicIslandInfo?>(null)
    private var isExpanded by mutableStateOf(false)
    private var timeTextSmall by mutableStateOf("")
    private var timeTextLarge by mutableStateOf("")

    private var type: DynamicIslandType = DynamicIslandType.LODGING

    private val handler = Handler(Looper.getMainLooper())
    private val checkRunnable = object : Runnable {
        override fun run() {
            val info = islandInfo ?: return
            val now = System.currentTimeMillis()

            if (now >= info.dispEndTime) {
                if (isExpanded) {
                    isExpanded = false
                    handler.postDelayed({ removeDynamicIsland() }, 400)
                } else {
                    removeDynamicIsland()
                }
            } else {
                val remaining = info.startTime - now
                timeTextSmall =  type.toStateMessage(remaining, true)
                timeTextLarge = type.toStateMessage(remaining, false)
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        savedStateRegistryController.performRestore(null)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        isRunning = true
        _instance = WeakReference(this)
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        setupComposeView()
    }

    private fun setupComposeView() {
        composeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@DynamicIslandService)
            setViewTreeSavedStateRegistryOwner(this@DynamicIslandService)
            setViewTreeViewModelStoreOwner(this@DynamicIslandService)

            setContent {
                DynamicIslandScreen(
                    isExpanded = isExpanded,
                    smallText = timeTextSmall,
                    largeText = timeTextLarge,
                    info = islandInfo,
                    onExpand = { expandWindow() },
                    onCollapse = { collapseWindow() },
                    onLinkClick = { executeLandingLink() }
                )
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        val info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(
                DynamicIslandConst.DYNAMIC_ISLAND_INFO,
                DynamicIslandInfo::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent?.getParcelableExtra(DynamicIslandConst.DYNAMIC_ISLAND_INFO)
        } ?: return START_NOT_STICKY

        reset(info)

        if (composeView?.parent == null) {
            showDynamicIsland()
        }

        return START_STICKY
    }

    fun reset(info: DynamicIslandInfo?) {
        if (info == null) return
        this.islandInfo = info
        this.type = info.type.toType()

        handler.removeCallbacks(checkRunnable)
        handler.post(checkRunnable)
    }

    private fun showDynamicIsland() {
        this.params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            y = 10
        }
        windowManager?.addView(composeView, params)
    }

    // WindowManager Layout Params 업데이트 로직
    private fun expandWindow() {
        if (isExpanded) return
        // 1. Window 크기를 먼저 키움 (터치 영역 확보)
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        windowManager?.updateViewLayout(composeView, params)
        // 2. Compose 상태 변경 (애니메이션 시작)
        isExpanded = true
    }

    private fun collapseWindow() {
        if (!isExpanded) return
        // 1. Compose 상태 변경 (애니메이션 시작)
        isExpanded = false
        // 2. 애니메이션이 끝날 즈음에 Window 크기 줄임 (Composable 내부의 SideEffect나 Timer로 처리 필요하지만, 여기서는 딜레이로 처리)
        handler.postDelayed({
            if (!isExpanded) { // 상태가 다시 바뀌지 않았다면
                params?.height = WindowManager.LayoutParams.WRAP_CONTENT
                windowManager?.updateViewLayout(composeView, params)
            }
        }, 400) // 애니메이션 시간과 맞춤
    }

    private fun executeLandingLink() {
        val link = islandInfo?.link
        if (link.isNullOrEmpty()) return

        val uri = Uri.parse(link)
        val launchIntent = Intent(Intent.ACTION_VIEW, uri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(launchIntent)
    }

    private fun removeDynamicIsland() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                stopForeground(STOP_FOREGROUND_DETACH)
            } else {
                @Suppress("DEPRECATION")
                stopForeground(true)
            }
            stopSelf()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        isRunning = false
        _instance = null
        handler.removeCallbacks(checkRunnable)
        composeView?.let {
            if (it.parent != null) windowManager?.removeView(it)
        }
        composeView = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

