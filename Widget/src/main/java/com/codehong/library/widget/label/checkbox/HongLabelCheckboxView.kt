package com.codehong.library.widget.label.checkbox

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.language.frameLayout
import com.codehong.library.widget.language.hongCheckbox
import com.codehong.library.widget.language.hongLabel
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongPosition
import com.codehong.library.widget.rule.color.HongColor

class HongLabelCheckboxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    var option = HongLabelCheckboxOption()
        private set

    fun set(
        option: HongLabelCheckboxOption
    ): HongLabelCheckboxView {
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
        hongPadding(option.padding)
        hongBackground(
            backgroundColor = option.backgroundColorHex
        )

        if (option.checkboxPosition == HongPosition.LEFT) {
            checkbox()
        }

        frameLayout {
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                weight = 1f
                if (option.checkboxPosition == HongPosition.LEFT) {
                    marginStart = context.dpToPx(HongLabelCheckboxOption.DEFAULT_BETWEEN_SPACER)
                } else {
                    marginEnd = context.dpToPx(HongLabelCheckboxOption.DEFAULT_BETWEEN_SPACER)
                }
            }

            hongLabel {
                set(
                    HongLabelBuilder()
                        .width(HongLayoutParam.WRAP_CONTENT.value)
                        .backgroundColor(HongColor.TRANSPARENT)
                        .label(option.label)
                        .labelColor(option.labelColorHex)
                        .labelTypo(option.labelTypo)
                        .description(option.description)
                        .descriptionColor(option.descriptionColorHex)
                        .descriptionTypo(option.descriptionTypo)
                        .applyOption()
                )
            }
        }

        if (option.checkboxPosition != HongPosition.LEFT) {
            checkbox()
        }

        return this
    }

    private fun checkbox() {
        hongCheckbox {
            set(this@HongLabelCheckboxView.option.checkboxOption)
        }
    }
}