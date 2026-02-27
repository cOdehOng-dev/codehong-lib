package com.codehong.library.widget.bottomsheet.bank

import android.view.WindowManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import com.codehong.library.widget.extensions.clickPress
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.image.def.HongImageBuilder
import com.codehong.library.widget.image.def.HongImageCompose
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import com.codehong.library.widget.util.KoreanBank
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun HongBottomSheetBank(
    showBottomSheet: Boolean,
    option: HongBottomSheetBankOption
) {
    if (!showBottomSheet) return

    val density = LocalDensity.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    val maxSheetHeight = (screenHeightDp * 2 / 3).dp
    val maxSheetHeightPx = with(density) { maxSheetHeight.toPx() }
    val partialOffsetPx = maxSheetHeightPx / 2f
    val offsetY = remember { Animatable(maxSheetHeightPx) }
    val scope = rememberCoroutineScope()
    var dialogVisible by remember(showBottomSheet) { mutableStateOf(true) }

    fun snapToExpanded() = scope.launch {
        offsetY.animateTo(0f, spring(Spring.DampingRatioLowBouncy, Spring.StiffnessMedium))
    }

    fun snapToPartial() = scope.launch {
        offsetY.animateTo(
            partialOffsetPx,
            spring(Spring.DampingRatioLowBouncy, Spring.StiffnessMedium)
        )
    }

    fun dismiss(afterDismiss: () -> Unit = {}) = scope.launch {
        offsetY.animateTo(maxSheetHeightPx, tween(250))
        dialogVisible = false
        afterDismiss()
        option.onDismissed()
    }

    // 그리드 스크롤과 시트 드래그 조율
    val nestedScrollConnection = remember(maxSheetHeightPx, partialOffsetPx) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // 위로 스크롤 & 시트가 완전히 펼쳐지지 않은 경우 → 시트를 먼저 확장
                return if (available.y < 0f && offsetY.value > 0f) {
                    val newOffset = (offsetY.value + available.y).coerceAtLeast(0f)
                    val consumed = newOffset - offsetY.value
                    scope.launch { offsetY.snapTo(newOffset) }
                    Offset(0f, consumed)
                } else Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                // 콘텐츠 최상단에서 아래로 스크롤 시 → 시트를 아래로 드래그
                return if (available.y > 0f) {
                    val newOffset = (offsetY.value + available.y).coerceAtMost(maxSheetHeightPx)
                    val delta = newOffset - offsetY.value
                    scope.launch { offsetY.snapTo(newOffset) }
                    Offset(0f, delta)
                } else Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                // 아래로 빠른 플링 → partial 또는 닫기
                return if (available.y > 500f && offsetY.value > 0f) {
                    if (offsetY.value > partialOffsetPx * 0.5f) dismiss() else snapToPartial()
                    available
                } else Velocity.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                // 위로 빠른 플링 → 펼치기
                if (available.y < -500f) {
                    snapToExpanded()
                    return available
                }
                // 현재 위치에 따라 가장 가까운 상태로 스냅
                when {
                    offsetY.value < partialOffsetPx * 0.5f -> snapToExpanded()
                    offsetY.value < partialOffsetPx + (maxSheetHeightPx - partialOffsetPx) * 0.5f -> snapToPartial()
                    else -> dismiss()
                }
                return Velocity.Zero
            }
        }
    }

    // 뷰 활성화 시 아래에서 위로 슬라이드업
    LaunchedEffect(Unit) {
        offsetY.animateTo(partialOffsetPx, tween(300))
    }

    if (!dialogVisible) return

    Dialog(
        onDismissRequest = { dismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false
        )
    ) {
        // Dialog 기본 dim 제거 (커스텀 scrim 사용)
        val dialogWindow = (LocalView.current.parent as? DialogWindowProvider)?.window
        SideEffect {
            dialogWindow?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialogWindow?.setWindowAnimations(0)
            if (dialogWindow != null) {
                WindowCompat.setDecorFitsSystemWindows(dialogWindow, false)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(option.dimColorHex.toColor())
        ) {
            // 시트는 nav bar 위에 배치
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxSheetHeight)
                        .align(Alignment.BottomCenter)
                        .offset { IntOffset(0, offsetY.value.roundToInt()) }
                        .clip(
                            RoundedCornerShape(
                                topStart = option.topRadius.dp,
                                topEnd = option.topRadius.dp
                            )
                        )
                        .background(HongColor.WHITE_100.hex.toColor())
                        .nestedScroll(nestedScrollConnection)
                ) {
                    // 드래그 핸들
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .draggable(
                                state = rememberDraggableState { delta ->
                                    scope.launch {
                                        offsetY.snapTo(
                                            (offsetY.value + delta).coerceIn(0f, maxSheetHeightPx)
                                        )
                                    }
                                },
                                orientation = Orientation.Vertical,
                                onDragStopped = { velocity ->
                                    when {
                                        velocity > 800f -> dismiss()
                                        velocity < -800f -> snapToExpanded()
                                        offsetY.value < partialOffsetPx * 0.5f -> snapToExpanded()
                                        offsetY.value > partialOffsetPx + (maxSheetHeightPx - partialOffsetPx) * 0.5f -> dismiss()
                                        else -> snapToPartial()
                                    }
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 12.dp, bottom = 8.dp)
                                .width(40.dp)
                                .height(4.dp)
                                .hongBackground(
                                    color = option.dragHandleColorHex,
                                    radius = HongRadiusInfo(all = 2)
                                )
                        )
                    }
                    BankSelectionContent(
                        modifier = Modifier.weight(1f),
                        option = option,
                        onDismiss = { bank ->
                            dismiss { option.onBankSelected(bank) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BankSelectionContent(
    modifier: Modifier = Modifier,
    option: HongBottomSheetBankOption,
    onDismiss: (KoreanBank) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // 상단 안내 텍스트
        HongTextCompose(
            HongTextBuilder()
                .text("은행을 선택해주세요")
                .typography(option.titleTypo)
                .color(option.titleColorHex)
                .padding(HongSpacingInfo(top = 20f, left = 20f, right = 20f, bottom = 16f))
                .applyOption()
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 24.dp
            )
        ) {
            items(KoreanBank.entries) { bank ->
                BankContent(
                    bank = bank,
                    onSelectBank = {
                        // 슬라이드 다운 애니메이션 실행 → 완료 후 은행 선택 콜백
                        onDismiss(bank)
                    }
                )
            }
        }
    }
}

@Composable
private fun BankContent(
    bank: KoreanBank,
    onSelectBank: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .hongBackground(
                color = HongColor.GRAY_05,
                radius = HongRadiusInfo(all = 16)
            )
            .clickPress {
                onSelectBank()
            }
            .padding(vertical = 14.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 은행 로고
        HongImageCompose(
            HongImageBuilder()
                .imageInfo(bank.logoUrl)
                .radius(HongRadiusInfo(8))
                .width(32)
                .height(32)
                .removeImageBg(true)
                .scaleType(HongScaleType.CENTER_CROP)
                .applyOption()
        )

        // 은행 이름
        HongTextCompose(
            HongTextBuilder()
                .text(bank.bankName)
                .typography(HongTypo.CONTENTS_12)
                .color(HongColor.BLACK_100)
                .padding(HongSpacingInfo(top = 6f))
                .maxLines(1)
                .applyOption()
        )
    }
}
