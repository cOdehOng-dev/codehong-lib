package com.codehong.library.widget.tab

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
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.disableRippleClickable
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import kotlinx.coroutines.delay

@Composable
fun HongScrollTabCompose(
    option: HongScrollTabOption,
) {
    val scrollState = rememberScrollState()

    var selectedIndex by remember { mutableIntStateOf(option.initialSelectIndex) }

    LaunchedEffect(selectedIndex) {
        delay(200)
        val centerOffset = (scrollState.maxValue / option.tabList.size) * selectedIndex
        scrollState.animateScrollTo(centerOffset)
    }

    Box(
        modifier = Modifier
            .padding(
                start = option.margin.left.dp,
                top = option.margin.top.dp,
                end = option.margin.right.dp,
                bottom = option.margin.bottom.dp
            )
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .hongWidth(option.width)
                .hongHeight(option.height)
                .padding(
                    start = option.padding.left.dp,
                    end = option.padding.right.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            option.tabList.forEachIndexed { i, item ->
                val isSelected = selectedIndex == i

                Row(
                    modifier = Modifier
                        .padding(
                            start = if (i == 0) {
                                0.dp
                            } else {
                                (option.tabBetweenPadding / 2).dp
                            },
                            end = if (i == option.tabList.size - 1) {
                                0.dp
                            } else {
                                (option.tabBetweenPadding / 2).dp
                            }
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .hongWidth(
                                if (isSelected) {
                                    option.selectTabTextOption.width
                                } else {
                                    option.unselectTabTextOption.width
                                }
                            )
                            .hongHeight(
                                if (isSelected) {
                                    option.selectTabTextOption.height
                                } else {
                                    option.unselectTabTextOption.height
                                }
                            )
                            .hongBackground(
                                backgroundColor = if (isSelected) {
                                    option.selectBackgroundColor
                                } else {
                                    option.unselectBackgroundColor
                                },
                                border = HongBorderInfo(
                                    width = if (isSelected) {
                                        0
                                    } else {
                                        option.borderWidth
                                    },
                                    color = if (isSelected) {
                                        option.selectBorderColor
                                    } else {
                                        option.unselectBorderColor
                                    }
                                ),
                                radius = option.radius
                            )
                            .padding(
                                vertical = option.tabTextVerticalPadding.dp,
                                horizontal = option.tabTextHorizontalPadding.dp
                            )
                            .disableRippleClickable {
                                selectedIndex = i
                                option.tabClick?.invoke(i, item)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        HongTextCompose(
                            option = if (isSelected) {
                                HongTextBuilder()
                                    .copy(option.selectTabTextOption)
                                    .text(option.tabTitleList[i])
                                    .applyOption()
                            } else {
                                HongTextBuilder()
                                    .copy(option.unselectTabTextOption)
                                    .text(option.tabTitleList[i])
                                    .applyOption()
                            }
                        )
                    }
                }
            }
        }
    }
}