package com.codehong.library.widget.tab.flow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HongTabFlowCompose(
    option: HongTabFlowOption
) {
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
            val isSelected = selectedIndex == index

            val endPadding = if (index == option.tabList.lastIndex || (index + 1) % option.maxRowCount == 0) {
                0.dp
            } else {
                option.betweenTabSpacing.dp
            }

            Box(
                modifier = Modifier
                    .padding(end = endPadding, bottom = option.rowSpacing.dp)
                    .height(36.dp)
                    .hongBackground(
                        color = if (isSelected) option.selectBackgroundColorHex else option.unselectTabBackgroundColorHex,
                        border = if (isSelected) option.selectedBorder else option.unselectedBorder,
                        radius = option.tabRadius
                    )
                    .clickable {
                        selectedIndex = index
                        option.onSelect?.invoke(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                HongTextCompose(
                    HongTextBuilder()
                        .padding(
                            HongSpacingInfo(
                                left = 21f,
                                right = 21f,
                                top = 8f,
                                bottom = 8f
                            )
                        )
                        .text(text)
                        .color(if (isSelected) option.selectTextColorHex else option.unselectTextColorHex)
                        .typography(if (isSelected) option.selectTextTypo else option.unselectTextTypo)
                        .applyOption()
                )
            }
        }
    }
}