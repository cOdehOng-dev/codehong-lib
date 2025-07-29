package com.codehong.library.widget.label.input

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.label.HongLabelBuilder2
import com.codehong.library.widget.label.HongLabelViewCompose2
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldCompose
import com.codehong.library.widget.textfield.HongTextFieldOption
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
            HongLabelViewCompose2(
                HongLabelBuilder2()
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
            HongTextFieldCompose(option.textFieldOption)

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
        .textFieldOption(
            HongTextFieldBuilder()
                .copy(HongLabelInputOption.DEFAULT_TEXT_FIELD)
                .margin(
                    HongSpacingInfo(
                        top = 10f
                    )
                )
                .placeholderTextOption(
                    HongTextBuilder()
                        .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                        .applyOption()
                )
                .inputTextOption(
                    HongTextBuilder()
                        .copy(HongTextFieldOption.DEFAULT_INPUT)
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .typography(HongTypo.BODY_14)
                        .color(HongColor.BLACK_100)
                        .applyOption()
                )
                .applyOption()
        )
        .applyOption()

    HongLabelInputCompose(option)
}