package com.codehong.library.widget.util.picker

abstract class PickerItemAdapter {

    abstract fun getValue(position: Int): String

    abstract fun getPosition(vale: String): Int

    abstract fun getTextWithMaximumLength(): String

    open fun getSize(): Int = -1

    open fun getMinValidIndex(): Int? {
        return null
    }

    open fun getMaxValidIndex(): Int? {
        return null
    }

    var picker: PickerItem? = null

    fun notifyDataSetChanged() {
        picker?.setAdapter(this)
        picker?.requestLayout()
    }
}