package com.codehong.lib.sample.videopopup

import android.os.Bundle
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleVideoPopupBinding
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.util.HongToastUtil
import com.codehong.library.widget.util.applyStatusBarColor
import com.codehong.library.widget.videopopup.HongVideoPopupManager
import com.codehong.library.widget.videopopup.view.HongVideoPopupListener

class SampleVideoPopupActivity : BaseActivity() {

    private lateinit var binding: ActivitySampleVideoPopupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleVideoPopupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vHeader.init(HongWidgetType.VIDEO_POPUP.value) {
            finish()
        }

        binding.btnRemove.setOnClickListener {
            HongVideoPopupManager.resetLastSeenTimestamp(this)
        }

        setup()
        checkShowing()
    }

    override fun handleOnBackPressed() {
        if (binding.vVideoPopup.checkVisible()) {
            return
        }
        super.handleOnBackPressed()
    }

    private fun setup() {
        binding.vVideoPopup.set(
            radiusDp = 14,
            blockTouchOutside = true,
            listener = object : HongVideoPopupListener {
                override fun onShow() {
                    applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_9929292d)
                }

                override fun onHide(isClickClose: Boolean) {
                    applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_ffffff)
                    if (isClickClose) {
                        HongVideoPopupManager.saveOneDayLastSeenTimestamp(this@SampleVideoPopupActivity)
                    }
                }
            }
        )
    }

    private fun checkShowing() {
        val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"
        val ratio = "16:9"
        val landingLink = "https://github.com/cOdehOng-dev"
        if (HongVideoPopupManager.isAllowDisplaying(this)) {
            binding.vVideoPopup.show(
                videoUrl = url,
                ratio = ratio,
                landingLink = landingLink,
                landing = {
                    if (!it.isNullOrEmpty()) {
                        HongToastUtil.showToast(this, "링크 이동")
                    }
                }
            )
        }
    }
}