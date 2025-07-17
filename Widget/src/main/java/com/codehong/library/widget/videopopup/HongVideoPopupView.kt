package com.codehong.library.widget.videopopup

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
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout

class HongVideoPopupView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        HonglibViewVideoPopupBinding.inflate(LayoutInflater.from(context), this, true)

    var option = HongVideoPopupOption()
        private set

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
    ): HongVideoPopupView {
        this.option = option

        binding.vDim.setOnClickListener {
            if (!option.blockTouchOutside) {
                dismiss(
                    true,
                    onHide
                )
            }
        }

        with(binding.vVideoContainer) {
            setLayout(
                option.width,
                option.height
            )?.apply {
                this.leftMargin = context.dpToPx(option.margin.left)
                this.topMargin = context.dpToPx(option.margin.top)
                this.rightMargin = context.dpToPx(option.margin.right)
                this.bottomMargin = context.dpToPx(option.margin.bottom)
            }

            hongBackground(
                backgroundColor = HongColor.WHITE_100.hex,
                radius = option.videoPlayerOption.radius
            )
            hongPadding(option.padding)

            set(
                option = option.videoPlayerOption,
                onReady = {
                    Handler(Looper.getMainLooper()).postDelayed({
                        showView(onShow)
                    }, 50)
                },
                onEnd = {
                    dismiss(true, onHide)

                },
                onError = {
                    dismiss(true, onHide)
                }
            )
        }

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
        if (option.videoPlayerOption.videoUrl.isNullOrEmpty()) {
            return
        }
        if (isShow()) {
            return
        }

        binding.vVideoContainer.play()
        binding.vVideoContainer.setOnClickListener {
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
                binding.vVideoContainer.clearPlayer()
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
        return isRootVisible && binding.vVideoContainer.isShowPlayer
    }

    fun checkVisible(): Boolean {
        return isShow()
    }
}