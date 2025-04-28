package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.codehong.lib.sample.R
import com.codehong.lib.sample.databinding.ViewToggleBinding
import com.codehong.library.widget.util.Utils

class ToggleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewToggleBinding
        .inflate(LayoutInflater.from(context), this, true)

    private var listener: OnOnOffChangedListener? = null
    private val cursorMargin = Utils.dpToPx(context, 3)
    var isOn = false
        private set

    private var isChanging = false

    fun setOnOff(isOn: Boolean, isListener: Boolean = true) {
        if (!isChanging && isOn != this.isOn) {
            this.isChanging = true
            this.isOn = isOn

            val params = binding.vOnOffCursor.layoutParams as LayoutParams
            val marginHorizontal = params.marginStart + params.marginEnd
            var bgWidth = width - marginHorizontal - binding.vOnOffCursor.width

            if (!isOn) {
                bgWidth = -bgWidth
            }

            val ani = TranslateAnimation(
                0f,
                bgWidth.toFloat(),
                0f,
                0f
            ).apply {
                duration = 200
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        setOnOffChanged(isOn, isListener)
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }

            binding.vOnOffCursor.startAnimation(ani)
        }
    }

    private fun setOnOffChanged(isOn: Boolean, isListener: Boolean) {
        this.isChanging = false

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)

        if (isOn) {
            constraintSet.connect(
                binding.vOnOffCursor.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END
            )
            constraintSet.setMargin(binding.vOnOffCursor.id, ConstraintSet.END, cursorMargin)
            constraintSet.clear(binding.vOnOffCursor.id, ConstraintSet.START)
            binding.clOnOff.setBackgroundResource(R.drawable.rect_6200ee_radius_100)
        } else {
            constraintSet.connect(
                binding.vOnOffCursor.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START
            )
            constraintSet.setMargin(binding.vOnOffCursor.id, ConstraintSet.START, cursorMargin)
            constraintSet.clear(binding.vOnOffCursor.id, ConstraintSet.END)
            binding.clOnOff.setBackgroundResource(R.drawable.rect_c7c7c7_radius_100)
        }

        constraintSet.applyTo(binding.root)
        binding.vOnOffCursor.clearAnimation()

        if (listener != null && isListener) {
            listener!!.onOnOffChanged(this.isOn)
        }
    }

    fun setOnOnOffListener(listener: OnOnOffChangedListener?) {
        this.listener = listener
    }

    interface OnOnOffChangedListener {
        fun onOnOffChanged(isOn: Boolean)
    }
}
