package com.codehong.lib.sample.textfield.number

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.textfield.number.HongTextFieldNumberBuilder
import com.codehong.library.widget.textfield.number.HongNumberTextFieldCompose
import com.codehong.library.widget.textfield.number.HongTextFieldNumberView

class SampleTextFieldNumberActivity : BaseSampleMixActivity() {

    private val option = HongTextFieldNumberBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100)
        .placeholder("값을 입력해주세요.")
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .applyOption()

    private val option2 = HongTextFieldNumberBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100)
        .placeholder("값을 입력해주세요.")
        .input("53600")
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .applyOption()

    private val option3 = HongTextFieldNumberBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100)
        .placeholder("값을 입력해주세요.")
        .input("53600")
        .useDecimal(false)
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .applyOption()


    private val optionList get() = listOf(
        option,
        option2,
        option3
    )


    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongTextFieldNumberView(this@SampleTextFieldNumberActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongNumberTextFieldCompose(it)
        }
    }
}