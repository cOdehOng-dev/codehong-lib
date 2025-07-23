package com.codehong.library.widget.button.select

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.frameLayout
import com.codehong.library.widget.language.hongTextButton
import com.codehong.library.widget.rule.HongLayoutParam

class HongSelectButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
    }

    var option = HongSelectButtonOption()
        private set

    fun set(
        option: HongSelectButtonOption
    ): HongSelectButtonView {
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
                    HongTextButtonBuilder()
                        .copy(option.negativeTextButtonOption)
                        .onClick {
                            option.negativeClick?.invoke()
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
                    HongTextButtonBuilder()
                        .copy(option.positiveTextButtonOption)
                        .onClick {
                            option.positiveClick?.invoke()
                        }
                        .applyOption()
                )
            }
        }
        return this
    }
}