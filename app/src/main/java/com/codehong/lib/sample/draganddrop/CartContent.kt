package com.codehong.lib.sample.draganddrop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.draganddrop.model.BreadItem
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.button.text.HongButtonTextCompose
import com.codehong.library.widget.draganddrop.DropTarget
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.util.dpToSp
import java.text.DecimalFormat


@Composable
fun CartContent() {
    val breadList = remember { mutableStateListOf<BreadItem>() }

    DropTarget<BreadItem>(
        modifier = Modifier
            .fillMaxWidth()
    ) { isInBound, breadItem ->
        // isInBound 값으로 배경색 변경
        val bgColor = if (isInBound) {
            HongColor.MAIN_ORANGE_25.toColor()
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
