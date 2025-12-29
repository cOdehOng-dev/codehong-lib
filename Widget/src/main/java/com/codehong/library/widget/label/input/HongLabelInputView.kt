package com.codehong.library.widget.label.input

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.label.def.HongLabelBuilder
import com.codehong.library.widget.language.hongLabel
import com.codehong.library.widget.language.hongTextField
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
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
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .height(48)
                    .margin(
                        HongSpacingInfo(
                            top = 10f
                        )
                    )
                    .radius(
                        HongRadiusInfo(
                            topLeft = 10,
                            topRight = 10,
                            bottomLeft = 10,
                            bottomRight = 10
                        )
                    )
                    .padding(
                        HongSpacingInfo(
                            top = 11f,
                            bottom = 11f,
                            left = 10f,
                            right = 10f
                        )
                    )
                    .input(option.input)
                    .inputTypo(option.inputTypo)
                    .inputColor(option.inputColorHex)
                    .placeholderPadding(
                        HongSpacingInfo(
                            top = 4f,
                            bottom = 4f,
                            left = 4f,
                            right = 4f
                        )
                    )
                    .placeholder(option.placeholder)
                    .placeholderTypo(option.placeholderTypo)
                    .placeholderColor(option.placeholderColorHex)
                    .clearIconSize(option.clearIconSize)
                    .clearIconRes(option.clearIconRes)
                    .clearIconScaleType(option.clearIconScaleType)
                    .clearIconMargin(option.clearIconMargin)
                    .keyboardOption(option.keyboardOption)
                    .onTextChanged(option.onTextChanged)
                    .applyOption()
            )
        }
        return this
    }

}