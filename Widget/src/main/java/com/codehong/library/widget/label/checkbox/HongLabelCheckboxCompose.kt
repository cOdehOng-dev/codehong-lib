package com.codehong.library.widget.label.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.checkbox.HongCheckBoxCompose
import com.codehong.library.widget.checkbox.HongCheckboxBuilder
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.label.def.HongLabelBuilder
import com.codehong.library.widget.label.def.HongLabelCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongPosition
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.util.HongWidgetNoneClickContainer

@Composable
fun HongLabelCheckboxCompose(
    option: HongLabelCheckboxOption
) {
    HongWidgetNoneClickContainer(option) {
        Row(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (option.checkboxPosition == HongPosition.LEFT) {
                HongCheckBoxCompose(option.checkboxOption)
                Spacer(
                    modifier = Modifier
                        .size(HongLabelCheckboxOption.DEFAULT_BETWEEN_SPACER.dp)
                )
            }
            HongLabelCompose(
                HongLabelBuilder()
                    .width(HongLayoutParam.WRAP_CONTENT.value)
                    .backgroundColor(HongColor.TRANSPARENT)
                    .label(option.label)
                    .labelColor(option.labelColorHex)
                    .labelTypo(option.labelTypo)
                    .description(option.description)
                    .descriptionColor(option.descriptionColorHex)
                    .descriptionTypo(option.descriptionTypo)
                    .applyOption()
            )
            if (option.checkboxPosition != HongPosition.LEFT) {
                Spacer(
                    modifier = Modifier
                        .size(HongLabelCheckboxOption.DEFAULT_BETWEEN_SPACER.dp)
                )
                HongCheckBoxCompose(option.checkboxOption)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongLabelCheckboxCompose() {
    val option = HongLabelCheckboxBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f
            )
        )
        .label("테스트")
        .description("테스트 입니다요")
        .checkboxSize(24)
        .checkState(true)
        .checkboxOption(
            HongCheckboxBuilder()
                .applyOption()
        )
        .applyOption()

    val option2 = HongLabelCheckboxBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f
            )
        )
        .label("테스트")
        .description("테스트 입니다요")
        .checkboxSize(20)
        .checkState(false)
        .applyOption()

    val option3 = HongLabelCheckboxBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f
            )
        )
        .label("테스트")
        .description("테스트 입니다요")
        .checkboxSize(24)
        .checkState(true)
        .checkboxPosition(HongPosition.RIGHT)
        .checkboxOption(
            HongCheckboxBuilder()
                .applyOption()
        )
        .applyOption()

    val option4 = HongLabelCheckboxBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f
            )
        )
        .label("테스트")
        .description("테스트 입니다요")
        .checkboxSize(20)
        .checkState(false)
        .checkboxPosition(HongPosition.RIGHT)
        .applyOption()

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        HongLabelCheckboxCompose(option)
//        HongLabelCheckboxCompose(option2)
//        HongLabelCheckboxCompose(option3)
//        HongLabelCheckboxCompose(option4)
    }
}