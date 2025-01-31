package com.codehong.library.widget.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun dpToSp(dp: Int) = with(LocalDensity.current) { dp.dp.toSp() }

@Composable
fun dpToSp(dp: Float) = with(LocalDensity.current) { dp.dp.toSp() }

@Composable
fun dpToPx(dp: Dp) = with(LocalDensity.current) { dp.toPx() }

@Composable
fun dpToPx(dp: Float) = with(LocalDensity.current) { dp.dp.toPx() }

@Composable
fun pxToDp(px: Float): Dp {
    return with(LocalDensity.current) { px.toDp() }
}
