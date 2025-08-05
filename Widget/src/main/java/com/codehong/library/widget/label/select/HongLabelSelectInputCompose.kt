package com.codehong.library.widget.label.select

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonCompose
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelViewCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongLayoutParam.Companion.toHongLayoutValueToParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldCompose
import com.codehong.library.widget.util.Const
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.util.picker.OptionPickerDialog

@Composable
fun HongLabelSelectInputCompose(
    option: HongLabelSelectInputOption
) {
    HongWidgetContainer(option) {
        Column(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
        ) {
            val context = LocalContext.current
            var currentPosition by rememberSaveable(option.selectPosition) { mutableIntStateOf(option.selectPosition) }
            var initial by rememberSaveable(option.textButtonOption.textOption.text) { mutableStateOf(option.textButtonOption.textOption.text) }

            HongLabelViewCompose(
                HongLabelBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .backgroundColor(HongColor.TRANSPARENT)
                    .label(option.label)
                    .labelTypo(option.labelTypo)
                    .labelColor(option.labelColorHex)
                    .description(option.description)
                    .descriptionTypo(option.descriptionTypo)
                    .descriptionColor(option.descriptionColorHex)
                    .applyOption()
            )

            val textButtonOption = HongTextButtonBuilder()
                .copy(option.textButtonOption)
                .textOption(
                    HongTextBuilder()
                        .color(option.buttonTextColorHex)
                        .typography(option.buttonTextTypo)
                        .text(if (!initial.isNullOrEmpty()) initial else "")
                        .applyOption()
                )
                .onClick {
                    OptionPickerDialog(
                        context,
                        title = "${option.label} 옵션 선택",
                        optionList = option.selectList,
                        selectedPosition = currentPosition,
                        useDirectCallback = option.useDirectCallback
                    ) { selectOption, index ->
                        currentPosition = index
                        initial = option.selectList.firstOrNull { it == selectOption }
                        option.pickerCallback?.invoke(
                            selectOption,
                            option.selectList.indexOf(selectOption)
                        )
                    }.show()
                }
                .applyOption()

            HongTextButtonCompose(textButtonOption)

            if (option.showInput) {
                val textFieldOption = HongTextFieldBuilder()
                    .copy(option.textFieldOption)
                    .margin(
                        HongSpacingInfo(
                            top = 10f
                        )
                    )
                    .input(option.input)
                    .placeholder(option.placeholder)
                    .keyboardOption(
                        Pair(
                            if (option.useOnlyNumber) HongKeyboardType.NUMBER else HongKeyboardType.TEXT,
                            HongKeyboardActionType.DONE
                        )
                    )
                    .onTextChanged {
                        option.inputCallback?.invoke(it)
                    }
                    .applyOption()
                HongTextFieldCompose(textFieldOption)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongLabelSelectCompose() {
    val testList = listOf("테스트1", "테스트2", "테스트3", "테스트4")
    val option1 = HongLabelSelectInputBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .label("테스트 옵션 [기본]")
        .buttonText(testList.first())
        .selectList(testList)
        .selectPosition(0)
        .pickerCallback { s, i -> }
        .applyOption()


    val widthHeightSizeList = listOf(
        HongLayoutParam.MATCH_PARENT.paramName,
        HongLayoutParam.WRAP_CONTENT.paramName,
        Const.DIRECT_INPUT
    )
    val width = 1
    val initialWidth =
        width.toHongLayoutValueToParam().ifEmpty { Const.DIRECT_INPUT }

    val option2 = HongLabelSelectInputBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .label("width")
        .description("width를 선택해주세요.")
        .buttonText(initialWidth)
        .inputText(
            if (initialWidth == Const.DIRECT_INPUT) {
                width.toString()
            } else {
                initialWidth
            }
        )
        .selectList(widthHeightSizeList)
        .selectPosition(widthHeightSizeList.indexOf(initialWidth.ifEmpty { Const.DIRECT_INPUT }))
        .useDirectCallback(true)
        .useOnlyNumber(true)
        .showInput(initialWidth == Const.DIRECT_INPUT)
        .inputCallback { inputSize -> }
        .pickerCallback { selectSize, index -> }
        .applyOption()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(HongColor.WHITE_100.toColor())
    ) {
        HongLabelSelectInputCompose(option1)
        HongLabelSelectInputCompose(option2)
    }
}