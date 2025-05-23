package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.codehong.lib.sample.databinding.ViewTitleDespBinding
import com.codehong.library.widget.util.setTextSize

class TitleDespView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewTitleDespBinding.inflate(LayoutInflater.from(context), this, true)

    fun init(
        title: String,
        desp: String? = null
    ) {
        binding.tvTitle.text = title
        if (title.isNotEmpty()) {
            binding.tvTitle.visibility = View.VISIBLE
        } else {
            binding.tvTitle.visibility = View.GONE
        }
        if (desp.isNullOrEmpty()) {
            binding.tvDesp.visibility = View.GONE
        } else {
            binding.tvDesp.visibility = View.VISIBLE
            binding.tvDesp.text = desp
        }
    }

    fun titleSize(dp: Int) {
        binding.tvTitle.setTextSize(dp)
    }

    fun despSize(dp: Int) {
        binding.tvDesp.setTextSize(dp)
    }
}

