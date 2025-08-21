package com.codehong.lib.sample.textfield

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldCompose
import com.codehong.library.widget.textfield.HongTextFieldView

class SampleTextFieldActivity : BaseSampleMixActivity() {

    private val option1 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f,
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("값을 입력해주세요.")
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText ->
        }
        .applyOption()

    private val option2 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[키보드 done 버튼] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText ->
        }
        .applyOption()

    private val option3 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[숫자 키패드] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.NUMBER, HongKeyboardActionType.DONE))
        .onTextChanged { trackingText ->
        }
        .applyOption()

    private val option4 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[지우기 버튼] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText ->
        }
        .clearIconRes(com.codehong.library.widget.R.drawable.honglib_ic_20_circle_close_fill)
        .applyOption()

    private val option5 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[password] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.PASSWORD, HongKeyboardActionType.DONE))
        .onTextChanged { trackingText ->
        }
        .applyOption()

    private val option6 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[키보드 go] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.GO))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText ->
        }
        .applyOption()

    private val option7 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[키보드 search] 값을 입력해주세요.")
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.SEARCH))
        .onTextChanged { trackingText ->
        }
        .applyOption()

    private val option8 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[키보드 send] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.SEND))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText ->
        }
        .applyOption()

    private val option9 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[number password] 값을 입력해주세요.")
        .keyboardOption(
            Pair(
                HongKeyboardType.NUMBER_PASSWORD,
                HongKeyboardActionType.DONE
            )
        )
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { trackingText ->
        }
        .applyOption()

    private val option10 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("[딜레이] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .delayInputCallback(500L)
        .onTextChanged { trackingText ->
            Log.d("TAG", "TextField 딜레이 입력 = $trackingText")
        }
        .applyOption()

    private val option11 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("딜레이 삭제 버튼 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .delayInputCallback(500L)
        .onTextChanged { trackingText ->
            Log.d("TAG", "TextField 딜레이 입력[삭제 버튼 포함] = $trackingText")
        }
        .clearIconRes(com.codehong.library.widget.R.drawable.honglib_ic_20_circle_close_fill)
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2,
        option3,
        option4,
        option5,
        option6,
        option7,
        option8,
        option9,
        option10,
        option11,
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(
                    HongTextFieldView(this@SampleTextFieldActivity).set(it)
                )
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTextFieldCompose(it)
        }
    }
}
