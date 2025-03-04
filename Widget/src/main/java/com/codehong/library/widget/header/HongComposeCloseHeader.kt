package com.codehong.library.widget.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.R
import com.codehong.library.widget.disableRippleClickable
import com.codehong.library.widget.image.HongImage

@Composable
fun HongComposeCloseHeader(
    modifier: Modifier = Modifier,
    closeClick: () -> Unit,
    contentGravity: Alignment = Alignment.CenterStart,
    content: @Composable () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .background(colorResource(id = ColorType.WHITE_100.colorResId))
            .height(52.dp)
    ) {
        val (cContent, ivClose) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(cContent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, 49.dp)
                    end.linkTo(ivClose.start, 5.dp)
                    width = Dimension.fillToConstraints
                },
            contentAlignment = contentGravity
        ) {
            content()
        }

        Box(
            modifier = Modifier
                .constrainAs(ivClose) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, 4.dp)
                }
                .size(40.dp)
                .background(colorResource(id = R.color.honglib_color_ffffff))
                .disableRippleClickable { closeClick.invoke() },
            contentAlignment = Alignment.Center
        ) {
            HongImage(drawableResId = R.drawable.ic_24_close)
        }
    }
}
