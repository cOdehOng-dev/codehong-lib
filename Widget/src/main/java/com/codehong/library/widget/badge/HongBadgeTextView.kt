package com.codehong.library.widget.badge

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.codehong.library.widget.databinding.HonglibViewBadgeTextBinding
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout

class HongBadgeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var option = HongBadgeTextOption()
        private set

    private val binding = HonglibViewBadgeTextBinding
        .inflate(LayoutInflater.from(context), this, true)


    fun set(
        option: HongBadgeTextOption
    ): HongBadgeTextView {
        this.option = option

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
                backgroundColor = option.backgroundColorHex,
                border = option.border,
                radius = option.radius
            )
        }
        binding.vText.set(option.textOption)
        return this
    }

}