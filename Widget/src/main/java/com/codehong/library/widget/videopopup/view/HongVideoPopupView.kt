package com.codehong.library.widget.videopopup.view

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
import com.codehong.library.widget.databinding.HonglibViewVideoPopupBinding
import com.codehong.library.widget.util.applyRoundBackground

class HongVideoPopupView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        HonglibViewVideoPopupBinding.inflate(LayoutInflater.from(context), this, true)

    private val accelerate =
        AnimationUtils.loadInterpolator(context, R.anim.honglib_default_interpolator)

    private var isAnimating = false

    private var listener: HongVideoPopupListener? = null

    init {
        this.visibility = View.GONE

        binding.llContent.doOnLayout {
            binding.llContent.translationY = binding.llContent.height.toFloat()
        }
    }

    fun set(
        radiusDp: Int = 0,
        blockTouchOutside: Boolean = true,
        listener: HongVideoPopupListener?
    ) {
        this.listener = listener

        binding.vDim.setOnClickListener {
            if (!blockTouchOutside) {
                dismiss()
            }
        }

        binding.vVideoContainer.applyRoundBackground(
            color = R.color.honglib_color_ffffff,
            radiusDp = radiusDp,
            topLeft = true,
            topRight = true
        )
        with(binding.tvNoShow) {
            this.text = "오늘은 그만 보기"
            setOnClickListener {
                dismiss(false)
            }
        }

        binding.tvClose.setOnClickListener {
            dismiss()
        }

        binding.vVideoContainer.set(
            radiusDp = radiusDp,
            topLeft = true,
            topRight = true,
            onReady = {
                Handler(Looper.getMainLooper()).postDelayed({
                    showView()
                }, 50)
            },
            onEnd = {
                dismiss()

            },
            onError = {
                dismiss()
            }
        )
    }

    fun show(
        videoUrl: String?,
        ratio: String?,
        landingLink: String?,
        landing: ((String?) -> Unit)? = null
    ) {
        if (visibility == View.VISIBLE
            || isAnimating
        ) {
            return
        }
        if (videoUrl.isNullOrEmpty()) {
            return
        }
        if (isShow()) {
            return
        }

        binding.vVideoContainer.play(
            playerUrl = videoUrl,
            ratio = ratio
        )
        setOnClickListener {
            if (!landingLink.isNullOrEmpty()) {
                landing?.invoke(landingLink)
                dismiss()
            }
        }
    }

    private fun showView() {
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
        listener?.onShow()
    }

    fun dismiss(isClickClose: Boolean = true) {
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
                binding.vVideoContainer.clearPlayer()
            }
            .start()

        listener?.onHide(isClickClose)
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
        return isRootVisible && binding.vVideoContainer.isShowPlayer
    }

    fun checkVisible(): Boolean {
        if (isShow()) {
            dismiss()
            return true
        }
        return false
    }
}