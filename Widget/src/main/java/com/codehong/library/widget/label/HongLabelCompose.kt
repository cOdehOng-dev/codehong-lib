package com.codehong.library.widget.label

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongLabelViewCompose(
    option: HongLabelOption
) {
    if (option.labelTextOption.text.isNullOrEmpty()) return

    HongWidgetContainer(option) {
        Column(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
        ) {
            HongTextCompose(option.labelTextOption)

            if (!option.descriptionTextOption.text.isNullOrEmpty())  {
                HongTextCompose(option.descriptionTextOption)

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongLabelViewCompose() {
    val option = HongLabelBuilder()
        .backgroundColor(HongColor.WHITE_100)
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f,
                right = 20f,
                bottom = 20f
            )
        )
        .labelTextOption(
            HongTextBuilder()
                .copy(HongLabelOption.DEFAULT_LABEL_OPTION)
                .text("text align")
                .applyOption()
        )
        .descriptionTextOption(
            HongTextBuilder()
                .copy(HongLabelOption.DEFAULT_DESCRIPTION_OPTION)
                .text("width가 MATCH_PARENT인 경우, textAlign이 적용되지 않습니다.")
                .applyOption()
        )
        .applyOption()

    HongLabelViewCompose(option)

}