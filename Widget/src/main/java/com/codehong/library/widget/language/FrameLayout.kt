package com.codehong.library.widget.language

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout

fun Context.frameLayout(
    block: FrameLayout.() -> Unit
) = FrameLayout(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.frameLayout(
    block: FrameLayout.() -> Unit
) = FrameLayout(this.context).run {
    block.invoke(this)
    this@frameLayout.addView(this)
    this
}