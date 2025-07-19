package com.codehong.lib.sample.image

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.R
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.image.HongImageView
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class SampleImageActivity : BaseSampleMixActivity() {

    private val option1 = HongImageBuilder()
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

    private val option2 = HongImageBuilder()
        .width(100)
        .height(100)
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .drawableResId(R.drawable.honglib_ic_20_close)
        .scaleType(HongScaleType.CENTER_CROP)
        .applyOption()

    private val option22 = HongImageBuilder()
        .width(13)
        .height(13)
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .drawableResId(R.drawable.honglib_ic_24_check)
        .scaleType(HongScaleType.CENTER_CROP)
        .imageColor(HongColor.MAIN_ORANGE_100)
        .applyOption()

    private val option3 = HongImageBuilder()
        .width(100)
        .height(100)
        .margin(
            HongSpacingInfo(
                left = 20f
            )
        )
        .drawableResId(R.drawable.honglib_ic_20_close)
        .scaleType(HongScaleType.CENTER_CROP)
        .applyOption()

    private val option4 = HongImageBuilder()
        .width(200)
        .height(200)
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .imageUrl("https://images.unsplash.com/photo-1735832489994-96adfc4db2dd?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
        .radius(
            HongRadiusInfo(
                topLeft = 12,
                topRight = 12,
                bottomLeft = 12,
                bottomRight = 12
            )
        )
        .shadow(
            HongShadowInfo(
                color = HongColor.MAIN_ORANGE_100.hex,
                blur = 24f,
                offsetY = 0f,
                offsetX = 2f,
                spread = 0f,
            )
        )
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .scaleType(HongScaleType.CENTER_CROP)
        .applyOption()

    private val option5 = HongImageBuilder()
        .width(200)
        .height(200)
        .margin(
            HongSpacingInfo(
                left = 20f
            )
        )
        .imageUrl(
            "https://images.unsplash.com/photo-173583248" +
                    "b=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        )
        .radius(
            HongRadiusInfo(
                topLeft = 12,
                topRight = 12,
                bottomLeft = 12,
                bottomRight = 12
            )
        )
        .scaleType(HongScaleType.CENTER_CROP)
        .error(R.drawable.honglib_bg_image_error)
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2,
        option22,
        option3,
        option4,
        option5
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { option ->
                add(HongImageView(this@SampleImageActivity).set(option))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach { option ->
            HongImageCompose(option)
        }
    }
}
