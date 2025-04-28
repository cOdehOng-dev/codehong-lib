package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.codehong.lib.sample.databinding.ViewPickerOptionBinding
import com.codehong.library.widget.util.picker.OptionPickerDialog

class PickerOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewPickerOptionBinding.inflate(LayoutInflater.from(context), this, true)

    private var selectPosition = 0

    fun init(
        title: String,
        desp: String? = null,
        initial: String? = null
    ) {
        binding.vTitleDesp.init(
            title,
            desp
        )
        binding.tvSelectOption.text = if (!initial.isNullOrEmpty()) initial else "옵션 선택"
    }

    fun observe(
        title: String,
        pickerList: List<String>,
        selectPosition: Int,
        isDirectCallback: Boolean = false,
        callback: (String, Int) -> Unit
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
                callback.invoke(selectOption, pickerList.indexOf(selectOption))
            }.show()
        }
    }
}
