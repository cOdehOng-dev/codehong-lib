package com.codehong.library.widget.button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.codehong.library.widget.databinding.HonglibViewTextButtonBinding
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.text.HongTextBuilder

class HongTextButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = HonglibViewTextButtonBinding
        .inflate(LayoutInflater.from(context), this, true)

    var option = HongTextButtonOption()
        private set

    fun set(
        option: HongTextButtonOption
    ): HongTextButtonView {
        this.option = option

        setOnClickListener {
            option.click?.invoke(option)
        }

        with(binding.clContainer) {
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
                border = option.border,
                radius = option.radius,
                backgroundColor = option.backgroundColorHex,
                useShapeCircle = option.useShapeCircle,
            )
        }

        binding.vText.set(
            option = HongTextBuilder()
                .copy(option.textOption)
                .width(HongLayoutParam.MATCH_PARENT.value)
                .textAlign(HongTextAlign.CENTER)
                .maxLines(1)
                .applyOption()
        )

        return this
    }
}