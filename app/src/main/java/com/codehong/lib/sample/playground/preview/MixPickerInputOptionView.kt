package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.codehong.lib.sample.databinding.ViewMixPickerInputOptionBinding
import com.codehong.library.widget.util.picker.OptionPickerDialog

class MixPickerInputOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewMixPickerInputOptionBinding.inflate(LayoutInflater.from(context), this, true)

    private var selectPosition = 0

    fun init(
        title: String,
        desp: String? = null,
        initial: String? = null,
        isNumberType: Boolean = false
    ) {
        binding.vTitleDesp.init(title, desp)

        binding.tvSelectOption.text =
            if (!initial.isNullOrEmpty()) initial else "FIXED_SIZE"

        binding.vInput.init(
            initial = initial,
            isNumberType = isNumberType
        )
    }

    fun observe(
        title: String,
        pickerList: List<String>,
        selectPosition: Int,
        isDirectCallback: Boolean = false,
        pickerCallback: (String, Int) -> Unit,
        inputCallback: (String?) -> Unit
    ) {
        this.selectPosition = selectPosition
        binding.tvSelectOption.setOnClickListener {
            OptionPickerDialog(
                context,
                title = "$title 옵션 선택",
                optionList = pickerList,
                selectedPosition = this.selectPosition,
                isDirectCallback = isDirectCallback
            ) { selectOption, index ->
                Log.e("TAG", "선택한 옵션 = $selectOption")
                this.selectPosition = index
                binding.tvSelectOption.text = selectOption
                pickerCallback.invoke(selectOption, pickerList.indexOf(selectOption))
            }.show()
        }

        binding.vInput.observe(inputCallback)
    }

    fun showInput() {
        binding.vInput.show()
    }

    fun hideInput() {
        binding.vInput.hide()
    }

    fun setOption(property: String) {
        binding.vInput.setProperty(property)
    }
}
