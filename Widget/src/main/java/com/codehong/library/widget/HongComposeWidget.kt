package com.codehong.library.widget

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.util.getColor

@Composable
fun HongDivider(
    color: HongComposeColor,
    height: Int
) {
    HorizontalDivider(
        color = color.getColor(),
        thickness = height.dp
    )
}
