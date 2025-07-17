package com.codehong.lib.sample.label.select

import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.label.select.HongLabelSelectInputBuilder
import com.codehong.library.widget.label.select.HongLabelSelectInputCompose
import com.codehong.library.widget.label.select.HongLabelSelectInputView
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongLayoutParam.Companion.toHongLayoutValueToParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.util.Const

class SampleLabelSelectInputActivity : BaseSampleMixActivity() {

    private val testList = listOf("테스트1", "테스트2", "테스트3", "테스트4")
    private val option1 = HongLabelSelectInputBuilder()
        .padding(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .label("테스트 옵션 [기본_인풋 없음]")
        .buttonText(testList.first())
        .selectList(testList)
        .selectPosition(0)
        .pickerCallback { s, i ->
            Log.e("TAG", "선택된 값 = $s")
        }
        .applyOption()

    private val option2 = HongLabelSelectInputBuilder()
        .padding(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .label("테스트 옵션 [자동 적용_인풋 없음]")
        .buttonText(testList.first())
        .selectList(testList)
        .selectPosition(0)
        .useDirectCallback(true)
        .pickerCallback { s, i ->
            Log.e("TAG", "선택된 값 = $s")
        }
        .applyOption()

    private val widthHeightSizeList = listOf(
        HongLayoutParam.MATCH_PARENT.paramName,
        HongLayoutParam.WRAP_CONTENT.paramName,
        Const.DIRECT_INPUT
    )
    private val testWidth = 1
    private val initialWidth = testWidth.toHongLayoutValueToParam().ifEmpty { Const.DIRECT_INPUT }

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongLabelSelectInputView(this@SampleLabelSelectInputActivity).setSelectView(it))
            }
            add(
                HongLabelSelectInputView(this@SampleLabelSelectInputActivity).apply {
                    setSelectInputView(
                        HongLabelSelectInputBuilder()
                            .padding(
                                HongSpacingInfo(
                                    top = 20f,
                                    left = 20f,
                                    right = 20f,
                                    bottom = 20f
                                )
                            )
                            .label("테스트 옵션 [기본_인풋 있음]")
                            .description("width를 선택해주세요.")
                            .buttonText(initialWidth)
                            .inputText(
                                if (initialWidth == Const.DIRECT_INPUT) {
                                    testWidth.toString()
                                } else {
                                    initialWidth
                                }
                            )
                            .selectList(widthHeightSizeList)
                            .selectPosition(widthHeightSizeList.indexOf(initialWidth.ifEmpty { Const.DIRECT_INPUT }))
                            .useDirectCallback(true)
                            .useOnlyNumber(true)
                            .showInput(initialWidth == Const.DIRECT_INPUT)
                            .inputCallback { inputSize ->
                                val selectWidthSize = if (inputSize.isNullOrEmpty()) {
                                    testWidth
                                } else {
                                    inputSize.toInt()
                                }
                                Log.e("TAG", "[테스트] Input selectWidthSize = $selectWidthSize")
                            }
                            .pickerCallback { selectSize, index ->
                                Log.e("TAG", "[테스트] Picker selectSize = $selectSize")
                                if (selectSize == HongLayoutParam.MATCH_PARENT.paramName
                                    || selectSize == HongLayoutParam.WRAP_CONTENT.paramName
                                ) {
                                    hideInput()
                                } else {
                                    showInput()
                                    setInputText("200")
                                }
                            }
                            .applyOption()
                    )
                }
            )
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongLabelSelectInputCompose(it)
        }

        var option3 by rememberSaveable(HongLabelSelectInputBuilder().applyOption()) {
            mutableStateOf(HongLabelSelectInputBuilder().applyOption())
        }

        option3 = HongLabelSelectInputBuilder()
            .padding(
                HongSpacingInfo(
                    top = 20f,
                    left = 20f,
                    right = 20f,
                    bottom = 20f
                )
            )
            .label("테스트 옵션 [기본_인풋 있음]")
            .description("width를 선택해주세요.")
            .buttonText(initialWidth)
            .inputText(
                if (initialWidth == Const.DIRECT_INPUT) {
                    testWidth.toString()
                } else {
                    initialWidth
                }
            )
            .selectList(widthHeightSizeList)
            .selectPosition(widthHeightSizeList.indexOf(initialWidth.ifEmpty { Const.DIRECT_INPUT }))
            .useDirectCallback(true)
            .useOnlyNumber(true)
            .showInput(initialWidth == Const.DIRECT_INPUT)
            .inputCallback { inputSize ->
                val selectWidthSize = if (inputSize.isNullOrEmpty()) {
                    testWidth
                } else {
                    inputSize.toInt()
                }
                Log.e("TAG", "[테스트] Input selectWidthSize = $selectWidthSize")
            }
            .pickerCallback { selectSize, index ->
                Log.e("TAG", "[테스트] Picker selectSize = $selectSize")
                if (selectSize == HongLayoutParam.MATCH_PARENT.paramName
                    || selectSize == HongLayoutParam.WRAP_CONTENT.paramName
                ) {
                    option3.showInput = false

                } else {
                    option3.showInput = true
                    option3.input = "200"
                }
            }
            .applyOption()

        HongLabelSelectInputCompose(option3)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(700.dp)
        )
    }
}