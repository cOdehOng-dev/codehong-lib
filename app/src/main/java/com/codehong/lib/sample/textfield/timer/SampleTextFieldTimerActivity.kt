package com.codehong.lib.sample.textfield.timer

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.textfield.timer.HongTextFieldTimerBuilder
import com.codehong.library.widget.textfield.timer.HongTimerTextFieldCompose
import com.codehong.library.widget.textfield.timer.HongTextFieldTimerOption
import com.codehong.library.widget.textfield.timer.HongTextFieldTimerView

class SampleTextFieldTimerActivity : BaseSampleMixActivity() {

    private val option = HongTextFieldTimerBuilder()
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
        .placeholder("값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText -> }
        .min(1)
        .applyOption()

    private val option2 = HongTextFieldTimerBuilder()
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
        .clearImageOption(
            HongImageBuilder()
                .copy(HongTextFieldTimerOption.DEFAULT_CLEAR_IMAGE)
                .width(16)
                .height(16)
                .applyOption()
        )
        .sec(10)
        .underlineFinishColor(HongColor.RED_100)
        .onFinish {}
        .applyOption()

    private val optionList get() = listOf(
        option,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongTextFieldTimerView(this@SampleTextFieldTimerActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTimerTextFieldCompose(it)
        }

    }
}