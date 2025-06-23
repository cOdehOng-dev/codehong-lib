package com.codehong.lib.sample.videopopup

import android.os.Bundle
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleVideoPopupBuilderBinding
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.util.HongToastUtil
import com.codehong.library.widget.util.applyStatusBarColor
import com.codehong.library.widget.videopopup.HongVideoPopupManager
import com.codehong.library.widget.videopopup.builder.HongVideoPopupBuilderView
import com.codehong.library.widget.videopopup.builder.HongVideoPopupOption

class SampleVideoPopupBuilderActivity : BaseActivity() {

    private lateinit var binding: ActivitySampleVideoPopupBuilderBinding

    private var videoPopupView: HongVideoPopupBuilderView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleVideoPopupBuilderBinding.inflate(layoutInflater)
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
        if (videoPopupView?.isShow() == true) {
            videoPopupView?.dismiss(true) {
                applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_ffffff)
            }
            return
        }
        super.handleOnBackPressed()
    }

    private fun setup() {
        val option = HongVideoPopupOption.Builder()
            .setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4")
            .setRadiusDp(14)
            .setRatio("16:9")
            .setLandingLink("https://github.com/cOdehOng-dev")
            .setTopLeft(true)
            .setTopRight(true)
            .build()
        this.videoPopupView = HongVideoPopupBuilderView(this)
            .set(
                option,
                onShow = {
                    applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_9929292d)
                },
                onHide = { isClickClose ->
                    hidePopup(isClickClose)
                }
            )
        binding.flVideoPopup.addView(videoPopupView)
    }

    private fun checkShowing() {
        if (HongVideoPopupManager.isAllowDisplaying(this)) {
            videoPopupView?.show(
                onHide = { isClickClose ->
                    hidePopup(isClickClose)
                },
                landing = {
                    if (!it.isNullOrEmpty()) {
                        HongToastUtil.showToast(this, "링크 이동")
                    }
                }
            )
        }
    }

    private fun hidePopup(isClickClose: Boolean) {
        applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_ffffff)
        if (isClickClose) {
            HongVideoPopupManager.saveOneDayLastSeenTimestamp(this@SampleVideoPopupBuilderActivity)
        }
    }
}