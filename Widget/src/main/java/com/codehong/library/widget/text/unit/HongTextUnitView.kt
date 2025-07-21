package com.codehong.library.widget.text.unit

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.hongText
import com.codehong.library.widget.text.HongTextBuilder

class HongTextUnitView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    var option = HongTextUnitOption()
        private set

    fun set(
        option: HongTextUnitOption
    ): HongTextUnitView {
        this.option = option

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
        hongBackground(
            backgroundColor = option.backgroundColorHex,
            border = option.border,
            radius = option.radius
        )

        hongText {
            set(option.textOption)
        }

        hongText {
            set(
                HongTextBuilder()
                    .copy(option.unitTextOption)
                    .applyOption()
            )
        }

        return this
    }

}