package com.codehong.lib.sample.layout.fadeanimheader

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.codehong.lib.R
import com.codehong.library.widget.text.HongText

@Composable
fun FadeAnimHeaderLayoutHeader(
    isTransparent: Boolean,
    headerAlpha: Float
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(colorResource(id = R.color.color_ffffff).copy(alpha = headerAlpha))
    ) {
        val startGuideline = createGuidelineFromStart(4.dp)
        val endGuideline = createGuidelineFromEnd(4.dp)
        val topGuideline = createGuidelineFromTop(5.dp)
        val bottomGuideline = createGuidelineFromBottom(5.dp)

        val (ivBack, ivShare, tvTitle) = createRefs()

        Spacer(
            modifier = Modifier
                .constrainAs(ivBack) {
                    top.linkTo(topGuideline)
                    bottom.linkTo(bottomGuideline)
                    start.linkTo(startGuideline)
                }
                .width(40.dp)
                .height(40.dp)
                .background(colorResource(id = if (isTransparent) R.color.white else R.color.black))
        )

        HongText(
            modifier = Modifier
                .constrainAs(tvTitle) {
                    start.linkTo(ivBack.end, 4.dp)
                    end.linkTo(ivShare.start, 4.dp)
                    top.linkTo(topGuideline)
                    bottom.linkTo(bottomGuideline)
                    width = Dimension.fillToConstraints
                }
                .alpha(if (isTransparent) 0f else 1f),
            text = "헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더",
            textSize = 16,
            fontWeight = FontWeight.W700,
            textColor = com.codehong.library.widget.R.color.honglib_color_29292d,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            isEmptyOrNullHideView = false
        )

        Spacer(
            modifier = Modifier
                .constrainAs(ivShare) {
                    top.linkTo(topGuideline)
                    bottom.linkTo(bottomGuideline)
                    end.linkTo(endGuideline)
                }
                .width(40.dp)
                .height(40.dp)
                .background(colorResource(id = if (isTransparent) R.color.white else R.color.black))
        )
    }
}
