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
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.disableRippleClickable
import com.codehong.library.widget.hongBorder
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.text.HongText
import com.codehong.library.widget.rule.typo.HongTypo
import kotlinx.coroutines.delay

@Composable
fun HongScrollTab(
    modifier: Modifier = Modifier,
    tabList: List<Any>,
    tabTitleList: List<String>,
    borderWidth: Int = 1,
    selectBorderColor: HongComposeColor = HongComposeColor(
        type = HongColor.WHITE_100
    ),
    unselectBorderColor: HongComposeColor = HongComposeColor(
        type = HongColor.GRAY_10
    ),
    selectBackgroundColor: HongComposeColor = HongComposeColor(
        type = HongColor.MAIN_PURPLE
    ),
    unselectBackgroundColor: HongComposeColor = HongComposeColor(
        type = HongColor.WHITE_100
    ),
    selectTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        typo = HongTypo.BODY_14_B,
        color = HongComposeColor(
            type = HongColor.WHITE_100
        )
    ),
    unselectTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        typo = HongTypo.BODY_14,
        color = HongComposeColor(
            type = HongColor.BLACK_100
        )
    ),
    tabLayoutStartPadding: Int = 0,
    tabLayoutEndPadding: Int = 0,
    tabBetweenPadding: Int = 0,
    tabTextHorizontalPadding: Int = 16,
    tabTextVerticalPadding: Int = 8,
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0,
    initialSelectIndex: Int = 0,
    onTabClick: (index: Int, item: Any) -> Unit
) {
    val scrollState = rememberScrollState()

    var selectedIndex by remember { mutableIntStateOf(initialSelectIndex) }

    LaunchedEffect(selectedIndex) {
        delay(200)
        val centerOffset = (scrollState.maxValue / tabList.size) * selectedIndex
        scrollState.animateScrollTo(centerOffset)
    }

    Row(
        modifier = Modifier
            .then(modifier)
            .horizontalScroll(scrollState)
            .padding(
                start = tabLayoutStartPadding.dp,
                end = tabLayoutEndPadding.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabList.forEachIndexed { i, item ->
            val isSelected = selectedIndex == i

            Row(
                modifier = Modifier
                    .padding(
                        start = if (i == 0) {
                            0.dp
                        } else {
                            (tabBetweenPadding / 2).dp
                        },
                        end = if (i == tabList.size - 1) {
                            0.dp
                        } else {
                            (tabBetweenPadding / 2).dp
                        }
                    )
            ) {
                Box(
                    modifier = Modifier
                        .hongBorder(
                            borderWidth = if (isSelected) 0 else borderWidth,
                            borderColor = if (isSelected) {
                                selectBorderColor
                            } else {
                                unselectBorderColor
                            },
                            allRadius = allRadius,
                            topRadius = topRadius,
                            bottomRadius = bottomRadius,
                            topStartRadius = topStartRadius,
                            topEndRadius = topEndRadius,
                            bottomStartRadius = bottomStartRadius,
                            bottomEndRadius = bottomEndRadius,
                            backgroundColor = if (isSelected) {
                                selectBackgroundColor
                            } else {
                                unselectBackgroundColor
                            }
                        )
                        .padding(
                            vertical = tabTextVerticalPadding.dp,
                            horizontal = tabTextHorizontalPadding.dp
                        )
                        .disableRippleClickable {
                            selectedIndex = i
                            onTabClick(i, item)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    HongText(
                        text = tabTitleList[i],
                        style = if (isSelected) selectTextStyle else unselectTextStyle
                    )
                }
            }
        }
    }
}