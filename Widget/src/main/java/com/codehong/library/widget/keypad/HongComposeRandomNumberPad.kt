package com.codehong.library.widget.keypad

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codehong.library.widget.layout.HongSlideVisibleLayout

@Composable
fun HongRandomNumberPad(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onAnimationEnd: (() -> Unit)? = null,
) {
    HongSlideVisibleLayout(
        isVisible = isVisible,
        onAnimationEnd = onAnimationEnd,
    ) {

   }
}
