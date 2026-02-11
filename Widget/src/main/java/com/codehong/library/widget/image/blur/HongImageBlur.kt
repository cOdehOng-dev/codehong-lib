package com.codehong.library.widget.image.blur

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.codehong.library.widget.rule.HongScaleType.Companion.toContentScale
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongImageBlur(option: HongImageBlurOption) {
    if (option.imageInfo == null) return

    HongWidgetContainer(option) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = option.blur.dp)
                .paint(
                    painter = rememberAsyncImagePainter(model = option.imageInfo),
                    contentScale = option.scaleType.toContentScale()
                )
        )
    }
}