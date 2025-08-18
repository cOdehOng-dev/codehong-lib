package com.codehong.library.widget.header

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.codehong.library.widget.R
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageView
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextView
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.setLayout

class HongCloseHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var option = HongCloseHeaderOption()
        private set

    init {
        orientation = HORIZONTAL
        setLayout(
            LayoutParams.MATCH_PARENT,
            58
        )
    }

    fun set(
        option: HongCloseHeaderOption
    ): HongCloseHeaderView {
        this.option = option

        hongBackground(
            backgroundColor = option.backgroundColorHex
        )

        this.gravity = Gravity.CENTER_VERTICAL

        this.addView(
            View(context).apply {
                setLayout(
                    40,
                    40
                )
            }
        )

        this.addView(
            LinearLayout(context).apply {
                orientation = HORIZONTAL
                layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                ).apply {
                    this.weight = 1f
                }
                gravity = Gravity.CENTER
            }.apply {
                this.addView(
                    HongTextView(context).set(
                        HongTextBuilder()
                            .copy(option.headerTitleTextOption)
                            .maxLines(1)
                            .overflow(HongTextOverflow.ELLIPSIS)
                            .applyOption()
                    )
                )
            }
        )

        this.addView(
            LinearLayout(context).apply {
                setLayout(
                    40,
                    40
                )
                setOnClickListener { option.close?.invoke() }
                gravity = Gravity.CENTER
            }.apply {
                this.addView(
                    HongImageView(context).set(
                        HongImageBuilder()
                            .width(20)
                            .height(20)
                            .scaleType(HongScaleType.CENTER_CROP)
                            .drawableResId(R.drawable.honglib_ic_close)
                            .useShapeCircle(true)
                            .applyOption()
                    )
                )
            }
        )
        return this
    }
}