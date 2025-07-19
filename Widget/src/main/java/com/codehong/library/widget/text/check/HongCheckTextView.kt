package com.codehong.library.widget.text.check

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.parseColor
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.hongText

class HongCheckTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var option = HongCheckTextOption()
        private set

    private var checkMark = AppCompatImageView(context)
    private var isChecked: Boolean = false

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        setOnClickListener {
            this.isChecked = !isChecked
            update()
        }
        addView(checkMark)
    }

    fun set(
        option: HongCheckTextOption
    ): HongCheckTextView {
        this.option = option
        this.isChecked = option.checkState

        setLayout(
            width = option.width,
            height = option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }
        hongPadding(option.padding)
        hongBackground(
            backgroundColor = option.backgroundColorHex,
        )

        val checkSize = context.dpToPx(option.size * 0.9f)
        checkMark.layoutParams = LayoutParams(checkSize, checkSize).apply {
            gravity = Gravity.CENTER
            marginEnd = context.dpToPx(10)
        }
        checkMark.setImageResource(R.drawable.honglib_ic_24_check)

        update()

        hongText {
            set(option.textOption)
        }

        return this

    }

    fun update() {
        if (isChecked) {
            checkMark.setColorFilter(option.checkColorHex.parseColor())
        } else {
            checkMark.setColorFilter(option.uncheckColorHex.parseColor())
        }

    }
}