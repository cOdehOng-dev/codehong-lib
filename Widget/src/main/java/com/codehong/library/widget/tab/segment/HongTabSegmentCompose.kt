package com.codehong.library.widget.tab.segment

import android.annotation.SuppressLint
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
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose

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
        targetValue = (selectedIndex * option.tabWidth).dp,
        animationSpec = tween(durationMillis = 300),
        label = "indicatorOffset"
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
                    radius = option.radius
                )
                .hongSpacing(option.padding)
        ) {
            HongTabSegmentIndicator(
                indicatorOffset = indicatorOffset,
                option = option
            )

            Row {
                option.tabTextList.forEachIndexed { index, text ->
                    HongTabSegmentItem(
                        option = option,
                        index = index,
                        text = text,
                        isSelected = selectedIndex == index,
                        onTabClick = { clickedIndex ->
                            selectedIndex = clickedIndex
                            option.tabClick?.invoke(clickedIndex)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun HongTabSegmentIndicator(
    indicatorOffset: androidx.compose.ui.unit.Dp,
    option: HongTabSegmentOption
) {
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
}

@Composable
private fun HongTabSegmentItem(
    option: HongTabSegmentOption,
    index: Int,
    text: String,
    isSelected: Boolean,
    onTabClick: (Int) -> Unit
) {
    val textColor = if (isSelected) {
        option.selectTabTextColorHex
    } else {
        option.unselectTabColorHex
    }

    val textTypo = if (isSelected) {
        option.selectTypo
    } else {
        option.unselectTypo
    }

    Box(
        modifier = Modifier
            .hongWidth(option.tabWidth)
            .hongHeight(option.tabHeight)
            .disableRippleClickable { onTabClick(index) },
        contentAlignment = Alignment.Center
    ) {
        HongTextCompose(
            option = HongTextBuilder()
                .text(text)
                .color(textColor)
                .typography(textTypo)
                .applyOption()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHongTabSegmentCompose() {
    val option = HongTabSegmentBuilder()
        .margin(HongSpacingInfo(left = 16f, top = 16f, right = 16f, bottom = 16f))
        .padding(HongSpacingInfo(left = 4f, top = 4f, right = 4f, bottom = 4f))
        .radius(HongRadiusInfo(all = 24))
        .backgroundColor(HongColor.GRAY_10)
        .tabTextList(listOf("추천", "계좌", "연락처"))
        .applyOption()

    HongTabSegmentCompose(option)
}

@Preview(showBackground = true)
@Composable
private fun PreviewHongTabSegmentComposeCustomStyle() {
    val option = HongTabSegmentBuilder()
        .margin(HongSpacingInfo(left = 16f, top = 16f, right = 16f, bottom = 16f))
        .padding(HongSpacingInfo(left = 4f, top = 4f, right = 4f, bottom = 4f))
        .radius(HongRadiusInfo(all = 12))
        .backgroundColor(HongColor.GRAY_20)
        .indicatorColor(HongColor.MAIN_ORANGE_100)
        .selectTabTextColor(HongColor.WHITE_100)
        .unselectTabTextColor(HongColor.GRAY_50)
        .selectTypo(HongTypo.BODY_14_B)
        .unselectTypo(HongTypo.BODY_14)
        .tabWidth(80)
        .tabHeight(36)
        .tabTextList(listOf("전체", "진행중", "완료"))
        .initialSelectIndex(1)
        .applyOption()

    HongTabSegmentCompose(option)
}