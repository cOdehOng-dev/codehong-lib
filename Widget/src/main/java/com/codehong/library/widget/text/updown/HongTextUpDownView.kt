package com.codehong.library.widget.text.updown

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.extensions.toFigureStringCoverZero
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.language.frameLayout
import com.codehong.library.widget.language.hongImage
import com.codehong.library.widget.language.hongTextUnit
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.unit.HongTextUnitBuilder
import com.codehong.library.widget.text.unit.HongTextUnitOption

class HongTextUpDownView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    var option = HongTextUpDownOption()
        private set

    private var amount: Int = 0

    private var amountView: FrameLayout? = null


    fun set(
        option: HongTextUpDownOption
    ): HongTextUpDownView {
        this.option = option

        this.amount = option.amount

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

        hongImage {
            set(
                HongImageBuilder()
                    .width(option.buttonSize)
                    .height(option.buttonSize)
                    .border(
                        HongBorderInfo(
                            width = 1,
                            color = option.borderColorHex
                        )
                    )
                    .imageInfo(R.drawable.honglib_ic_minus)
                    .onClick {
                        amount -= option.gap
                        option.onResult(amount)

                        amountView?.removeAllViews()
                        amountView?.hongTextUnit {
                            set(getDisplayTextUnitOption(amount))
                        }
                    }
                    .useShapeCircle(true)
                    .scaleType(HongScaleType.CENTER_CROP)
                    .imageColor(option.iconColorHex)
                    .applyOption()
            )
        }

        frameLayout {
            amountView = this
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    marginStart = context.dpToPx(option.spaceButtonAndDisplay)
                    marginEnd = context.dpToPx(option.spaceButtonAndDisplay)
                }

            hongTextUnit {
                set(getDisplayTextUnitOption(amount))
            }
        }

        hongImage {
            set(
                HongImageBuilder()
                    .width(option.buttonSize)
                    .height(option.buttonSize)
                    .border(
                        HongBorderInfo(
                            width = 1,
                            color = option.borderColorHex
                        )
                    )
                    .imageInfo(R.drawable.honglib_ic_plus)
                    .onClick {
                        amount += option.gap
                        option.onResult(amount)

                        amountView?.removeAllViews()
                        amountView?.hongTextUnit {
                            set(getDisplayTextUnitOption(amount))
                        }
                    }
                    .useShapeCircle(true)
                    .scaleType(HongScaleType.CENTER_CROP)
                    .imageColor(option.iconColorHex)
                    .applyOption()
            )
        }

        return this
    }

    private fun getDisplayTextUnitOption(amount: Int): HongTextUnitOption {
        return HongTextUnitBuilder()
            .width(HongLayoutParam.WRAP_CONTENT.value)
            .height(HongLayoutParam.WRAP_CONTENT.value)
            .textOption(
                HongTextBuilder()
                    .width(HongLayoutParam.WRAP_CONTENT.value)
                    .height(HongLayoutParam.WRAP_CONTENT.value)
                    .text(amount.toFigureStringCoverZero())
                    .typography(option.displayTypo)
                    .color(option.displayColorHex)
                    .applyOption()
            )
            .useNumberDecimal(option.useDecimal)
            .unitText(option.unit)
            .applyOption()
    }
}