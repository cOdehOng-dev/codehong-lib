package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.codehong.lib.sample.databinding.ViewToggleOptionBinding

class ToggleOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewToggleOptionBinding
        .inflate(LayoutInflater.from(context), this, true)

    fun init(
        title: String,
        initial: Boolean,
        desp: String? = null
    ) {
        binding.tvTitle.text = title
        if (desp.isNullOrEmpty()) {
            binding.tvDesp.visibility = View.GONE
        } else {
            binding.tvDesp.visibility = View.VISIBLE
            binding.tvDesp.text = desp
        }

        binding.vToggle.setOnOff(initial, false)
    }

    fun observe(
        isOnCallback: (Boolean) -> Unit
    ) {
        binding.vToggle.setOnClickListener {
            binding.vToggle.setOnOff(
                isOn = !binding.vToggle.isOn,
                isListener = true
            )
        }
        binding.vToggle.setOnOnOffListener(object : ToggleView.OnOnOffChangedListener {
            override fun onOnOffChanged(isOn: Boolean) {
                isOnCallback.invoke(isOn)
                binding.vToggle.setOnOff(
                    isOn = isOn,
                    isListener = false
                )
            }
        })
    }
}
