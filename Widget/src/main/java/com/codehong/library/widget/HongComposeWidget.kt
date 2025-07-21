package com.codehong.library.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor

@Composable
fun HongDivider(
    colorHex: String,
    height: Int
) {
    HorizontalDivider(
        color = colorHex.toColor(),
        thickness = height.dp
    )
}
@Composable
fun HongDivider(
    color: HongColor,
    height: Int
) {
    HorizontalDivider(
        color = color.toColor(),
        thickness = height.dp
    )
}
@Composable
@UiComposable
fun MarginTopOrBottom(value: Int) {
    Spacer(modifier = Modifier.height(value.dp))
}

@Composable
@UiComposable
fun MarginLeftOrRight(value: Int) {
    Spacer(modifier = Modifier.width(value.dp))
}
