package com.codehong.lib.sample.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.image.blur.HongImageBlur
import com.codehong.library.widget.image.blur.HongImageBlurBuilder
import com.codehong.library.widget.image.def.HongImageBuilder
import com.codehong.library.widget.image.def.HongImageCompose
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.color.HongColor

class SampleImageBlurActivity : BaseSampleComposeActivity() {

    companion object {
        private const val IMAGE_URL_1 = "https://www.kopis.or.kr/upload/pfmPoster/PF_PF284865_260210_131117.jpg"
        private const val IMAGE_URL_2 = "https://www.kopis.or.kr/upload/pfmPoster/PF_PF284850_260210_113353.gif"
        private const val IMAGE_URL_3 = "https://www.kopis.or.kr/upload/pfmPoster/PF_PF284753_260209_134936.png"
    }

    private val option1 = HongImageBlurBuilder()
        .imageInfo(IMAGE_URL_1)
        .blur(30)
        .applyOption()

    private val option2 = HongImageBlurBuilder()
        .width(150)
        .height(200)
        .imageInfo(IMAGE_URL_2)
        .blur(30)
        .applyOption()

    private val option3 = HongImageBlurBuilder()
        .width(150)
        .height(150)
        .imageInfo(IMAGE_URL_3)
        .blur(80)
        .useShapeCircle(true)
        .applyOption()

    @Composable
    override fun InitCompose() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .hongBackground(HongColor.WHITE_100)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 5f)
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                HongImageBlur(option1)

                HongImageCompose(
                    option = HongImageBuilder()
                        .width(150)
                        .height(200)
                        .scaleType(HongScaleType.CENTER_CROP)
                        .imageInfo(IMAGE_URL_1)
                        .applyOption()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            HongImageBlur(option2)

            Spacer(modifier = Modifier.height(8.dp))

            HongImageBlur(option3)
        }
    }
}