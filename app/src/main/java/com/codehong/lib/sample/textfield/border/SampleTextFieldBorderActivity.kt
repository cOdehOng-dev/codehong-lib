package com.codehong.lib.sample.textfield.border

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongInputState
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.textfield.border.HongTextFieldBorderBuilder
import com.codehong.library.widget.textfield.border.HongTextFieldBorderCompose
import com.codehong.library.widget.textfield.border.HongTextFieldBorderView

class SampleTextFieldBorderActivity : BaseSampleMixActivity() {

    private val option1 = HongTextFieldBorderBuilder()
        .padding(
            HongSpacingInfo(
                left = 20f,
                right = 20f
            )
        )
        .label("닉네임")
        .state(HongInputState.ENABLE)
        .isRequired(true)
        .placeholder("닉네임을 입력하세요.")
        .helperText("헬퍼텍스트가 노출되는 영역입니다.")
        .applyOption()

    private val option2 = HongTextFieldBorderBuilder()
        .padding(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                top = 20f
            )
        )
        .label("닉네임")
        .state(HongInputState.DISABLE)
        .isRequired(true)
        .placeholder("닉네임을 입력하세요.")
        .helperText("헬퍼텍스트가 노출되는 영역입니다.")
        .applyOption()

    private val optionList = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(
                    HongTextFieldBorderView(this@SampleTextFieldBorderActivity).set(it)
                )
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTextFieldBorderCompose(it)
        }
    }
}