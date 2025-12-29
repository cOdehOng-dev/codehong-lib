package com.codehong.lib.sample.layout.fade


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.codehong.library.widget.R
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.label.HongTextBuilder
import com.codehong.library.widget.text.label.HongTextCompose

@Composable
fun FadeAnimHeaderLayoutHeader(
    isTransparent: Boolean,
    headerAlpha: Float
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                colorResource(id = R.color.honglib_color_ffffff)
                    .copy(alpha = headerAlpha)
            )
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
                .background(colorResource(id = if (isTransparent) R.color.honglib_color_ffffff else R.color.honglib_color_000000))
        )

        HongTextCompose(
            option = HongTextBuilder()
                .width(HongLayoutParam.MATCH_PARENT.value)
                .height(HongLayoutParam.WRAP_CONTENT.value)
                .textAlign(HongTextAlign.CENTER)
                .maxLines(1)
                .overflow(HongTextOverflow.ELLIPSIS)
                .typography(HongTypo.TITLE_26_B)
                .color(HongColor.BLACK_100)
                .text("헤더 타이틀")
                .applyOption()
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
                .background(colorResource(id = if (isTransparent) R.color.honglib_color_ffffff else R.color.honglib_color_000000))
        )
    }
}
