package com.codehong.library.widget.label.input

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.hongLabel
import com.codehong.library.widget.language.hongTextField
import com.codehong.library.widget.rule.HongSpacingInfo
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
        this.option = option

        removeAllViews()

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


        if (!option.labelOption.labelTextOption.text.isNullOrEmpty()) {
            hongLabel {
                set(option.labelOption)
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