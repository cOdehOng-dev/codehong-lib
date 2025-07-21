package com.codehong.lib.sample.textfield.number

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption
import com.codehong.library.widget.textfield.number.HongNumberTextFieldBuilder
import com.codehong.library.widget.textfield.number.HongNumberTextFieldCompose
import com.codehong.library.widget.textfield.number.HongNumberTextFieldView

class SampleNumberTextFieldActivity : BaseSampleMixActivity() {

    private val option = HongNumberTextFieldBuilder()
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
        .placeholderTextOption(
            HongTextBuilder()
                .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                .text("값을 입력해주세요.")
                .applyOption()
        )
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .applyOption()

    private val option2 = HongNumberTextFieldBuilder()
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
        .placeholderTextOption(
            HongTextBuilder()
                .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                .text("값을 입력해주세요.")
                .applyOption()
        )
        .input("53600")
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .applyOption()


    private val optionList get() = listOf(
        option,
        option2
    )


    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongNumberTextFieldView(this@SampleNumberTextFieldActivity).set(it))
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