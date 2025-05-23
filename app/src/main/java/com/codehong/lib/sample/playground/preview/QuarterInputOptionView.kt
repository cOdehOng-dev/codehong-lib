package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.codehong.lib.sample.databinding.ViewQuarterInputPropertyBinding

class QuarterInputOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewQuarterInputPropertyBinding.inflate(LayoutInflater.from(context), this, true)

    fun init(
        mainTitle: String,
        desp: String? = null,
        inputInit1: Triple<String?, String?, Boolean>,
        inputInit2: Triple<String?, String?, Boolean>,
        inputInit3: Triple<String?, String?, Boolean>,
        inputInit4: Triple<String?, String?, Boolean>
    ) {
        binding.vTitleDesp.init(mainTitle, desp)

        with(binding.vInput1) {
            titleSize(15)
            init(
                title = inputInit1.first ?: "",
                initial = inputInit1.second,
                isNumberType = inputInit1.third
            )
        }

        with(binding.vInput2) {
            titleSize(15)
            init(
                title = inputInit2.first ?: "",
                initial = inputInit2.second,
                isNumberType = inputInit2.third
            )
        }

        with(binding.vInput3) {
            titleSize(15)
            init(
                title = inputInit3.first ?: "",
                initial = inputInit3.second,
                isNumberType = inputInit3.third
            )
        }

        with(binding.vInput4) {
            titleSize(15)
            init(
                title = inputInit4.first ?: "",
                initial = inputInit4.second,
                isNumberType = inputInit4.third
            )
        }
    }

    fun observe(
        inputCallback1: (String?) -> Unit,
        inputCallback2: (String?) -> Unit,
        inputCallback3: (String?) -> Unit,
        inputCallback4: (String?) -> Unit
    ) {
        binding.vInput1.observe(inputCallback1)
        binding.vInput2.observe(inputCallback2)
        binding.vInput3.observe(inputCallback3)
        binding.vInput4.observe(inputCallback4)
    }

    fun setOption(
        property1: String,
        property2: String,
        property3: String,
        property4: String
    ) {
        binding.vInput1.setOption(property1)
        binding.vInput2.setOption(property2)
        binding.vInput3.setOption(property3)
        binding.vInput4.setOption(property4)
    }
}
