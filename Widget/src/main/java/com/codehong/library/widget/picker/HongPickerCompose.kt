package com.codehong.library.widget.picker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.button.text2.HongTextButtonBuilder2
import com.codehong.library.widget.button.text2.HongTextButtonCompose2
import com.codehong.library.widget.extensions.disableRippleClickable
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun HongPickerCompose(
    visible: Boolean,
    option: HongPickerOption,
) {

    val isTwoOptionList = !option.secondOptionList.isNullOrEmpty()

    var selectedFirstOption by remember { mutableStateOf(Pair(option.initialFirstOption, option.firstOptionList[option.initialFirstOption])) }
    var selectedSecondOption by remember { mutableStateOf(Pair(option.initialSecondOption, option.secondOptionList?.get(option.initialSecondOption))) }

    var showContent by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if (visible) {
            delay(100)
            showContent = true
        } else {
            showContent = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (visible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hongBackground(
                        backgroundColor = HongColor.GRAY_30.hex
                    )
                    .disableRippleClickable {
                        if (option.useDimClickCLose) {
                            option.onDismiss()
                        }
                    }
            )
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = 300)
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = 300)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .hongBackground(
                        backgroundColor = option.backgroundColorHex,
                        radius = HongRadiusInfo(
                            topLeft = option.radius.topLeft,
                            topRight = option.radius.topRight
                        )
                    )
                    .hongSpacing(
                        HongSpacingInfo(
                            top = 30f
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 상단 타이틀 + 닫기 버튼
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .hongSpacing(
                            HongSpacingInfo(
                                left = 20f,
                                right = 20f
                            )
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        HongTextCompose(
                            option = HongTextBuilder()
                                .text(option.title)
                                .typography(HongTypo.BODY_20_B)
                                .color(option.titleColorHex)
                                .applyOption()
                        )
                    }

                    if (option.onDirectSelect == null) {
                        HongImageCompose(
                            option = HongImageBuilder()
                                .width(24)
                                .height(24)
                                .drawableResId(R.drawable.honglib_ic_close)
                                .onClick { option.onDismiss() }
                                .applyOption()
                        )
                    }
                }

                // Picker 영역
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .hongSpacing(
                            HongSpacingInfo(
                                top = 30f,
                                bottom = 30f,
                                left = 20f,
                                right = 20f
                            )
                        )
                        .height(157.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Picker(
                        modifier = Modifier.weight(1f),
                        optionList = option.firstOptionList,
                    ) { index, item ->
                        selectedFirstOption = Pair(index, item)
                        option.onDirectSelect?.invoke(selectedFirstOption, selectedSecondOption)
                    }

                    if (isTwoOptionList) {
                        Picker(
                            modifier = Modifier
                                .hongSpacing(
                                    HongSpacingInfo(
                                        left = 15f
                                    )
                                )
                                .weight(1f),
                            optionList = option.secondOptionList!!,
                        ) { index, item ->
                            selectedSecondOption = Pair(index, item)
                            option.onDirectSelect?.invoke(selectedFirstOption, selectedSecondOption)
                        }
                    }
                }

                HongTextButtonCompose2(
                    HongTextButtonBuilder2()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .height(48)
                        .margin(
                            HongSpacingInfo(
                                top = 10f,
                                left = 20f,
                                right = 20f,
                                bottom = 10f
                            )
                        )
                        .text(option.buttonText)
                        .textTypo(HongTypo.BODY_15_B)
                        .textColor(HongColor.WHITE_100)
                        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
                        .radius(
                            HongRadiusInfo(
                                all = 12
                            )
                        )
                        .onClick {
                            if (option.onDirectSelect == null) {
                                option.onConfirm?.invoke(
                                    selectedFirstOption,
                                    selectedSecondOption
                                )
                            }
                            option.onDismiss()
                        }
                        .applyOption()
                )
            }
        }
    }
}

@Composable
private fun Picker(
    modifier: Modifier = Modifier,
    optionList: List<String>,
    onSelected: (Int, String) -> Unit
) {
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    BoxWithConstraints(
        modifier = modifier
            .fillMaxHeight()
    ) {
        val viewportHeight = maxHeight
        val topBottomPadding = (viewportHeight - 36.dp) / 2

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(36.dp)
                .hongBackground(
                    backgroundColor = HongColor.GRAY_10.hex,
                    radius = HongRadiusInfo(
                        topLeft = 8,
                        topRight = 8,
                        bottomLeft = 8,
                        bottomRight = 8
                    )
                )
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(vertical = topBottomPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(optionList) { index, item ->
                val layoutInfo by remember { derivedStateOf { listState.layoutInfo } }
                val viewportCenter =
                    (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
                val isSelected = layoutInfo.visibleItemsInfo
                    .find { it.index == index }
                    ?.let { itemInfo ->
                        abs((itemInfo.offset + itemInfo.size / 2) - viewportCenter) < itemInfo.size / 2
                    } ?: false

                if (isSelected) {
                    onSelected(index, item)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .hongHeight(36)
                        .wrapContentHeight(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    HongTextCompose(
                        option = HongTextBuilder()
                            .width(HongLayoutParam.MATCH_PARENT.value)
                            .padding(
                                HongSpacingInfo(
                                    left = 8f,
                                    right = 8f
                                )
                            )
                            .text(item)
                            .typography(if (isSelected) HongTypo.BODY_20_B else HongTypo.BODY_18)
                            .color(if (isSelected) HongColor.BLACK_100 else HongColor.GRAY_50)
                            .textAlign(HongTextAlign.CENTER)
                            .onClick { onSelected(index, item) }
                            .applyOption()
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .hongHeight(8)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            HongColor.WHITE_100.toColor(),
                            HongColor.TRANSPARENT.toColor()
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .hongHeight(8)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            HongColor.TRANSPARENT.toColor(),
                            HongColor.WHITE_100.toColor()
                        )
                    )
                )
        )
    }
}