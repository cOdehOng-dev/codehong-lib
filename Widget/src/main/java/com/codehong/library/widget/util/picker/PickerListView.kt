package com.codehong.library.widget.util.picker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.codehong.library.widget.databinding.HonglibContainerPickerBinding

class PickerListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        HonglibContainerPickerBinding.inflate(LayoutInflater.from(context), this, true)

    fun initPicker(
        pickerList: List<String>,
        selectPosition: Int,
        callback: (String, Int) -> Unit
    ) {
        var selectedItem: String
        if (pickerList.size > 1) {
            setPickerSetting(binding.vPicker, pickerList)
            selectedItem = pickerList.first()
        } else {
            binding.vPicker.visibility = View.GONE
        }
        binding.vPicker.scrollTo(selectPosition)
        binding.vPicker.setOnValueChangedListener(object : PickerItem.OnValueChangeListener {
            override fun onValueChange(picker: PickerItem, oldVal: String, newVal: String) {
                selectedItem = newVal
                callback.invoke(selectedItem, pickerList.indexOf(selectedItem))
            }
        })
    }

    private fun setPickerSetting(numberPicker: PickerItem, list: List<String>) {
        numberPicker.run {
            this.visibility = View.VISIBLE
            setRawPickerItemCount(7)
            setWrapSelectorRawPicker(false)
            var max = ""
            list.forEach {
                if (it.length > max.length) {
                    max = it
                }
            }
            setAdapter(object : PickerItemAdapter() {
                override fun getValue(position: Int): String {
                    return try {
                        list[position]
                    } catch (e: ArrayIndexOutOfBoundsException) {
                        ""
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    } catch (e: Exception) {
                        ""
                    }
                }

                override fun getPosition(vale: String): Int {
                    list.forEachIndexed { index, s ->
                        if (vale == s) {
                            return index
                        }
                    }
                    return 0
                }

                override fun getTextWithMaximumLength(): String {
                    return max
                }

                override fun getMaxValidIndex(): Int {
                    return list.size - 1
                }

                override fun getMinValidIndex(): Int {
                    return 0
                }
            })
        }
    }
}
