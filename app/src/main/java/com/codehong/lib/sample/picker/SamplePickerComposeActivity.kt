package com.codehong.lib.sample.picker

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.picker.HongPicker
import com.codehong.library.widget.picker.HongPickerBuilder
import com.codehong.library.widget.rule.color.HongColor

class SamplePickerComposeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showBottomSheet1 by remember { mutableStateOf(false) }
            var showBottomSheet2 by remember { mutableStateOf(false) }
            var showBottomSheet3 by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hongBackground(
                        color = HongColor.WHITE_100.hex
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Button(onClick = { showBottomSheet1 = true }) {
                        Text("열기")
                    }

                    Button(
                        modifier = Modifier
                            .padding(top = 15.dp),
                        onClick = { showBottomSheet2 = true }) {
                        Text("열기1")
                    }

                    Button(
                        modifier = Modifier
                            .padding(top = 15.dp),
                        onClick = { showBottomSheet3 = true }) {
                        Text("열기2")
                    }
                }
            }

            HongPicker(
                visible = showBottomSheet1,
                injectOption = HongPickerBuilder()
                    .title("성별 · 출생년도")
                    .firstOptionList(listOf("남성", "여성", "기타"))
                    .secondOptionList((1980..2010).map { "${it}년" })
                    .buttonText("선택")
                    .useDimClickClose(false)
                    .selectorColor(HongColor.GRAY_10.hex)
                    .onDismiss { showBottomSheet1 = false }
                    .onConfirm { selectedFirstOption, selectedSecondOption ->
                        showBottomSheet1 = false
                    }
                    .applyOption(),
            )

            HongPicker(
                visible = showBottomSheet2,
                injectOption = HongPickerBuilder()
                    .title("성별 · 출생년도")
                    .firstOptionList((1980..2010).map { "${it}년" })
                    .buttonText("선택")
                    .useDimClickClose(true)
                    .onDismiss { showBottomSheet2 = false }
                    .onDirectSelect { _, _ ->
                    }
                    .applyOption(),
            )

            HongPicker(
                visible = showBottomSheet3,
                injectOption = HongPickerBuilder()
                    .title("성별 · 출생년도")
                    .titleColor(HongColor.MAIN_ORANGE_100)
                    .firstOptionList((1980..2010).map { "${it}년" })
                    .useDimClickClose(true)
                    .onDismiss { showBottomSheet3 = false }
                    .onDirectSelect { selectedFirstOption, _ ->
                        Log.d("TAG", "select = $selectedFirstOption")
                    }
                    .applyOption(),
            )
        }
    }
}