package com.codehong.library.widget

import androidx.annotation.ColorRes
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun InpkDivider(
    @ColorRes colorResId: Int,
    height: Int
) {
    HorizontalDivider(
        color = colorResource(id = colorResId),
        thickness = height.dp
    )
}
