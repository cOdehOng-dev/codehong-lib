package com.codehong.lib.sample.videopopup

import android.os.Bundle
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleVideoPopupBinding
import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.util.HongToastUtil
import com.codehong.library.widget.util.applyStatusBarColor
import com.codehong.library.widget.videopopup.HongVideoPopupBuilder
import com.codehong.library.widget.videopopup.HongVideoPopupManager

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
        val option = HongVideoPopupBuilder()
            .videoPlayerOption(
                HongVideoPlayerBuilder()
                    .setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")
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
        binding.vVideoPopup.set(
            option = option,
            onShow = {
                applyStatusBarColor(com.codehong.library.widget.R.color.honglib_color_29292d_60)
            },
            onHide = { isClickClose ->
                hidePopup(isClickClose)
            }
        )
    }

    private fun checkShowing() {
        if (HongVideoPopupManager.isAllowDisplaying(this)) {
            binding.vVideoPopup.show(
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