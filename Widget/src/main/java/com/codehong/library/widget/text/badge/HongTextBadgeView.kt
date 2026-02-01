package com.codehong.library.widget.text.badge

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.hongText
import com.codehong.library.widget.text.def.HongTextBuilder

class HongTextBadgeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var option = HongTextBadgeOption()
        private set

    fun set(option: HongTextBadgeOption): HongTextBadgeView {
        this.option = option
        applyLayout()
        applyBackground()
        applyText()
        return this
    }

    private fun applyLayout() {
        setLayout(option.width, option.height)?.apply {
            leftMargin = context.dpToPx(option.margin.left)
            topMargin = context.dpToPx(option.margin.top)
            rightMargin = context.dpToPx(option.margin.right)
            bottomMargin = context.dpToPx(option.margin.bottom)
        }
        hongPadding(option.padding)
    }

    private fun applyBackground() {
        hongBackground(
            backgroundColor = option.backgroundColorHex,
            border = option.border,
            radius = option.radius
        )
    }

    private fun applyText() {
        hongText {
            set(
                option = HongTextBuilder()
                    .width(option.width)
                    .height(option.height)
                    .text(option.text)
                    .color(option.textColorHex)
                    .typography(option.textTypography)
                    .maxLines(1)
                    .applyOption()
            )
        }
    }
}