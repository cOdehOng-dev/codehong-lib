package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.codehong.library.widget.Consts
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.label.def.HongLabelBuilder
import com.codehong.library.widget.language.frameLayout
import com.codehong.library.widget.language.hongLabel
import com.codehong.library.widget.language.horizontalLinearLayout
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.parseColor

class HorizontalOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }


    fun set(
        leftOptionView: View?,
        rightOptionView: View?,
        label: String = "",
        description: String = "",
        useTopPadding: Boolean = true,
    ): HorizontalOptionView {
        removeAllViews()

        setBackgroundColor(HongColor.WHITE_100.parseColor())

        setPadding(
            paddingLeft,
            if (useTopPadding) context.dpToPx(Consts.PLAYGROUND_TOP_PADDING) else 0,
            paddingRight,
            paddingBottom
        )

        hongLabel {
            set(
                HongLabelBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .height(HongLayoutParam.WRAP_CONTENT.value)
                    .label(label)
                    .description(description)
                    .applyOption()
            )
        }

        horizontalLinearLayout {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            leftOptionView?.let { leftView ->
                frameLayout {
                    layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                        weight = 1f
                        marginEnd = context.dpToPx(5f)
                    }
                    addView(leftView)
                }
            }

            rightOptionView?.let { rightView ->
                frameLayout {
                    layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                        weight = 1f
                        marginStart = context.dpToPx(5f)
                    }
                    addView(rightView)
                }
            }
        }

        return this
    }
}