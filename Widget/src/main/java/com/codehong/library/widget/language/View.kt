package com.codehong.library.widget.language

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView

fun Context.view(
    block: View.() -> Unit
) = View(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.view(
    block: View.() -> Unit
) = View(this.context).run {
    block.invoke(this)
    this@view.addView(this)
    this
}

fun Context.textField(
    block: AppCompatEditText.() -> Unit
) = AppCompatEditText(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.textField(
    block: AppCompatEditText.() -> Unit
) = AppCompatEditText(this.context).run {
    block.invoke(this)
    this@textField.addView(this)
    this
}

fun Context.image(
    block: AppCompatImageView.() -> Unit
) = AppCompatImageView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.image(
    block: AppCompatImageView.() -> Unit
) = AppCompatImageView(this.context).run {
    block.invoke(this)
    this@image.addView(this)
    this
}