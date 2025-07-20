package com.codehong.library.widget.text.check

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongCheckTextCompose(
    option: HongCheckTextOption
) {
    HongWidgetContainer(option) {
        Row(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var isChecked by rememberSaveable(option.checkState) { mutableStateOf(option.checkState) }

            HongImageCompose(
                HongImageBuilder()
                    .width(option.checkSize)
                    .height(option.checkSize)
                    .margin(
                        HongSpacingInfo(
                            right = 8f
                        )
                    )
                    .drawableResId(R.drawable.honglib_ic_check)
                    .imageColor(
                        if (isChecked) option.checkColor
                        else option.uncheckColor
                    )
                    .onClick {
                        isChecked = !isChecked
                        option.onCheck?.invoke(isChecked)
                    }
                    .applyOption()
            )

            HongTextCompose(option.textOption)

            HongImageCompose(
                HongImageBuilder()
                    .width(option.arrowSize)
                    .height(option.arrowSize)
                    .drawableResId(R.drawable.honglib_ic_arrow_right)
                    .imageColor(option.textOption.colorHex)
                    .scaleType(HongScaleType.CENTER_INSIDE)
                    .applyOption()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongCheckTextCompose() {
    val option = HongCheckTextBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f
            )
        )
        .checkSize(30)
        .arrowSize(20)
        .text("휴대폰/카드 본인확인 서비스")
        .textOption(
            HongTextBuilder()
                .copy(HongCheckTextOption.DEFAULT_TEXT_OPTION)
                .typography(HongTypo.BODY_15)
                .color(HongColor.GRAY_70)
                .applyOption()
        )
        .checkState(true)
        .onClick {}
        .onCheck {}
        .applyOption()

    HongCheckTextCompose(option)
}