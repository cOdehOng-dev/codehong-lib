package com.codehong.library.widget.tab.scroll

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import kotlinx.coroutines.delay

@Composable
fun HongTabScrollCompose(
    option: HongTabScrollOption,
) {
    if (option.tabList.isEmpty() || option.tabTextList.isEmpty()) {
        return
    }

    val scrollState = rememberScrollState()
    var selectedIndex by remember { mutableIntStateOf(option.initialSelectIndex) }

    LaunchedEffect(selectedIndex) {
        delay(200)
        if (option.tabList.isNotEmpty()) {
            val centerOffset = (scrollState.maxValue / option.tabList.size) * selectedIndex
            scrollState.animateScrollTo(centerOffset)
        }
    }

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .hongSpacing(option.margin)
            .hongWidth(HongLayoutParam.MATCH_PARENT.value)
            .hongSpacing(
                HongSpacingInfo(
                    left = option.padding.left,
                    right = option.padding.right,
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        option.tabList.forEachIndexed { index, item ->
            HongTabScrollItem(
                option = option,
                index = index,
                item = item,
                isSelected = selectedIndex == index,
                isFirst = index == 0,
                isLast = index == option.tabList.size - 1,
                onTabClick = { clickedIndex, clickedItem ->
                    selectedIndex = clickedIndex
                    option.tabClick?.invoke(clickedIndex, clickedItem)
                }
            )
        }
    }
}

@Composable
private fun HongTabScrollItem(
    option: HongTabScrollOption,
    index: Int,
    item: Any,
    isSelected: Boolean,
    isFirst: Boolean,
    isLast: Boolean,
    onTabClick: (Int, Any) -> Unit
) {
    val startPadding = if (isFirst) 0.dp else (option.tabBetweenPadding / 2).dp
    val endPadding = if (isLast) 0.dp else (option.tabBetweenPadding / 2).dp

    val backgroundColor = if (isSelected) {
        option.selectBackgroundColorHex
    } else {
        option.unselectBackgroundColorHex
    }

    val borderInfo = HongBorderInfo(
        width = if (isSelected) option.selectBorderWidth else option.unselectBorderWidth,
        color = if (isSelected) option.selectBorderColorHex else option.unselectBorderColorHex
    )

    val textOption = if (isSelected) {
        HongTextBuilder()
            .text(option.tabTextList.getOrElse(index) { "" })
            .typography(option.selectTabTextTypo)
            .color(option.selectTabTextColorHex)
            .applyOption()
    } else {
        HongTextBuilder()
            .text(option.tabTextList.getOrElse(index) { "" })
            .typography(option.unselectTabTextTypo)
            .color(option.unselectTabTextColorHex)
            .applyOption()
    }

    Box(
        modifier = Modifier
            .padding(start = startPadding, end = endPadding)
            .hongBackground(
                color = backgroundColor,
                border = borderInfo,
                radius = option.radius
            )
            .padding(
                vertical = option.tabTextVerticalPadding.dp,
                horizontal = option.tabTextHorizontalPadding.dp
            )
            .disableRippleClickable { onTabClick(index, item) },
        contentAlignment = Alignment.Center
    ) {
        HongTextCompose(option = textOption)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHongTabScrollCompose() {
    val tabItems = listOf("Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5")
    val option = HongTabScrollBuilder()
        .tabList(tabItems)
        .tabTextList(tabItems)
        .selectTabTextTypo(HongTypo.BODY_14_B)
        .selectTabTextColor(HongColor.WHITE_100)
        .selectBackgroundColor(HongColor.MAIN_ORANGE_100)
        .selectBorderWidth(0)
        .unselectTabTextTypo(HongTypo.BODY_14_B)
        .unselectTabTextColor(HongColor.BLACK_100)
        .unselectBackgroundColor(HongColor.WHITE_100)
        .unselectBorderColor(HongColor.LINE)
        .unselectBorderWidth(1)
        .tabBetweenPadding(8)
        .tabTextHorizontalPadding(16)
        .tabTextVerticalPadding(8)
        .radius(HongRadiusInfo(all = 20))
        .initialSelectIndex(0)
        .padding(HongSpacingInfo(left = 16f, right = 16f))
        .applyOption()

    HongTabScrollCompose(option = option)
}

@Preview(showBackground = true)
@Composable
private fun PreviewHongTabScrollComposeOutline() {
    val tabItems = listOf("All", "Active", "Pending", "Completed")
    val option = HongTabScrollBuilder()
        .tabList(tabItems)
        .tabTextList(tabItems)
        .selectTabTextTypo(HongTypo.BODY_14_B)
        .selectTabTextColor(HongColor.MAIN_ORANGE_100)
        .selectBackgroundColor(HongColor.WHITE_100)
        .selectBorderColor(HongColor.MAIN_ORANGE_100)
        .selectBorderWidth(2)
        .unselectTabTextTypo(HongTypo.BODY_14)
        .unselectTabTextColor(HongColor.GRAY_50)
        .unselectBackgroundColor(HongColor.WHITE_100)
        .unselectBorderColor(HongColor.GRAY_20)
        .unselectBorderWidth(1)
        .tabBetweenPadding(12)
        .tabTextHorizontalPadding(20)
        .tabTextVerticalPadding(10)
        .radius(HongRadiusInfo(all = 8))
        .initialSelectIndex(1)
        .padding(HongSpacingInfo(left = 20f, right = 20f, top = 10f, bottom = 10f))
        .applyOption()

    HongTabScrollCompose(option = option)
}