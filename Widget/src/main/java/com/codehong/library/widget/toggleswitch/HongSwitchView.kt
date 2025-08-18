package com.codehong.library.widget.toggleswitch

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout

class HongSwitchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var option = HongSwitchOption()
    private val cursorView = View(context)
    private var isOn = false


    fun set(
        option: HongSwitchOption
    ): HongSwitchView {
        this.option = option
        this.isOn = option.initialState
        setLayout(
            option.width,
            option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }
        hongPadding(option.padding)
        this.cursorView.apply {
            setLayout(
                width = option.cursorSize,
                height = option.cursorSize
            )?.apply {
                this.leftMargin = context.dpToPx(option.cursorHorizontalMargin)
                this.rightMargin = context.dpToPx(option.cursorHorizontalMargin)
            }
            hongBackground(
                backgroundColor = option.cursorColorHex,
                useShapeCircle = true
            )
            this@HongSwitchView.addView(this)
        }
        updateOnOffState(isOn)

        this.setOnClickListener {
            this.isOn = !isOn
            val fromX = 0f
            val toX = if (isOn) {
                this.width - cursorView.width - context.dpToPx(option.cursorHorizontalMargin) * 2
            } else {
                -(this.width - cursorView.width - context.dpToPx(option.cursorHorizontalMargin) * 2)
            }

            val ani = TranslateAnimation(fromX, toX.toFloat(), 0f, 0f).apply {
                duration = 200
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        cursorView.clearAnimation()
                        updateOnOffState(isOn)
                        option.switchClick?.invoke(option, isOn)
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
            cursorView.startAnimation(ani)
        }
        return this
    }

    private fun updateOnOffState(isOn: Boolean) {
        hongBackground(
            backgroundColor = if (isOn) option.onColorHex else option.offColorHex,
            radius = HongRadiusInfo(100, 100, 100, 100)
        )
        updateCursorView(isOn)
    }

    private fun updateCursorView(isOn: Boolean) {
        if (isOn) {
            val params = LayoutParams(
                cursorView.layoutParams.width,
                cursorView.layoutParams.height
            ).apply {
                gravity = Gravity.END or Gravity.CENTER_VERTICAL
                leftMargin = context.dpToPx(option.cursorHorizontalMargin)
                rightMargin = context.dpToPx(option.cursorHorizontalMargin)
            }
            cursorView.layoutParams = params
        } else {
            val params = LayoutParams(
                cursorView.layoutParams.width,
                cursorView.layoutParams.height
            ).apply {
                gravity = Gravity.START or Gravity.CENTER_VERTICAL
                leftMargin = context.dpToPx(option.cursorHorizontalMargin)
                rightMargin = context.dpToPx(option.cursorHorizontalMargin)
            }
            cursorView.layoutParams = params
        }
    }
}
