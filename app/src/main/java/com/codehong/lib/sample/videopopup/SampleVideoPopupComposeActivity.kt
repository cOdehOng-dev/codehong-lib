package com.codehong.lib.sample.videopopup

import android.os.Bundle
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleVideoPopupComposeBinding
import com.codehong.library.widget.R
import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.util.HongToastUtil
import com.codehong.library.widget.util.applyStatusBarColor
import com.codehong.library.widget.videopopup.HongVideoPopupBuilder
import com.codehong.library.widget.videopopup.HongVideoPopupCompose
import com.codehong.library.widget.videopopup.HongVideoPopupManager


// TODO 구조 변경
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
        applyStatusBarColor(R.color.honglib_color_ffffff)
        super.handleOnBackPressed()
    }

    private fun setup() {
        val option = HongVideoPopupBuilder()
            .videoPlayerOption(
                HongVideoPlayerBuilder()
                    .setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4")
                    .radius(
                        HongRadiusInfo(
                            top = 14
                        )
                    )
                    .ratio("16:9")
                    .applyOption()
            )
            .landingLink("https://github.com/cOdehOng-dev")
            .applyOption()
        binding.vCompose.setContent {
            HongVideoPopupCompose(
                option = option,
                onShow = {
                    applyStatusBarColor(R.color.honglib_color_29292d_60)
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
        applyStatusBarColor(R.color.honglib_color_ffffff)
        if (isClickClose) {
            HongVideoPopupManager.saveOneDayLastSeenTimestamp(this@SampleVideoPopupComposeActivity)
        }
    }
}