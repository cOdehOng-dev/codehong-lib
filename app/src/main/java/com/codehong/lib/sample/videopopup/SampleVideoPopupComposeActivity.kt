package com.codehong.lib.sample.videopopup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.R
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.button.text.HongButtonTextCompose
import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.util.HongToastUtil
import com.codehong.library.widget.util.applyStatusBarColor
import com.codehong.library.widget.videopopup.HongVideoPopupBuilder
import com.codehong.library.widget.videopopup.HongVideoPopupCompose
import com.codehong.library.widget.videopopup.HongVideoPopupManager


class SampleVideoPopupComposeActivity : BaseSampleComposeActivity() {

    override fun handleOnBackPressed() {
        applyStatusBarColor(R.color.honglib_color_ffffff)
        super.handleOnBackPressed()
    }


    @Composable
    override fun InitCompose() {
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
            .onShow {
                applyStatusBarColor(R.color.honglib_color_29292d_60)
            }
            .onHide { isClickClose ->
                hidePopup(isClickClose)
            }
            .clickLanding { link ->
                if (!link.isNullOrEmpty()) {
                    HongToastUtil.showToast(this, "링크 이동")
                }

            }
            .applyOption()

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val remoteButtonOption = HongButtonTextBuilder()
                .width(150)
                .height(50)
                .margin(
                    HongSpacingInfo(
                        top = 10f,
                        left = 20f,
                        right = 20f,
                        bottom = 10f
                    )
                )
                .text("데이터 초기화")
                .textTypo(HongTypo.BODY_15_B)
                .textColor(HongColor.WHITE_100)
                .backgroundColor(HongColor.GRAY_50.hex)
                .radius(
                    HongRadiusInfo(
                        all = 8
                    )
                )
                .onClick {
                    HongVideoPopupManager.resetLastSeenTimestamp(this@SampleVideoPopupComposeActivity)
                }
                .applyOption()
            HongButtonTextCompose(
                option = remoteButtonOption,
            )
        }

        HongVideoPopupCompose(
            option = option
        )
    }

    private fun hidePopup(isClickClose: Boolean) {
        applyStatusBarColor(R.color.honglib_color_ffffff)
        if (isClickClose) {
            HongVideoPopupManager.saveOneDayLastSeenTimestamp(this@SampleVideoPopupComposeActivity)
        }
    }
}