package com.codehong.library.widget.language

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout

fun Context.verticalLinearLayout(
    block: LinearLayout.() -> Unit
) = LinearLayout(this).run {
    this.orientation = LinearLayout.VERTICAL
    block.invoke(this)
    this
}

fun ViewGroup.verticalLinearLayout(
    block: LinearLayout.() -> Unit
) = LinearLayout(this.context).run {
    this.orientation = LinearLayout.VERTICAL
    block.invoke(this)
    this@verticalLinearLayout.addView(this)
    this
}

fun Context.horizontalLinearLayout(
    block: LinearLayout.() -> Unit
) = LinearLayout(this).run {
    this.orientation = LinearLayout.HORIZONTAL
    block.invoke(this)
    this
}

fun ViewGroup.horizontalLinearLayout(
    block: LinearLayout.() -> Unit
) = LinearLayout(this.context).run {
    this.orientation = LinearLayout.HORIZONTAL
    block.invoke(this)
    this@horizontalLinearLayout.addView(this)
    this
}