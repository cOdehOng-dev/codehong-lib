package com.codehong.library.widget.image.def

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongScaleType.Companion.toContentScale
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongImageCompose(option: HongImageOption) {
    HongWidgetContainer(option) {
        val context = LocalContext.current
        val isPreview = LocalInspectionMode.current

        val imageModifier = Modifier
            .hongWidth(option.width)
            .hongHeight(option.height)
            .hongBackground(
                border = option.border,
                radius = option.radius,
                shadow = option.shadow,
                color = option.backgroundColorHex,
                useShapeCircle = option.useShapeCircle,
            )
            .hongSpacing(option.padding)

        val colorFilter = option.imageColor?.let { ColorFilter.tint(it.toColor()) }

        if (isPreview) {
            val previewRes = (option.imageInfo as? Int)
                ?: option.placeholder
                ?: option.error
            if (previewRes != null) {
                Image(
                    modifier = imageModifier,
                    painter = painterResource(id = previewRes),
                    contentDescription = null,
                    contentScale = option.scaleType.toContentScale(),
                    colorFilter = colorFilter
                )
            }
        } else {
            val imageRequest = ImageRequest.Builder(context)
                .data(option.imageInfo)
                .placeholder(option.placeholder?.let { ContextCompat.getDrawable(context, it) })
                .error(option.error?.let { ContextCompat.getDrawable(context, it) })
                .fallback(R.color.honglib_color_transparent)
                .memoryCachePolicy(option.memoryCache)
                .diskCachePolicy(option.diskCache)
                .crossfade(option.crossFade)
                .apply { option.size?.let { size(it) } }
                .build()

            SubcomposeAsyncImage(
                modifier = imageModifier,
                model = imageRequest,
                contentDescription = null,
                contentScale = option.scaleType.toContentScale(),
                colorFilter = colorFilter,
                loading = {
                    option.onLoading?.invoke()
                    option.placeholder?.let {
                        SubcomposeAsyncImageContent()
                    }
                },
                success = {
                    option.onSuccess?.invoke()
                    SubcomposeAsyncImageContent()
                },
                error = {
                    option.onError?.invoke()
                    option.error?.let {
                        SubcomposeAsyncImageContent()
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHongImageCompose() {
    val option = HongImageBuilder()
        .width(100)
        .height(100)
        .imageInfo("https://picsum.photos/200")
        .placeholder(R.drawable.honglib_ic_search)
        .scaleType(HongScaleType.CENTER_CROP)
        .radius(HongRadiusInfo(all = 12))
        .backgroundColor(HongColor.GRAY_100.hex)
        .applyOption()
    HongImageCompose(option)
}

@Preview(showBackground = true, name = "Circle Image")
@Composable
private fun PreviewHongImageComposeCircle() {
    val option = HongImageBuilder()
        .width(80)
        .height(80)
        .imageInfo("https://picsum.photos/200")
        .placeholder(R.drawable.honglib_ic_search)
        .scaleType(HongScaleType.CENTER_CROP)
        .useShapeCircle(true)
        .backgroundColor(HongColor.GRAY_100.hex)
        .applyOption()
    HongImageCompose(option)
}

@Preview(showBackground = true, name = "With Border")
@Composable
private fun PreviewHongImageComposeWithBorder() {
    val option = HongImageBuilder()
        .width(100)
        .height(100)
        .imageInfo("https://picsum.photos/200")
        .placeholder(R.drawable.honglib_ic_search)
        .scaleType(HongScaleType.CENTER_CROP)
        .radius(HongRadiusInfo(all = 8))
        .border(HongBorderInfo(color = HongColor.PURPLE_100.hex, width = 2))
        .backgroundColor(HongColor.WHITE_100.hex)
        .applyOption()
    HongImageCompose(option)
}