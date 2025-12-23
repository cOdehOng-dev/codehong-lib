package com.codehong.library.widget.header

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageView
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.text.label.HongTextBuilder
import com.codehong.library.widget.text.label.HongTextView

class HongHeaderCloseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var option = HongHeaderCloseOption()
        private set

    init {
        orientation = HORIZONTAL
        setLayout(
            LayoutParams.MATCH_PARENT,
            58
        )
    }

    fun set(
        option: HongHeaderCloseOption
    ): HongHeaderCloseView {
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
                            .width(HongLayoutParam.MATCH_PARENT.value)
                            .height(HongLayoutParam.WRAP_CONTENT.value)
                            .textAlign(HongTextAlign.CENTER)
                            .maxLines(1)
                            .overflow(HongTextOverflow.ELLIPSIS)
                            .typography(option.titleTypo)
                            .color(option.titleColorHex)
                            .text(option.title)
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
                setOnClickListener { option.onCloseClick() }
                gravity = Gravity.CENTER
            }.apply {
                this.addView(
                    HongImageView(context).set(
                        HongImageBuilder()
                            .width(20)
                            .height(20)
                            .scaleType(HongScaleType.CENTER_CROP)
                            .imageInfo(R.drawable.honglib_ic_close)
                            .imageColor(option.closeIconColorHex)
                            .useShapeCircle(true)
                            .applyOption()
                    )
                )
            }
        )
        return this
    }
}