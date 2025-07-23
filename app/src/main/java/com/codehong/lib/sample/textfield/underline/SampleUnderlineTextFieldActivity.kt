package com.codehong.lib.sample.textfield.underline

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldBuilder
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldCompose
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldOption
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldView

class SampleUnderlineTextFieldActivity : BaseSampleMixActivity() {

    private val option = HongUnderlineTextFieldBuilder()
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
        .onTextChanged { trackingText ->
        }
        .clearImageOption(
            HongUnderlineTextFieldOption.DEFAULT_CLEAR_IMAGE
        )
        .applyOption()

    private val option2 = HongUnderlineTextFieldBuilder()
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
        .inputTextOption(
            HongTextBuilder()
                .copy(HongUnderlineTextFieldOption.DEFAULT_INPUT)
                .applyOption()
        )
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText ->
        }
        .clearImageOption(
            HongUnderlineTextFieldOption.DEFAULT_CLEAR_IMAGE
        )
        .underlineHeight(1)
        .applyOption()

    private val option3 = HongUnderlineTextFieldBuilder()
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
        .placeholderTextOption(
            HongTextBuilder()
                .copy(HongUnderlineTextFieldOption.DEFAULT_PLACEHOLDER)
                .text("[지우기 버튼] 값을 입력해주세요.")
                .applyOption()
        )
        .inputTextOption(
            HongTextBuilder()
                .copy(HongUnderlineTextFieldOption.DEFAULT_INPUT)
                .applyOption()
        )
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText ->
        }
        .clearImageOption(
            HongUnderlineTextFieldOption.DEFAULT_CLEAR_IMAGE
        )
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
                add(HongUnderlineTextFieldView(this@SampleUnderlineTextFieldActivity).set(it))
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