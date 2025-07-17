package com.codehong.library.widget.image

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import coil.imageLoader
import coil.request.ImageRequest
import com.codehong.library.widget.rule.HongScaleType.Companion.toScaleType
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toRoundedCornersTransformation
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout

class HongImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var option = HongImageOption()
        private set

    fun set(
        option: HongImageOption
    ): HongImageView {
        this.option = option

        this.removeAllViews()

        setLayout(
            option.width,
            option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }

        hongPadding(option.padding)
        hongBackground(
            backgroundColor = option.backgroundColorHex,
            border = option.border,
            radius = option.radius,
            useShapeCircle = option.useShapeCircle,
        )

        val imageView = ImageView(context).apply {
            this.scaleType = option.scaleType.toScaleType()

            val transformation = option.radius.toRoundedCornersTransformation(context)

            val request = ImageRequest.Builder(context)
                .data(option.imageUrl ?: option.drawableResId)
                .target(this)
                .crossfade(true)
                .transformations(transformation)
                .apply {
                    option.placeholder?.let { placeholder(it) }
                    option.error?.let { error(it) }
                }
                .diskCachePolicy(option.diskCache)
                .memoryCachePolicy(option.memoryCache)
                .build()

            context.imageLoader.enqueue(request)
        }

        addView(imageView)

        // 클릭 리스너 설정
        this.setOnClickListener {
            option.click?.invoke(option)
        }

        return this
    }
}