package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.codehong.lib.sample.databinding.ViewHeaderBinding

class HeaderView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet?) : super(context, attr)
    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) :
            super(context, attr, defStyleAttr)

    private var binding = ViewHeaderBinding
        .inflate(LayoutInflater.from(context), this, true)

    fun init(
        title: String,
        back: () -> Unit
    ) {
        binding.ivBack.setOnClickListener { back.invoke() }
        binding.tvTitle.visibility = View.VISIBLE
        binding.tvTitle.text = title
    }
}
