package com.codehong.lib.sample.bottomsheet

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.bottomsheet.select.HongBottomSheetSelectBuilder
import com.codehong.library.widget.bottomsheet.select.HongBottomSheetSelectCompose
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.rule.color.HongColor

class SampleBottomSheetSelectActivity : BaseSampleComposeActivity() {


    @Composable
    override fun InitCompose() {
        var show1 by remember(false) { mutableStateOf(false) }
        var show2 by remember(false) { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hongBackground(
                    color = HongColor.WHITE_100
                ),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Button(
                    onClick = { show1 = true }
                ) {
                    Text("테스트 1")
                }

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { show2 = true }
                ) {
                    Text("테스트 2")
                }
            }

            HongBottomSheetSelectCompose(
                showBottomSheet = show1,
                option = HongBottomSheetSelectBuilder()
                    .title("선호 러닝")
                    .selectionList(
                        listOf(
                            Pair("실내 러닝", ""),
                            Pair("실외 러닝", "")
                        )
                    )
                    .initialSelection(
                        Pair("실외 러닝", "")
                    )
                    .selectSelectionCallback {
                        Log.d("TAG", "선택된 옵션: $it")
                    }
                    .onChangeVisibleState {
                        show1 = it
                    }
                    .applyOption()
            )

            HongBottomSheetSelectCompose(
                showBottomSheet = show2,
                option = HongBottomSheetSelectBuilder()
                    .title("신체 컨디션")
                    .selectionList(
                        listOf(
                            Pair("125%", "건너신 형소보다 활발 좋습니다."),
                            Pair("100%", "평소와 같은 상태입니다."),
                            Pair("75%", "몸이 무겁게 느껴집니다."),
                            Pair("50%", "피곤하고 기운이 없습니다."),
                            Pair("25%", "몸 상태가 매우 좋지 않습니다.")
                        )
                    )
                    .initialSelection(
                        Pair("75%", "몸이 무겁게 느껴집니다.")
                    )
                    .selectSelectionCallback {
                        Log.d("TAG", "선택된 옵션: $it")
                    }
                    .onChangeVisibleState {
                        show2 = it
                    }
                    .applyOption()
            )
        }
    }
}