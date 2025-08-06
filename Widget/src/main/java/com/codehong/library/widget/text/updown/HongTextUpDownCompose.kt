package com.codehong.library.widget.text.updown

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.MarginLeftOrRight
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.extensions.toFigureStringCoverZero
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.unit.HongTextUnitBuilder
import com.codehong.library.widget.text.unit.HongTextUnitCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongTextUpDownCompose(
    option: HongTextUpDownOption
) {
    HongWidgetContainer(option) {
        var amount by rememberSaveable(option.amount) { mutableIntStateOf(option.amount) }
        Row(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HongImageCompose(
                HongImageBuilder()
                    .width(option.buttonSize)
                    .height(option.buttonSize)
                    .border(
                        HongBorderInfo(
                            width = 1,
                            color = option.borderColorHex
                        )
                    )
                    .drawableResId(R.drawable.honglib_ic_minus)
                    .onClick {
                        amount -= option.gap
                        option.onResult(amount)
                    }
                    .useShapeCircle(true)
                    .scaleType(HongScaleType.CENTER_CROP)
                    .imageColor(option.iconColorHex)
                    .applyOption()
            )
            MarginLeftOrRight(option.spaceButtonAndDisplay)
            HongTextUnitCompose(
                HongTextUnitBuilder()
                    .width(HongLayoutParam.WRAP_CONTENT.value)
                    .height(HongLayoutParam.WRAP_CONTENT.value)
                    .textOption(
                        HongTextBuilder()
                            .width(HongLayoutParam.WRAP_CONTENT.value)
                            .height(HongLayoutParam.WRAP_CONTENT.value)
                            .text(amount.toFigureStringCoverZero())
                            .typography(option.displayTypo)
                            .color(option.displayColorHex)
                            .applyOption()
                    )
                    .useNumberDecimal(option.useDecimal)
                    .unitText(option.unit)
                    .applyOption()
            )
            MarginLeftOrRight(option.spaceButtonAndDisplay)
            HongImageCompose(
                HongImageBuilder()
                    .width(option.buttonSize)
                    .height(option.buttonSize)
                    .border(
                        HongBorderInfo(
                            width = 1,
                            color = option.borderColorHex
                        )
                    )
                    .drawableResId(R.drawable.honglib_ic_plus)
                    .onClick {
                        amount += option.gap
                        option.onResult(amount)
                    }
                    .useShapeCircle(true)
                    .scaleType(HongScaleType.CENTER_CROP)
                    .imageColor(option.iconColorHex)
                    .applyOption()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongTextUpDownCompose() {
    val option = HongTextUpDownBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                top = 10f,
                bottom = 10f
            )
        )
        .amount(7)
        .unit("장")
        .spaceButtonAndDisplay(8)
        .gap(1)
        .applyOption()
    HongTextUpDownCompose(option)
}