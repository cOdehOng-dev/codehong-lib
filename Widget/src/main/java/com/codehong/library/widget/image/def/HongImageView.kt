package com.codehong.library.widget.image.def

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import coil.imageLoader
import coil.request.ImageRequest
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.parseColor
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.rule.HongScaleType.Companion.toScaleType
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toRoundedCornersTransformation

class HongImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var option = HongImageOption()
        private set

    private var imageView: ImageView? = null

    fun set(option: HongImageOption): HongImageView {
        this.option = option
        removeAllViews()
        applyLayout()
        applyBackground()
        applyImage()
        applyClickListener()
        return this
    }

    private fun applyLayout() {
        setLayout(option.width, option.height)?.apply {
            leftMargin = context.dpToPx(option.margin.left)
            topMargin = context.dpToPx(option.margin.top)
            rightMargin = context.dpToPx(option.margin.right)
            bottomMargin = context.dpToPx(option.margin.bottom)
        }
        hongPadding(option.padding)
    }

    private fun applyBackground() {
        hongBackground(
            backgroundColor = option.backgroundColorHex,
            border = option.border,
            radius = option.radius,
            useShapeCircle = option.useShapeCircle
        )
    }

    private fun applyImage() {
        imageView = ImageView(context).apply {
            scaleType = option.scaleType.toScaleType()
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        }

        val request = buildImageRequest()
        context.imageLoader.enqueue(request)

        addView(imageView)
    }

    private fun buildImageRequest(): ImageRequest {
        val transformation = option.radius.toRoundedCornersTransformation(context)

        return ImageRequest.Builder(context)
            .data(option.imageInfo)
            .target(imageView!!)
            .crossfade(true)
            .transformations(transformation)
            .apply {
                option.placeholder?.let { placeholder(it) }
                option.error?.let { error(it) }
            }
            .diskCachePolicy(option.diskCache)
            .memoryCachePolicy(option.memoryCache)
            .listener(
                onStart = {
                    option.onLoading?.invoke()
                },
                onSuccess = { _, _ ->
                    option.onSuccess?.invoke()
                    applyImageColorFilter()
                },
                onError = { _, _ ->
                    option.onError?.invoke()
                }
            )
            .build()
    }

    private fun applyImageColorFilter() {
        option.imageColor
            ?.takeIf { it.isNotEmpty() }
            ?.parseColor()
            ?.let { color ->
                imageView?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
            }
    }

    private fun applyClickListener() {
        option.click?.let { clickListener ->
            setOnClickListener { clickListener.invoke(option) }
        } ?: setOnClickListener(null)
    }
}