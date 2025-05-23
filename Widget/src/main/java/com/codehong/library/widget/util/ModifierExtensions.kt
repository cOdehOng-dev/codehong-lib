package com.codehong.library.widget.util

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.rule.HongLayoutParam

@Composable
fun Modifier.adjustWidth(
    width: Int
): Modifier {
    return if (width > 0) {
        this.width(width.dp)
    } else {
        if (width == HongLayoutParam.MATCH_PARENT.value) {
            fillMaxWidth()
        } else {
            wrapContentWidth()
        }
    }
}

@Composable
fun Modifier.adjustHeight(
    height: Int
): Modifier {
    return if (height > 0) {
        this.height(height.dp)
    } else {
        if (height == HongLayoutParam.MATCH_PARENT.value) {
            fillMaxHeight()
        } else {
            wrapContentHeight()
        }
    }
}
