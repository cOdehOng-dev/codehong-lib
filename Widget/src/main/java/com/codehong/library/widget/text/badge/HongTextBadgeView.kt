package com.codehong.library.widget.text.badge

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.hongText
import com.codehong.library.widget.text.HongTextBuilder

class HongTextBadgeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var option = HongTextBadgeOption()
        private set

    fun set(
        option: HongTextBadgeOption
    ): HongTextBadgeView {
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
            set(
                HongTextBuilder()
                    .copy(option.textOption)
                    .maxLines(1)
                    .applyOption()
            )
        }
        return this
    }

}