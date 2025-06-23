package com.codehong.lib.sample.videopopup

import android.os.Bundle
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleVideoPopupComposeBinding
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.util.HongToastUtil
import com.codehong.library.widget.util.applyStatusBarColor
import com.codehong.library.widget.videopopup.HongVideoPopupManager
import com.codehong.library.widget.videopopup.builder.HongVideoPopupOption
import com.codehong.library.widget.videopopup.compose.HongComposeVideoPopup

class SampleVideoPopupComposeActivity : BaseActivity() {

    private lateinit var binding: ActivitySampleVideoPopupComposeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleVideoPopupComposeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vHeader.init(HongWidgetType.VIDEO_POPUP.value) {
            finish()
        }

        binding.btnRemove.setOnClickListener {
            HongVideoPopupManager.resetLastSeenTimestamp(this)
        }
        setup()
    }

    override fun handleOnBackPressed() {
//        if (videoPopupView?.isShow() == true) {
//            videoPopupView?.dismiss(true) {
//                applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_ffffff)
//            }
//            return
//        }
        applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_ffffff)
        super.handleOnBackPressed()
    }

    private fun setup() {
        val option = HongVideoPopupOption.Builder()
            .setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4")
            .setRadiusDp(14)
            .setRatio("16:9")
            .setLandingLink("https://github.com/cOdehOng-dev")
            .setTopLeft(true)
            .setTopRight(true)
            .build()
        binding.vCompose.setContent {
            HongComposeVideoPopup(
                option = option,
                onShow = {
                    applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_9929292d)
                },
                onHide = { isClickClose ->
                    hidePopup(isClickClose)
                },
                clickLanding = { link ->
                    if (!link.isNullOrEmpty()) {
                        HongToastUtil.showToast(this, "링크 이동")
                    }
                }
            )
        }
    }

    private fun hidePopup(isClickClose: Boolean) {
        applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_ffffff)
        if (isClickClose) {
            HongVideoPopupManager.saveOneDayLastSeenTimestamp(this@SampleVideoPopupComposeActivity)
        }
    }
}