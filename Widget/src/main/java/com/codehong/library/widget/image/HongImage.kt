package com.codehong.library.widget.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codehong.library.widget.getRadius
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.util.getColor
import com.codehong.library.widget.widthHeight

@Composable
fun HongImage(
    modifier: Modifier = Modifier,
    width: Int? = null,
    height: Int? = null,
    @DrawableRes drawableResId: Int,
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0,
    borderColor: HongComposeColor? = null,
    borderWidth: Int = 0,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    useShapeCircle: Boolean = false
) {
    Image(
        modifier = Modifier
            .then(modifier)
            .widthHeight(width, height)
            .border(
                border = BorderStroke(
                    borderWidth.dp,
                    borderColor.getColor()
                ),
                shape = if (useShapeCircle) {
                    CircleShape
                } else {
                    RoundedCornerShape(
                        topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
                        topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
                        bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
                        bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
                    )
                }
            )
            .clip(
                shape = if (useShapeCircle) {
                    CircleShape
                } else {
                    RoundedCornerShape(
                        topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
                        topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
                        bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
                        bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
                    )
                }
            ),
        painter = painterResource(drawableResId),
        contentDescription = contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}

@Composable
fun HongImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    width: Int? = null,
    height: Int? = null,
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0,
    borderColor: HongComposeColor? = null,
    borderWidth: Int = 0,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    useShapeCircle: Boolean = false
) {
    AsyncImage(
        modifier = Modifier
            .then(modifier)
            .widthHeight(width, height)
            .border(
                border = BorderStroke(
                    borderWidth.dp,
                    borderColor.getColor()
                ),
                shape = if (useShapeCircle) {
                    CircleShape
                } else {
                    RoundedCornerShape(
                        topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
                        topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
                        bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
                        bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
                    )
                }
            )
            .clip(
                shape = if (useShapeCircle) {
                    CircleShape
                } else {
                    RoundedCornerShape(
                        topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
                        topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
                        bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
                        bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
                    )
                }
            ),
        alpha = alpha,
        model = imageUrl,
        contentDescription = contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}
