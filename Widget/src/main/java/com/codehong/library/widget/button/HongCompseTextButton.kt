package com.codehong.library.widget.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.disableRippleClickable
import com.codehong.library.widget.getRadius
import com.codehong.library.widget.hongBorder
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.HongText
import com.codehong.library.widget.util.getColor

@Composable
fun HongTextButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    buttonBackgroundColor: HongComposeColor = HongComposeColor(
        resId = R.color.honglib_color_ffffff
    ),
    buttonTextColor: HongComposeColor = HongComposeColor(
        resId = R.color.honglib_color_ffffff
    ),
    borderWidth: Int = 0,
    borderColor: HongComposeColor = HongComposeColor(),
    useShapeCircle: Boolean = false,
    buttonTextTypoType: HongTypo,
    horizontalPadding: Int = 0,
    verticalPadding: Int = 0,
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0,
    click: () -> Unit
) {
    HongTextButton(
        modifier = modifier,
        buttonText = buttonText,
        buttonBackgroundColor = buttonBackgroundColor,
        buttonTextStyle = HongComposeTextStyle(
            fontWeight = buttonTextTypoType.fontWeight(),
            size = buttonTextTypoType.size(),
            color = buttonTextColor
        ),
        borderWidth = borderWidth,
        borderColor = borderColor,
        useShapeCircle = useShapeCircle,
        horizontalPadding = horizontalPadding,
        verticalPadding = verticalPadding,
        allRadius = allRadius,
        topRadius = topRadius,
        bottomRadius = bottomRadius,
        topStartRadius = topStartRadius,
        topEndRadius = topEndRadius,
        bottomStartRadius = bottomStartRadius,
        bottomEndRadius = bottomEndRadius,
        click = click
    )
}

@Composable
fun HongTextButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    buttonBackgroundColor: HongComposeColor = HongComposeColor(
        resId = R.color.honglib_color_ffffff
    ),
    buttonTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W700,
        size = 16,
        color = HongComposeColor(
            resId = R.color.honglib_color_ffffff
        )
    ),
    borderWidth: Int = 0,
    borderColor: HongComposeColor = HongComposeColor(),
    useShapeCircle: Boolean = false,
    horizontalPadding: Int = 0,
    verticalPadding: Int = 0,
    allRadius: Int = 0,
    topRadius: Int = 0,
    bottomRadius: Int = 0,
    topStartRadius: Int = 0,
    topEndRadius: Int = 0,
    bottomStartRadius: Int = 0,
    bottomEndRadius: Int = 0,
    click: () -> Unit
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .hongBorder(
                borderWidth = borderWidth,
                borderColor = borderColor,
                backgroundColor = buttonBackgroundColor,
                useShapeCircle = useShapeCircle,
                allRadius = allRadius,
                topRadius = topRadius,
                bottomRadius = bottomRadius,
                topStartRadius = topStartRadius,
                topEndRadius = topEndRadius,
                bottomStartRadius = bottomStartRadius,
                bottomEndRadius = bottomEndRadius
            )
            .background(
                color = buttonBackgroundColor.getColor(),
                shape = RoundedCornerShape(
                    topStart = getRadius(allRadius, topRadius, topStartRadius).dp,
                    topEnd = getRadius(allRadius, topRadius, topEndRadius).dp,
                    bottomStart = getRadius(allRadius, bottomRadius, bottomStartRadius).dp,
                    bottomEnd = getRadius(allRadius, bottomRadius, bottomEndRadius).dp
                )
            )
            .disableRippleClickable { click.invoke() },
        contentAlignment = Alignment.Center
    ) {
        HongText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontalPadding.dp, verticalPadding.dp),
            text = buttonText,
            style = buttonTextStyle,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}
