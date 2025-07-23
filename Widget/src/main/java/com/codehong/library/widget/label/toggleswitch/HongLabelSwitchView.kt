package com.codehong.library.widget.label.toggleswitch

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.frameLayout
import com.codehong.library.widget.language.hongLabel
import com.codehong.library.widget.language.hongSwitch

class HongLabelSwitchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    var option: HongLabelSwitchOption = HongLabelSwitchOption()
        private set

    fun set(
        option: HongLabelSwitchOption
    ): HongLabelSwitchView {
        removeAllViews()

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
        hongBackground(
            backgroundColor = option.backgroundColorHex
        )
        hongPadding(option.padding)

        frameLayout {
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                weight = 1f
                marginEnd = context.dpToPx(5f)
            }

            hongLabel {
                this.set(option.labelOption)
            }
        }

        hongSwitch {
            set(option.switchOption)
        }
        return this
    }
}
