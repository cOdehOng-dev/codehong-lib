package com.codehong.library.widget.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongScaleType.Companion.toContentScale
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toRoundedCornersTransformation
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongImageCompose(
    option: HongImageOption,
) {
    val hasUrl = !option.imageUrl.isNullOrEmpty()
    if (option.drawableResId == null && !hasUrl) {
        return
    }

    HongWidgetContainer(option) {
        val context = LocalContext.current
        val imageInfo = if (hasUrl) {
            option.imageUrl
        } else {
            option.drawableResId
        }
        AsyncImage(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
                .hongBackground(
                    border = option.border,
                    radius = option.radius,
                    shadow = option.shadow,
                    color = option.backgroundColorHex,
                    useShapeCircle = option.useShapeCircle,
                )
                .hongSpacing(option.padding),
            model = ImageRequest.Builder(context)
                .data(imageInfo)
                .placeholder(
                    option.placeholder?.let {
                        ContextCompat.getDrawable(
                            context,
                            it
                        )
                    }
                )
                .error(
                    option.error?.let {
                        ContextCompat.getDrawable(
                            context,
                            it
                        )
                    }
                )
                .transformations(option.radius.toRoundedCornersTransformation(context))
                .fallback(R.color.honglib_color_transparent)
                .memoryCachePolicy(option.memoryCache)
                .diskCachePolicy(option.diskCache)
                .build(),
            contentDescription = null,
            contentScale = option.scaleType.toContentScale(),
            colorFilter = option.imageColor?.let { ColorFilter.tint(it.toColor()) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongImageCompose() {
    val option = HongImageBuilder()
        .width(20)
        .height(20)
        .margin(
            HongSpacingInfo(
                left = 20f
            )
        )
        .drawableResId(R.drawable.honglib_ic_20_close)
        .scaleType(HongScaleType.CENTER_CROP)
        .applyOption()
    HongImageCompose(option)
}