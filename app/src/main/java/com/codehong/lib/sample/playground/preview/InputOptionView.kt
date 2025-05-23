package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.codehong.lib.sample.databinding.ViewInputOptionBinding

class InputOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewInputOptionBinding.inflate(LayoutInflater.from(context), this, true)

    fun init(
        title: String,
        desp: String? = null,
        initial: String? = null,
        isNumberType: Boolean = false
    ) {
        binding.vTitleDesp.init(
            title,
            desp
        )

        binding.vInputProperty.init(initial, isNumberType)
    }

    fun observe(
        inputCallback: (String?) -> Unit
    ) {
        binding.vInputProperty.observe(inputCallback)
    }

    fun setOption(property: String) {
        binding.vInputProperty.setProperty(property)
    }

    fun titleSize(dp: Int) {
        binding.vTitleDesp.titleSize(dp)
    }

    fun despSize(dp: Int) {
        binding.vTitleDesp.despSize(dp)
    }

    fun inputSize(dp: Float) {
        binding.vInputProperty.inputTextSize(dp)
    }
}
