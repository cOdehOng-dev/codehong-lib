package com.codehong.library.widget.label.def

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.label.HongTextBuilder
import com.codehong.library.widget.text.label.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongLabelViewCompose(
    option: HongLabelOption
) {
    if (option.label.isNullOrEmpty()) return

    HongWidgetContainer(option) {
        Column(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
        ) {
            HongTextCompose(
                HongTextBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .text(option.label)
                    .typography(option.labelTypo)
                    .color(option.labelColorHex)
                    .applyOption()
            )

            if (!option.description.isNullOrEmpty())  {
                HongTextCompose(
                    HongTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .margin(
                            HongSpacingInfo(
                                top = 2f
                            )
                        )
                        .text(option.description)
                        .typography(option.descriptionTypo)
                        .color(option.descriptionColorHex)
                        .applyOption()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongLabelViewCompose2() {
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
        .label("text align")
        .description("width가 MATCH_PARENT인 경우, textAlign이 적용되지 않습니다.")
        .applyOption()

    HongLabelViewCompose(option)

}