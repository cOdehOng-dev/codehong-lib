package com.codehong.library.widget.tab.segment

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.disableRippleClickable
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.text.label.HongTextBuilder
import com.codehong.library.widget.text.label.HongTextCompose

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun HongTabSegmentCompose(
    option: HongTabSegmentOption
) {
    if (option.tabTextList.isEmpty()) {
        return
    }

    var selectedIndex by remember { mutableIntStateOf(option.initialSelectIndex) }
    val indicatorOffset by animateDpAsState(
        targetValue = (selectedIndex * 100).dp,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .hongWidth(option.width)
            .hongHeight(option.height),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .hongSpacing(option.margin)
                .hongBackground(
                    color = option.backgroundColorHex,
                    radius = option.radius,
                )
                .hongSpacing(option.padding)
        ) {
            // 인디케이터
            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .hongBackground(
                        color = option.indicatorColorHex,
                        radius = option.radius
                    )
                    .hongWidth(option.tabWidth)
                    .hongHeight(option.tabHeight)
            )

            Row {
                option.tabTextList.forEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .hongWidth(option.tabWidth)
                            .hongHeight(option.tabHeight)
                            .disableRippleClickable {
                                selectedIndex = index
                                option.tabClick?.invoke(index)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        HongTextCompose(
                            option = HongTextBuilder()
                                .text(text)
                                .color(
                                    if (selectedIndex == index) option.selectTextColorHex
                                    else option.unselectTabColorHex
                                )
                                .typography(
                                    if (selectedIndex == index) option.selectTypo
                                    else option.unselectTypo
                                )
                                .applyOption()
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongTabSegmentCompose() {
    val option = HongTabSegmentBuilder()
        .margin(
            HongSpacingInfo(
                left = 16f,
                top = 16f,
                right = 16f,
                bottom = 16f
            )
        )
        .padding(
            HongSpacingInfo(
                left = 4f,
                top = 4f,
                right = 4f,
                bottom = 4f
            )
        )
        .radius(
            HongRadiusInfo(
                topLeft = 24,
                topRight = 24,
                bottomLeft = 24,
                bottomRight = 24
            )
        )
        .backgroundColor(HongColor.GRAY_10)
        .tabTextList(listOf("추천", "계좌", "연락처"))
        .onTabClick {
            Log.d("TAG", "selected tab index: $it")
        }
        .applyOption()

    HongTabSegmentCompose(
        option
    )
}