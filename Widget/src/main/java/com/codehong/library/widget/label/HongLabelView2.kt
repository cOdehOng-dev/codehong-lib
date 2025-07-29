package com.codehong.library.widget.label

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextView

class HongLabelView2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
    }

    var option = HongLabelOption2()
        private set
    
    fun set(
        option: HongLabelOption2
    ): HongLabelView2 {
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
        hongBackground(option.backgroundColorHex)

        if (option.label.isNullOrEmpty()) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.VISIBLE
            this.addView(
                HongTextView(context).set(
                    HongTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .text(option.label)
                        .typography(option.labelTypo)
                        .color(option.labelColorHex)
                        .applyOption()
                )
            )
            if (!option.description.isNullOrEmpty()) {
                this.addView(
                    HongTextView(context).set(
                        HongTextBuilder()
                            .width(HongLayoutParam.MATCH_PARENT.value)
                            .margin(
                                HongSpacingInfo(
                                    top = 2f
                                )
                            )
                            .text(option.description)
                            .typography(option.descriptionTypo)
                            .color(option.descriptionColorHex)
                            .applyOption()
                    )
                )
            }
        }
        return this
    }
}