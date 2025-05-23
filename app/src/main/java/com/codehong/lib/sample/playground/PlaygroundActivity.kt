package com.codehong.lib.sample.playground

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.codehong.lib.sample.R
import com.codehong.lib.sample.databinding.ActivityPlaygroundBinding
import com.codehong.lib.sample.playground.preview.ToggleView
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType
import com.codehong.library.widget.util.applyActivityCloseAnim
import com.codehong.library.widget.util.applyActivityOpenAnim

class PlaygroundActivity : ComponentActivity() {

    private lateinit var binding: ActivityPlaygroundBinding

    private var componentType: HongWidgetType? = null
    private var preview: View? = null
    private var isBorderOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyActivityOpenAnim(R.anim.slide_right_in)
        binding = ActivityPlaygroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        componentType = intent.getStringExtra("componentType").toHongWidgetType()

        if (componentType == HongWidgetType.NO_VALUE) {
            finish()
            return
        }

        binding.vHeader.init(
            title = componentType?.value ?: "",
            back = {
                finish()
            }
        )

        setOptionView()
    }

    override fun finish() {
        super.finish()
        applyActivityCloseAnim(R.anim.slide_right_out)
    }

    private fun setOptionView() {
        binding.ivCheckWhite.visibility = View.VISIBLE
        binding.ivCheckBlack.visibility = View.GONE
        binding.ivCheckGray.visibility = View.GONE

        binding.vChangeWhite.setOnClickListener {
            binding.ivCheckWhite.visibility = View.VISIBLE
            binding.ivCheckBlack.visibility = View.GONE
            binding.ivCheckGray.visibility = View.GONE
            binding.clPreview.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.color_ffffff
                )
            )
        }
        binding.vChangeBlack.setOnClickListener {
            binding.ivCheckWhite.visibility = View.GONE
            binding.ivCheckBlack.visibility = View.VISIBLE
            binding.ivCheckGray.visibility = View.GONE
            binding.clPreview.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.color_000000
                )
            )
        }
        binding.vChangeGray.setOnClickListener {
            binding.ivCheckWhite.visibility = View.GONE
            binding.ivCheckBlack.visibility = View.GONE
            binding.ivCheckGray.visibility = View.VISIBLE
            binding.clPreview.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.color_999999
                )
            )
        }

        binding.vToggleBorderLine.setOnClickListener {
            binding.vToggleBorderLine.setOnOff(
                isOn = !binding.vToggleBorderLine.isOn,
                isListener = true
            )
        }

        binding.vToggleBorderLine.setOnOnOffListener(object : ToggleView.OnOnOffChangedListener {
            override fun onOnOffChanged(isOn: Boolean) {
                isBorderOn = isOn
                preview?.setBackgroundResource(
                    if (isOn) {
                        R.drawable.rect_transparent_border_default
                    } else {
                        R.drawable.rect_transparent
                    }
                )
                binding.vToggleBorderLine.setOnOff(
                    isOn = isOn,
                    isListener = false
                )
            }
        })
        when (componentType) {
            HongWidgetType.TEXT -> {
                PlaygroundManager.previewText(this)
            }

            else -> {
            }
        }
    }

    fun applyPreview(preview: View) {
        preview.setBackgroundResource(
            if (isBorderOn) {
                R.drawable.rect_transparent_border_default
            } else {
                R.drawable.rect_transparent
            }
        )
        this.preview = preview
        if (binding.flComponentPreview.childCount > 0) {
            binding.flComponentPreview.removeAllViews()
        }
        binding.flComponentPreview.addView(preview)
    }

    fun addOptionView(view: View) {
        binding.llProperty.addView(view)
    }
}