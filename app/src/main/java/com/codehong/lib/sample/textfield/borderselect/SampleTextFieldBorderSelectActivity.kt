package com.codehong.lib.sample.textfield.borderselect

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.Consts
import com.codehong.library.widget.bottomsheet.select.HongBottomSheetSelectBuilder
import com.codehong.library.widget.bottomsheet.select.HongBottomSheetSelect
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.rule.HongInputState
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.textfield.select.HongTextFieldBorderSelect
import com.codehong.library.widget.textfield.select.HongTextFieldBorderSelectBuilder

class SampleTextFieldBorderSelectActivity : BaseSampleComposeActivity() {

    @Composable
    override fun InitCompose() {
        val optionList = mutableListOf(
            Pair("실내 러닝", ""),
            Pair("실외 러닝", ""),
            Pair(Consts.DIRECT_INPUT, "")
        )
        var runningType by remember { mutableStateOf("실외 러닝") }
        var showBottomSheet by remember { mutableStateOf(false) }
        var useDirectInput by remember(false) { mutableStateOf(false) }

        val option = HongTextFieldBorderSelectBuilder()
            .backgroundColor(HongColor.WHITE_100)
            .label("선호하는 러닝")
            .initialInput(runningType)
            .state(HongInputState.ENABLE)
            .isRequired(true)
            .placeholder("선호하는 러닝을 선택해주세요.")
            .helperText("헬퍼텍스트가 노출되는 영역입니다.")
            .useNumberKeypad(false)
            .onChangeInput { changeInput ->
                runningType = changeInput.ifEmpty { "" }
            }
            .onSelectionClick {
                showBottomSheet = true
            }
            .applyOption()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .hongBackground(
                    color = HongColor.WHITE_100
                )
        ) {
            HongTextFieldBorderSelect(
                option = HongTextFieldBorderSelectBuilder()
                    .copy(option)
                    .useDirectInput(useDirectInput)
                    .applyOption(),
            )

            HongBottomSheetSelect(
                showBottomSheet = showBottomSheet,
                option = HongBottomSheetSelectBuilder()
                    .title("선호 러닝")
                    .selectionList(optionList)
                    .initialSelection(
                        Pair("실외 러닝", "")
                    )
                    .selectSelectionCallback {
                        Log.d("TAG", "선택된 옵션: $it")
                        runningType = if (it.first == Consts.DIRECT_INPUT) {
                            useDirectInput = true
                            ""
                        } else {
                            useDirectInput = false
                            it.first
                        }
                    }
                    .onChangeVisibleState {
                        showBottomSheet = it
                    }
                    .applyOption()
            )
        }
    }
}