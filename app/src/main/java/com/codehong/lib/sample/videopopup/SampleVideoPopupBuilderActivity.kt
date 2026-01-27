package com.codehong.lib.sample.videopopup

import android.os.Bundle
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleVideoPopupBuilderBinding
import com.codehong.library.widget.extensions.applyStatusBarColor
import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.util.HongToastUtil
import com.codehong.library.widget.videopopup.HongVideoPopupBuilder
import com.codehong.library.widget.videopopup.HongVideoPopupManager
import com.codehong.library.widget.videopopup.HongVideoPopupView

class SampleVideoPopupBuilderActivity : BaseActivity() {

    private lateinit var binding: ActivitySampleVideoPopupBuilderBinding

    private var videoPopupView: HongVideoPopupView? = null


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
        val option = HongVideoPopupBuilder()
            .videoPlayerOption(
                HongVideoPlayerBuilder()
                    .setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4")
                    .ratio("16:9")
                    .radius(
                        HongRadiusInfo(
                            top = 14
                        )
                    )
                    .applyOption()
            )
            .landingLink("https://github.com/cOdehOng-dev")
            .applyOption()
        this.videoPopupView = HongVideoPopupView(this)
            .set(
                option,
                onShow = {
                    applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_29292d_60)
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
            HongVideoPopupManager.saveOneDayLastSeenTimestamp(this)
        }
    }
}