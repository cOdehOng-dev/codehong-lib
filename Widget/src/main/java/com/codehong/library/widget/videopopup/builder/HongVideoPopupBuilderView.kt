package com.codehong.library.widget.videopopup.builder

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import com.codehong.library.widget.R
import com.codehong.library.widget.databinding.HonglibViewVideoPopupBuilderBinding
import com.codehong.library.widget.player.builder.HongVideoPlayerBuilderView
import com.codehong.library.widget.player.builder.HongVideoPlayerOption
import com.codehong.library.widget.util.applyRoundBackground

class HongVideoPopupBuilderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        HonglibViewVideoPopupBuilderBinding.inflate(LayoutInflater.from(context), this, true)

    var option = HongVideoPopupOption()
        private set
    private var builderView: HongVideoPlayerBuilderView? = null

    private val accelerate =
        AnimationUtils.loadInterpolator(context, R.anim.honglib_default_interpolator)

    private var isAnimating = false

    init {
        this.visibility = View.GONE

        binding.llContent.doOnLayout {
            binding.llContent.translationY = binding.llContent.height.toFloat()
        }
    }

    fun set(
        option: HongVideoPopupOption,
        onShow: () -> Unit = {},
        onHide: (Boolean) -> Unit = {},
    ): HongVideoPopupBuilderView {
        this.option = option

        binding.vDim.setOnClickListener {
            if (!(option.blockTouchOutside ?: HongVideoPopupOption.DEFAULT_BLOCK_TOUCH_OUTSIDE)) {
                dismiss(
                    true,
                    onHide
                )
            }
        }

        binding.flVideoPlayer.applyRoundBackground(
            color = R.color.honglib_color_ffffff,
            radiusDp = option.radiusDp ?: HongVideoPopupOption.DEFAULT_RADIUS_DP,
            topLeft = option.topLeft ?: HongVideoPopupOption.DEFAULT_SET_TOP_LEFT_RADIUS,
            topRight = option.topRight ?: HongVideoPopupOption.DEFAULT_SET_TOP_RIGHT_RADIUS,
        )

        val property = HongVideoPlayerOption.Builder()
            .setPlayerUrl(option.videoUrl)
            .setRadiusDp(option.radiusDp)
            .setRatio(option.ratio)
            .build()

        this.builderView = HongVideoPlayerBuilderView(context).set(
            option = property,
            onReady = {
                Handler(Looper.getMainLooper()).postDelayed({
                    showView(onShow)
                }, 50)
            },
            onEnd = {
                dismiss(
                    true,
                    onHide
                )

            },
            onError = {
                dismiss(
                    true,
                    onHide
                )
            }
        )

        binding.flVideoPlayer.addView(builderView)

        with(binding.tvNoShow) {
            this.text = "오늘은 그만 보기"
            setOnClickListener {
                dismiss(
                    false,
                    onHide
                )
            }
        }

        binding.tvClose.setOnClickListener {
            dismiss(
                true,
                onHide
            )
        }

        return this
    }

    fun show(
        onHide: (Boolean) -> Unit = {},
        landing: ((String?) -> Unit)? = null,
    ) {
        if (visibility == View.VISIBLE
            || isAnimating
        ) {
            return
        }
        if (option.videoUrl.isNullOrEmpty()) {
            return
        }
        if (isShow()) {
            return
        }

        builderView?.play()
        setOnClickListener {
            if (!option.landingLink.isNullOrEmpty()) {
                landing?.invoke(option.landingLink)
                dismiss(
                    true,
                    onHide
                )
            }
        }
    }

    private fun showView(
        onShow: () -> Unit = {}
    ) {
        this.visibility = View.VISIBLE
        this.isAnimating = true

        binding.vDim.alpha = 0f
        binding.vDim.animate()
            .alpha(1f)
            .setDuration(150)
            .setInterpolator(accelerate)
            .withEndAction {
                binding.llContent.translationY = binding.llContent.height.toFloat()
                binding.llContent.animate()
                    .translationY(0f)
                    .setDuration(250)
                    .setInterpolator(accelerate)
                    .withEndAction {
                        isAnimating = false
                    }
                    .start()
            }
            .start()
        onShow()
    }

    fun dismiss(
        isClickClose: Boolean,
        onHide: (Boolean) -> Unit = {},
    ) {
        if (visibility != View.VISIBLE
            || isAnimating
        ) {
            return
        }
        this.isAnimating = true

        binding.llContent.animate()
            .translationY(binding.llContent.height.toFloat())
            .setDuration(250)
            .setInterpolator(accelerate)
            .withEndAction {
                builderView?.clearPlayer()
            }
            .start()

        onHide(isClickClose)
        binding.vDim.animate()
            .alpha(0f)
            .setDuration(300)
            .setInterpolator(accelerate)
            .withEndAction {
                visibility = View.GONE
                isAnimating = false
            }
            .start()
    }

    fun isShow(): Boolean {
        val isRootVisible = this.visibility == View.VISIBLE
        return isRootVisible && (builderView?.option?.isShowPlayer == true)
    }

    fun checkVisible(): Boolean {
        return isShow()
    }
}