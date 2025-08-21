package com.codehong.lib.sample.textfield.underline

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.textfield.underline.HongTextFieldUnderlineBuilder
import com.codehong.library.widget.textfield.underline.HongTextFieldUnderlineView
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldCompose

class SampleTextFieldUnderlineActivity : BaseSampleMixActivity() {

    private val option = HongTextFieldUnderlineBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100.hex)
        .placeholder("[지우기 버튼] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText -> }
        .applyOption()

    private val option2 = HongTextFieldUnderlineBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f,
                top = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100.hex)
        .placeholder("[지우기 버튼] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText -> }
        .underlineHeight(1)
        .applyOption()

    private val option3 = HongTextFieldUnderlineBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f,
                top = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100.hex)
        .placeholder("[지우기 버튼] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText -> }
        .underlineFocusColor(HongColor.BLUE_100)
        .applyOption()

    private val optionList get() = listOf(
        option,
        option2,
        option3
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongTextFieldUnderlineView(this@SampleTextFieldUnderlineActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach { option ->
            HongUnderlineTextFieldCompose(option)
        }
    }
}