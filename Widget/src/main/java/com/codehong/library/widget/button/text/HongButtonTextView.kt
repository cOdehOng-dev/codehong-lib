package com.codehong.library.widget.button.text

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.hongText
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

class HongButtonTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    var option = HongButtonTextOption()
        private set

    fun set(
        option: HongButtonTextOption
    ): HongButtonTextView {
        removeAllViews()
        this.option = option

        setOnClickListener {
            option.click?.invoke(option)
        }

        setLayout(
            option.width,
            option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }

        if (option.state == HongState.DISABLED) {
            this.isEnabled = false
            hongBackground(
                backgroundColor = HongButtonTextOption.DEFAULT_DISABLE_BACKGROUND_COLOR.hex,
                useShapeCircle = option.useShapeCircle,
                radius = option.radius,
            )
            hongText {
                set(
                    HongTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .text(option.text)
                        .typography(HongTypo.BODY_15_B)
                        .color(HongColor.WHITE_60.hex)
                        .textAlign(HongTextAlign.CENTER)
                        .maxLines(1)
                        .applyOption()
                )
            }
        } else {
            this.isEnabled = true
            hongBackground(
                border = option.border,
                radius = option.radius,
                backgroundColor = option.backgroundColorHex,
                useShapeCircle = option.useShapeCircle,
            )

            hongText {
                set(
                    HongTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .text(option.text)
                        .typography(option.textTypo)
                        .color(option.textColorHex)
                        .textAlign(HongTextAlign.CENTER)
                        .maxLines(1)
                        .applyOption()
                )
            }
        }



        return this
    }
}