package com.codehong.lib.sample.checkbox

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.checkbox.HongCheckBoxCompose
import com.codehong.library.widget.checkbox.HongCheckboxBuilder
import com.codehong.library.widget.checkbox.HongCheckboxView
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class SampleCheckboxActivity : BaseSampleMixActivity() {

    private val option1 = HongCheckboxBuilder()
        .size(24)
        .margin(
            HongSpacingInfo(
                left = 10f,
                right = 10f,
                top = 10f,
                bottom = 10f
            )
        )
        .backgroundColor(HongColor.TRANSPARENT)
        .checkedColor(HongColor.MAIN_ORANGE_100)
        .checkmarkColor(HongColor.WHITE_100)
        .border(
            HongBorderInfo(
                width = 2,
                color = HongColor.GRAY_20.hex
            )
        )
        .radius(
            HongRadiusInfo(
                topLeft = 4,
                topRight = 4,
                bottomLeft = 4,
                bottomRight = 4
            )
        )
        .applyOption()

    private val option2 = HongCheckboxBuilder()
        .size(24)
        .enabled(false)
        .margin(
            HongSpacingInfo(
                left = 10f,
                right = 10f,
                top = 10f,
                bottom = 10f
            )
        )
        .backgroundColor(HongColor.TRANSPARENT)
        .checkedColor(HongColor.MAIN_ORANGE_100)
        .checkmarkColor(HongColor.WHITE_100)
        .border(
            HongBorderInfo(
                width = 2,
                color = HongColor.GRAY_20.hex
            )
        )
        .radius(
            HongRadiusInfo(
                topLeft = 4,
                topRight = 4,
                bottomLeft = 4,
                bottomRight = 4
            )
        )
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return  mutableListOf<View>().apply {
            optionList.forEach { opt ->
                add(HongCheckboxView(this@SampleCheckboxActivity).set(opt))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach { option ->
            HongCheckBoxCompose(option)
        }
    }
}