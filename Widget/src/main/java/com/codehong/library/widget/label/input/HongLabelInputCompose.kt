package com.codehong.library.widget.label.input

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.label.def.HongLabelBuilder
import com.codehong.library.widget.label.def.HongLabelViewCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongLabelInputCompose(
    option: HongLabelInputOption
) {

    HongWidgetContainer(option) {
        Column(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
        ) {
            HongLabelViewCompose(
                HongLabelBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .backgroundColor(HongColor.TRANSPARENT)
                    .label(option.label)
                    .labelColor(option.labelColorHex)
                    .labelTypo(option.labelTypo)
                    .description(option.description)
                    .descriptionColor(option.descriptionColorHex)
                    .descriptionTypo(option.descriptionTypo)
                    .applyOption()
            )
            HongTextFieldCompose(
                HongTextFieldBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .height(48)
                    .margin(
                        HongSpacingInfo(
                            top = 10f
                        )
                    )
                    .radius(
                        HongRadiusInfo(
                            topLeft = 10,
                            topRight = 10,
                            bottomLeft = 10,
                            bottomRight = 10
                        )
                    )
                    .padding(
                        HongSpacingInfo(
                            top = 11f,
                            bottom = 11f,
                            left = 10f,
                            right = 10f
                        )
                    )
                    .input(option.input)
                    .inputTypo(option.inputTypo)
                    .inputColor(option.inputColorHex)
                    .placeholderPadding(
                        HongSpacingInfo(
                            top = 4f,
                            bottom = 4f,
                            left = 4f,
                            right = 4f
                        )
                    )
                    .placeholder(option.placeholder)
                    .placeholderTypo(option.placeholderTypo)
                    .placeholderColor(option.placeholderColorHex)
                    .clearIconSize(option.clearIconSize)
                    .clearIconRes(option.clearIconRes)
                    .clearIconScaleType(option.clearIconScaleType)
                    .clearIconMargin(option.clearIconMargin)
                    .keyboardOption(option.keyboardOption)
                    .onTextChanged(option.onTextChanged)
                    .applyOption()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongLabelInputCompose() {
    val option = HongLabelInputBuilder()
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
        .applyOption()

    HongLabelInputCompose(option)
}