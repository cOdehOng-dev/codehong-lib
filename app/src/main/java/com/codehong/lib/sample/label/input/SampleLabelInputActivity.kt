package com.codehong.lib.sample.label.input

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.label.input.HongLabelInputBuilder
import com.codehong.library.widget.label.input.HongLabelInputCompose
import com.codehong.library.widget.label.input.HongLabelInputOption
import com.codehong.library.widget.label.input.HongLabelInputView
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption

class SampleLabelInputActivity : BaseSampleMixActivity() {
    private val option1 = HongLabelInputBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(
            HongSpacingInfo(
                top = 10f,
                bottom = 10f,
                left = 16f,
                right = 16f
            )
        )
        .label("레이블")
        .description("레이블 설명하는 테스트이에요.")
        .textFieldOption(
            HongTextFieldBuilder()
                .copy(HongLabelInputOption.DEFAULT_TEXT_FIELD)
                .height(48)
                .margin(
                    HongSpacingInfo(
                        top = 10f
                    )
                )
                .placeholderTextOption(
                    HongTextBuilder()
                        .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                        .text("월요일 좋아!!!!")
                        .applyOption()
                )
                .inputTextOption(
                    HongTextBuilder()
                        .copy(HongTextFieldOption.DEFAULT_INPUT)
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .typography(HongTypo.BODY_14)
                        .color(HongColor.BLACK_100)
                        .applyOption()
                )
                .clearImageOption(
                    HongTextFieldOption.DEFAULT_CLEAR_IMAGE
                )
                .applyOption()
        )
        .applyOption()

    private val option2 = HongLabelInputBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(
            HongSpacingInfo(
                top = 10f,
                bottom = 10f,
                left = 16f,
                right = 16f
            )
        )
        .label("레이블")
        .description("레이블 설명하는 테스트이에요.")
        .textFieldOption(
            HongTextFieldBuilder()
                .copy(HongLabelInputOption.DEFAULT_TEXT_FIELD)
                .height(48)
                .margin(
                    HongSpacingInfo(
                        top = 10f
                    )
                )
                .input("월요일 좋아!!!!")
                .clearImageOption(
                    HongTextFieldOption.DEFAULT_CLEAR_IMAGE
                )
                .inputTextOption(
                    HongTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .typography(HongTypo.BODY_14)
                        .color(HongColor.BLACK_100)
                        .applyOption()
                )
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
                add(HongLabelInputView(this@SampleLabelInputActivity).set(option))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongLabelInputCompose(it)
        }
    }

}