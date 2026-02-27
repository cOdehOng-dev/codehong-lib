package com.codehong.lib.sample.bottomsheet

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
import com.codehong.library.widget.bottomsheet.bank.HongBottomSheetBank
import com.codehong.library.widget.bottomsheet.bank.HongBottomSheetBankBuilder
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.rule.HongInputState
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.textfield.select.HongTextFieldBorderSelect
import com.codehong.library.widget.textfield.select.HongTextFieldBorderSelectBuilder

class SampleBottomSheetBankActivity : BaseSampleComposeActivity() {

    @Composable
    override fun InitCompose() {
        var showBottomSheet by remember { mutableStateOf(false) }
        var selectBankName by remember { mutableStateOf("") }

        val option = HongTextFieldBorderSelectBuilder()
            .backgroundColor(HongColor.WHITE_100)
            .label("은행 선택")
            .initialInput(selectBankName)
            .state(HongInputState.ENABLE)
            .isRequired(false)
            .placeholder("은행을 선택해주세요")
            .useNumberKeypad(false)
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
                    .applyOption(),
            )

            HongBottomSheetBank(
                showBottomSheet = showBottomSheet,
                option = HongBottomSheetBankBuilder()
                    .onDismissed {
                        showBottomSheet = false
                    }
                    .onBankSelected {
                        selectBankName = it.bankName
                        showBottomSheet = false
                    }
                    .applyOption()
            )
        }
    }
}