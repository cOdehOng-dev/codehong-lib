package com.codehong.library.widget.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.R
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.typo.TypoType
import com.codehong.library.widget.typo.isBold

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
fun dpToPx(dp: Int) = with(LocalDensity.current) { dp.dp.toPx() }

@Composable
fun pxToDp(px: Float): Dp {
    return with(LocalDensity.current) { px.toDp() }
}

@Composable
fun ColorType?.getComposeColor(defColor: Int): Color {
    if (this == null) {
        return colorResource(id = defColor)
    }

    return colorResource(id = this.colorResId)
}

@Composable
fun HongComposeColor?.getColor(): Color {
    if (this == null) {
        return colorResource(id = R.color.honglib_color_transparent)
    }

    return if (colorType != null) {
        colorResource(id = colorType.colorResId)
    } else {
        colorResource(id = colorRes)
    }
}

