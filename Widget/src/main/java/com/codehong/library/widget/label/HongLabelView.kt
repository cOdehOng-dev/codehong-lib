package com.codehong.library.widget.label

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.text.HongTextView

class HongLabelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
    }

    private var option = HongLabelOption()

    fun set(
        option: HongLabelOption
    ): HongLabelView {
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
        hongPadding(option.padding)
        hongBackground(option.backgroundColorHex)

        if (option.labelTextOption.text.isNullOrEmpty()) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.VISIBLE
            this.addView(
                HongTextView(context).set(option.labelTextOption)
            )
            if (!option.descriptionTextOption.text.isNullOrEmpty()) {
                this.addView(
                    HongTextView(context).set(option.descriptionTextOption)
                )
            }
        }
        return this
    }
}