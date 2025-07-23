package com.codehong.lib.sample.label.toggle

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchBuilder
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchCompose
import com.codehong.library.widget.language.hongLabelSwitch
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder

class SampleLabelSwitchActivity : BaseSampleMixActivity() {

    private val option1 = HongLabelSwitchBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f,
                left = 16f,
                right = 16f
            )
        )
        .label("테스트")
        .description("테스트 입니다요")
        .switchOption(
            HongSwitchBuilder()
                .width(55)
                .height(30)
                .onColor(HongColor.MAIN_ORANGE_100)
                .offColor(HongColor.GRAY_20)
                .cursorSize(25)
                .cursorHorizontalMargin(3)
                .cursorColor(HongColor.WHITE_100)
                .initialState(true)
                .switchClick { _, _ -> }
                .applyOption()
        )
        .applyOption()

    private val option2 = HongLabelSwitchBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f,
                left = 16f,
                right = 16f
            )
        )
        .label("테스트")
        .switchOption(
            HongSwitchBuilder()
                .width(55)
                .height(30)
                .onColor(HongColor.MAIN_ORANGE_100)
                .offColor(HongColor.GRAY_20)
                .cursorSize(25)
                .cursorHorizontalMargin(3)
                .cursorColor(HongColor.WHITE_100)
                .initialState(false)
                .switchClick { _, _ -> }
                .applyOption()
        )
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { option ->
                hongLabelSwitch {
                    this.set(option)
                }
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach { option ->
            HongLabelSwitchCompose(option)
        }
    }
}