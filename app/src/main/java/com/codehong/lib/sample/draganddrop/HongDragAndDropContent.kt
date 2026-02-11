package com.codehong.lib.sample.draganddrop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.R
import com.codehong.lib.sample.draganddrop.model.BreadItem
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.button.text.HongButtonTextCompose
import com.codehong.library.widget.draganddrop.HongDragTarget
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.image.def.HongImageBuilder
import com.codehong.library.widget.image.def.HongImageCompose
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import com.codehong.library.widget.util.dpToSp
import java.text.DecimalFormat

@Composable
fun BreadItemContent(
    breadItem: BreadItem
) {
    Box(
        modifier = Modifier
            .background(
                color = Color.White
            )
            .border(
                width = 1.dp,
                color = colorResource(R.color.color_0A000000)
            )
    ) {
        Column {
            HongImageCompose(
                option = HongImageBuilder()
                    .width(120)
                    .height(120)
                    .backgroundColor("0A000000")
                    .scaleType(HongScaleType.CENTER_INSIDE)
                    .imageInfo(breadItem.image)
                    .applyOption()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            ) {
                HongTextCompose(
                    option = HongTextBuilder()
                        .width(HongLayoutParam.WRAP_CONTENT.value)
                        .text(breadItem.storeName)
                        .typography(HongTypo.CONTENTS_12_B)
                        .color(HongColor.BLACK_100)
                        .applyOption()
                )

                Spacer(modifier = Modifier.height(2.dp))

                HongTextCompose(
                    option = HongTextBuilder()
                        .width(HongLayoutParam.WRAP_CONTENT.value)
                        .text(breadItem.prdName)
                        .typography(HongTypo.BODY_13)
                        .color(HongColor.BLACK_100)
                        .applyOption()
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row {
                    if (breadItem.discountPer.isNotEmpty()) {
                        HongTextCompose(
                            option = HongTextBuilder()
                                .width(HongLayoutParam.WRAP_CONTENT.value)
                                .text("${breadItem.discountPer}%")
                                .typography(HongTypo.BODY_15_B)
                                .color(HongColor.RED_100)
                                .applyOption()
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                    HongTextCompose(
                        option = HongTextBuilder()
                            .width(HongLayoutParam.WRAP_CONTENT.value)
                            .text("${DecimalFormat("#,###").format(breadItem.price.toLong())}원")
                            .typography(HongTypo.BODY_15_B)
                            .color(HongColor.BLACK_100)
                            .applyOption()
                    )

                }
            }
        }
    }
}


@Composable
fun CartContent(
    inboundColorHex: String
) {
    val breadList = remember { mutableStateListOf<BreadItem>() }

    HongDragTarget<BreadItem>(
        modifier = Modifier
            .fillMaxWidth()
    ) { isInBound, breadItem ->
        val bgColor = if (isInBound) {
            inboundColorHex.toColor()
        } else {
            Color.White
        }

        breadItem?.let {
            if (isInBound) {
                breadList.add(breadItem)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    bgColor
                )
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier
                    .weight(1f),
                text = "${DecimalFormat("#,###").format(breadList.sumOf { it.price }.toLong())}원",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = pretendardFontFamily,
                    fontWeight = FontWeight.W700,
                    fontSize = dpToSp(dp = 16),
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )

            HongButtonTextCompose(
                HongButtonTextBuilder()
                    .width(200)
                    .height(50)
                    .backgroundColor(
                        if (breadList.isNotEmpty()) HongColor.MAIN_ORANGE_100 else HongColor.GRAY_30
                    )
                    .padding(
                        HongSpacingInfo(
                            top = 8f,
                            bottom = 8f
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
                    .text("${breadList.size}개 빵 바로 주문하기")
                    .textTypo(HongTypo.BODY_15_B)
                    .textColor(if (breadList.isNotEmpty()) HongColor.BLACK_100 else HongColor.GRAY_60)
                    .onClick {}
                    .applyOption()
            )
        }
    }
}
