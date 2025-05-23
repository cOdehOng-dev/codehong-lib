package com.codehong.library.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.unit.dp

@Composable
@UiComposable
fun MarginTopOrBottom(value: Int) {
    Spacer(modifier = Modifier.height(value.dp))
}

@Composable
@UiComposable
fun MarginStartOrEnd(value: Int) {
    Spacer(modifier = Modifier.width(value.dp))
}

fun getRadius(
    allRadius: Int,
    hvRadius: Int,
    cornerRadius: Int
): Int {
    return if (allRadius != 0) {
        allRadius
    } else {
        hvRadius.takeIf { it > 0 } ?: run { cornerRadius }
    }
}

enum class WidthHeightRange(val id: Int) {
    FILL(-1),
    WRAP(-2)
}
