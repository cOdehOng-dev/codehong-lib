package com.codehong.library.widget.tab.flow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HongTabFlowCompose(
    option: HongTabFlowOption
) {
    if (option.tabList.isEmpty()) {
        return
    }

    var selectedIndex by remember(option.initialSelectedIndex) { mutableIntStateOf(option.initialSelectedIndex) }

    FlowRow(
        modifier = Modifier
            .hongSpacing(option.margin)
            .fillMaxWidth(),
        maxItemsInEachRow = option.maxRowCount,
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center
    ) {
        option.tabList.forEachIndexed { index, text ->
            HongTabFlowItem(
                option = option,
                index = index,
                text = text,
                isSelected = selectedIndex == index,
                isLastInRow = index == option.tabList.lastIndex || (index + 1) % option.maxRowCount == 0,
                onTabClick = { clickedIndex ->
                    selectedIndex = clickedIndex
                    option.onSelect?.invoke(clickedIndex)
                }
            )
        }
    }
}

@Composable
private fun HongTabFlowItem(
    option: HongTabFlowOption,
    index: Int,
    text: String,
    isSelected: Boolean,
    isLastInRow: Boolean,
    onTabClick: (Int) -> Unit
) {
    val endPadding = if (isLastInRow) 0.dp else option.betweenTabSpacing.dp

    val backgroundColor = if (isSelected) {
        option.selectBackgroundColorHex
    } else {
        option.unselectTabBackgroundColorHex
    }

    val borderInfo = if (isSelected) {
        option.selectedBorder
    } else {
        option.unselectedBorder
    }

    val textColor = if (isSelected) {
        option.selectTextColorHex
    } else {
        option.unselectTextColorHex
    }

    val textTypo = if (isSelected) {
        option.selectTextTypo
    } else {
        option.unselectTextTypo
    }

    Box(
        modifier = Modifier
            .padding(end = endPadding, bottom = option.rowSpacing.dp)
            .hongBackground(
                color = backgroundColor,
                border = borderInfo,
                radius = option.tabRadius
            )
            .disableRippleClickable { onTabClick(index) },
        contentAlignment = Alignment.Center
    ) {
        HongTextCompose(
            option = HongTextBuilder()
                .padding(
                    HongSpacingInfo(
                        left = option.padding.left.takeIf { it > 0 } ?: 21f,
                        right = option.padding.right.takeIf { it > 0 } ?: 21f,
                        top = option.padding.top.takeIf { it > 0 } ?: 8f,
                        bottom = option.padding.bottom.takeIf { it > 0 } ?: 8f
                    )
                )
                .text(text)
                .color(textColor)
                .typography(textTypo)
                .applyOption()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHongTabFlowCompose() {
    val option = HongTabFlowBuilder()
        .tabList(listOf("전체", "인기", "최신", "추천", "이벤트", "할인"))
        .initialSelectedIndex(0)
        .maxRowCount(3)
        .betweenTabSpacing(10)
        .rowSpacing(10)
        .tabRadius(HongRadiusInfo(all = 18))
        .selectBackgroundColor(HongColor.WHITE_100)
        .unselectTabBackgroundColor(HongColor.WHITE_100)
        .selectedBorder(HongBorderInfo(width = 2, color = HongColor.BLACK_80.hex))
        .unselectedBorder(HongBorderInfo(width = 1, color = HongColor.GRAY_30.hex))
        .selectTextColor(HongColor.BLACK_100)
        .unselectTextColor(HongColor.BLACK_100)
        .selectTextTypo(HongTypo.BODY_16_B)
        .unselectTextTypo(HongTypo.BODY_16)
        .margin(HongSpacingInfo(left = 16f, right = 16f, top = 16f, bottom = 16f))
        .applyOption()

    HongTabFlowCompose(option = option)
}

@Preview(showBackground = true)
@Composable
private fun PreviewHongTabFlowComposeFilledStyle() {
    val option = HongTabFlowBuilder()
        .tabList(listOf("Option 1", "Option 2", "Option 3", "Option 4"))
        .initialSelectedIndex(1)
        .maxRowCount(2)
        .betweenTabSpacing(8)
        .rowSpacing(8)
        .tabRadius(HongRadiusInfo(all = 8))
        .selectBackgroundColor(HongColor.MAIN_ORANGE_100)
        .unselectTabBackgroundColor(HongColor.GRAY_10)
        .selectedBorder(HongBorderInfo(width = 0, color = HongColor.TRANSPARENT.hex))
        .unselectedBorder(HongBorderInfo(width = 0, color = HongColor.TRANSPARENT.hex))
        .selectTextColor(HongColor.WHITE_100)
        .unselectTextColor(HongColor.GRAY_50)
        .selectTextTypo(HongTypo.BODY_14_B)
        .unselectTextTypo(HongTypo.BODY_14)
        .margin(HongSpacingInfo(left = 16f, right = 16f, top = 16f, bottom = 16f))
        .applyOption()

    HongTabFlowCompose(option = option)
}