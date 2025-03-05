package com.codehong.library.widget.text

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.codehong.library.widget.R
import com.codehong.library.widget.hongBorder
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeTextStyle

@Composable
fun HongBadgeText(
    modifier: Modifier = Modifier,
    text: String?,
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0,
    textStyle: HongComposeTextStyle = HongComposeTextStyle(),
    backgroundColor: HongComposeColor = HongComposeColor(),
    borderWidth: Int? = null,
    borderColor: HongComposeColor? = null,
    paddingTop: Float = 4f,
    paddingBottom: Float = 4f,
    paddingStart: Float = 8f,
    paddingEnd: Float = 8f
) {
    if (text.isNullOrEmpty()) return

    ConstraintLayout(
        modifier = Modifier
            .then(modifier)
            .hongBorder(
                borderColor = borderColor ?: HongComposeColor(),
                borderWidth = borderWidth ?: 0,
                backgroundColor = backgroundColor,
                allRadius = allRadius,
                topRadius = topRadius,
                bottomRadius = bottomRadius,
                topStartRadius = topStartRadius,
                topEndRadius = topEndRadius,
                bottomStartRadius = bottomStartRadius,
                bottomEndRadius = bottomEndRadius
            )
    ) {
        val (tvBadge) = createRefs()

        HongText(
            modifier = Modifier
                .constrainAs(tvBadge) {
                    top.linkTo(parent.top, paddingTop.dp)
                    bottom.linkTo(parent.bottom, paddingBottom.dp)
                    start.linkTo(parent.start, paddingStart.dp)
                    end.linkTo(parent.end, paddingEnd.dp)
                }
                .background(colorResource(id = R.color.honglib_color_transparent)),
            text = text,
            style = textStyle
        )
    }
}
