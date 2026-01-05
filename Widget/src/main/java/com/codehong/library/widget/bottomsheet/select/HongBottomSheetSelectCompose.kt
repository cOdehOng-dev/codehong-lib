package com.codehong.library.widget.bottomsheet.select

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.button.icon.HongButtonIconBuilder
import com.codehong.library.widget.button.icon.HongButtonIconCompose
import com.codehong.library.widget.extensions.disableRippleClickable
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toRoundedCornerShape
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HongBottomSheetSelectCompose(
    showBottomSheet: Boolean,
    option: HongBottomSheetSelectOption
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    val remOption by remember { mutableStateOf(option) }
    var selectedOption by remember { mutableStateOf(remOption.initialSelection) }

    @Composable
    fun Content() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .hongBackground(
                    color = HongColor.WHITE_100,
                )
                .padding(bottom = 20.dp)
        ) {
            // 헤더 영역
            Row(
                modifier = Modifier
                    .padding(top = 29.dp, start = 20.dp, bottom = 30.dp, end = 13.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    HongTextCompose(
                        HongTextBuilder()
                            .text(remOption.title)
                            .typography(remOption.titleTypo)
                            .color(remOption.titleColorHex)
                            .applyOption()
                    )
                }

                Box(
                    modifier = Modifier
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    HongButtonIconCompose(
                        HongButtonIconBuilder()
                            .iconResId(R.drawable.honglib_ic_close)
                            .buttonType(HongButtonIconType.SIZE_40)
                            .border(HongBorderInfo())
                            .onClick {
                                scope.launch {
                                    bottomSheetState.hide()
                                    remOption.onChangeVisibleState(false)
                                }
                            }
                            .applyOption()
                    )
                }
            }

            remOption.selectionList.forEachIndexed { _, item ->
                SelectionItem(
                    option = option,
                    selection = item,
                    isSelected = item.first == selectedOption.first,
                    onSelect = {
                        remOption.selectSelectionCallback(item)
                        selectedOption = item
                        scope.launch {
                            bottomSheetState.hide()
                            remOption.onChangeVisibleState(false)
                        }
                    }
                )
            }
        }
    }

    if (!showBottomSheet) {
        return
    }

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = {
            scope.launch {
                remOption.onChangeVisibleState(false)
            }
        },
        sheetState = bottomSheetState,
        scrimColor = remOption.dimColorHex.toColor(),
        dragHandle = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .hongBackground(
                        color = HongColor.WHITE_100.hex
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(40.dp)
                        .height(6.dp)
                        .hongBackground(
                            color = remOption.dragHandleColorHex,
                            radius = HongRadiusInfo(
                                topLeft = 3,
                                topRight = 3,
                                bottomLeft = 3,
                                bottomRight = 3
                            )
                        )
                )
            }
        },
        shape = HongRadiusInfo(
            topLeft = remOption.topRadius,
            topRight = remOption.topRadius,
            bottomLeft = 0,
            bottomRight = 0
        ).toRoundedCornerShape()
    ) {
        Content()
    }
}


@Composable
private fun SelectionItem(
    option: HongBottomSheetSelectOption,
    selection: Pair<String, String>,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(bottom = 15.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .hongBackground(
                color = option.selectionBackgroundColorHex,
                radius = option.selectionRadius,
                border = HongBorderInfo(
                    width = if (isSelected) option.selectionSelectorBorder.width else 0,
                    color = option.selectionSelectorBorder.color
                )
            )
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .disableRippleClickable { onSelect() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            HongTextCompose(
                HongTextBuilder()
                    .text(selection.first)
                    .typography(option.selectionTitleTypo)
                    .color(option.selectionTitleColorHex)
                    .maxLines(1)
                    .applyOption()
            )


            if (selection.second.isNotEmpty()) {
                HongTextCompose(
                    HongTextBuilder()
                        .padding(
                            HongSpacingInfo(
                                top = 4f
                            )
                        )
                        .text(selection.second)
                        .typography(option.selectionSubtitleTypo)
                        .color(option.selectionSubtitleColorHex)
                        .maxLines(1)
                        .applyOption()
                )
            }
        }
    }
}