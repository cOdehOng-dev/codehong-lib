package com.codehong.library.widget.label.input

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.language.hongLabel
import com.codehong.library.widget.language.hongTextField
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.textfield.HongTextFieldBuilder

class HongLabelInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
    }

    var option = HongLabelInputOption()
        private set

    fun set(
        option: HongLabelInputOption
    ): HongLabelInputView {
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


        if (!option.label.isNullOrEmpty()) {
            hongLabel {
                set(
                    HongLabelBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
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

        hongTextField {
            set(
                HongTextFieldBuilder()
                    .copy(option.textFieldOption)
                    .margin(
                        HongSpacingInfo(
                            top = 10f
                        )
                    )
                    .applyOption()
            )
        }
        return this
    }

}