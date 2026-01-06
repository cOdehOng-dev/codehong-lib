package com.codehong.library.widget.button.select

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.frameLayout
import com.codehong.library.widget.language.hongTextButton
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongButtonSelectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
    }

    var option = HongButtonSelectOption()
        private set

    fun set(
        option: HongButtonSelectOption
    ): HongButtonSelectView {
        removeAllViews()

        this.option = option

        setLayout(
            width = HongLayoutParam.MATCH_PARENT.value,
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
        )

        frameLayout {
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                weight = 1f
                marginEnd = context.dpToPx(5f)
            }

            hongTextButton {
                set(
                    HongButtonTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .height(48)
                        .padding(
                            HongSpacingInfo(
                                top = 8f,
                                bottom = 8f
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
                        .border(
                            HongBorderInfo(
                                width = 1,
                                color = option.negativeBorderColorHex
                            )
                        )
                        .text(option.negativeText)
                        .textTypo(option.negativeTextTypo)
                        .textColor(option.negativeTextColorHex)
                        .onClick {
                            option.onClickNegative?.invoke()
                        }
                        .applyOption()
                )
            }
        }

        frameLayout {
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                weight = 1f
                marginStart = context.dpToPx(5f)
            }

            hongTextButton {
                set(
                    HongButtonTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .height(48)
                        .padding(
                            HongSpacingInfo(
                                top = 8f,
                                bottom = 8f
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
                        .text(option.positiveText)
                        .textTypo(option.positiveTextTypo)
                        .textColor(option.positiveTextColorHex)
                        .backgroundColor(option.positiveBackgroundColorHex)
                        .onClick {
                            option.onClickPositive?.invoke()
                        }
                        .applyOption()
                )
            }
        }
        return this
    }
}