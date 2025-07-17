package com.codehong.lib.sample.toggleswitch

import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder
import com.codehong.library.widget.toggleswitch.HongSwitchCompose
import com.codehong.library.widget.toggleswitch.HongSwitchView

class SampleSwitchActivity : BaseSampleMixActivity() {

    private val option1 = HongSwitchBuilder()
        .width(55)
        .height(30)
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .onColor(HongColor.MAIN_ORANGE_100)
        .offColor(HongColor.GRAY_20)
        .cursorSize(25)
        .cursorHorizontalMargin(3)
        .cursorColor(HongColor.WHITE_100)
        .initialState(false)
        .applyOption()

    private val option2 = HongSwitchBuilder()
        .width(55)
        .height(30)
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .onColor(HongColor.MAIN_ORANGE_100)
        .offColor(HongColor.GRAY_20)
        .cursorSize(25)
        .cursorHorizontalMargin(3)
        .cursorColor(HongColor.WHITE_100)
        .initialState(true)
        .applyOption()

    override fun optionViewList(): List<View> {
        return listOf(
            HongSwitchView(this).set(option = option1),
            HongSwitchView(this).set(option = option2)
        )
    }

    @Composable
    override fun InitCompose() {
        Column {
            HongSwitchCompose(option1)
            HongSwitchCompose(option2)
        }
    }
}