package com.codehong.library.widget.checkbox

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.parseColor
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.color.HongColor

class HongCheckboxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var checkMark = AppCompatImageView(context)
    private var isChecked: Boolean = false
    private var isEnabled = false
    private var option: HongCheckboxOption = HongCheckboxOption()

    init {
        addView(checkMark)
        gravity = Gravity.CENTER
        setOnClickListener {
            if (option.isEnabled) {
                toggle()
            }
        }
    }

    fun set(
        option: HongCheckboxOption
    ): HongCheckboxView {
        this.option = option
        this.isChecked = option.checkState
        this.isEnabled = option.isEnabled
        buildCheckBox()
        return this
    }

    private fun buildCheckBox() {
        val backgroundColor = when {
            !isEnabled -> HongColor.GRAY_20.hex
            isChecked -> option.checkedColorHex
            else -> HongColor.TRANSPARENT.hex
        }

        val borderColor = when {
            !isEnabled -> HongColor.GRAY_20.hex
            isChecked -> option.checkedColorHex
            else -> option.border.color
        }

        setLayout(
            width = option.size,
            height = option.size,
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }

        hongBackground(
            backgroundColor = backgroundColor,
            border = HongBorderInfo(
                width = option.border.width,
                color = borderColor
            ),
            radius = option.radius
        )

        checkMark.setImageResource(R.drawable.honglib_ic_24_check)
        if (isChecked) {
            checkMark.visibility = View.VISIBLE
            checkMark.setColorFilter(option.checkmarkColorHex.parseColor())
        } else if (!isEnabled) {
            checkMark.setColorFilter(HongColor.GRAY_40.hex.parseColor())
        } else {
            checkMark.visibility = View.INVISIBLE
        }

        val checkSize = context.dpToPx(option.size * 0.9f)
        checkMark.layoutParams = LayoutParams(checkSize, checkSize).apply {
            gravity = Gravity.CENTER
        }
    }

    fun toggle() {
        this.isChecked = !isChecked
        buildCheckBox()
    }

    fun setChecked(value: Boolean) {
        this.isChecked = value
        buildCheckBox()
    }

    fun isChecked(): Boolean = isChecked
}
