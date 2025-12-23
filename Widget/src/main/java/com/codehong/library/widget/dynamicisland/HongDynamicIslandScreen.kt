package com.codehong.library.widget.dynamicisland

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codehong.library.widget.R
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.util.dpToSp

@Composable
fun HongDynamicIslandScreen(
    isExpanded: Boolean,
    smallText: String,
    largeText: String,
    info: DynamicIslandInfo?,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
    onLinkClick: () -> Unit
) {
    val animatedCornerRadius by animateDpAsState(
        targetValue = if (isExpanded) 35.dp else 30.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "CornerRadius"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clickable(
                enabled = isExpanded,
                onClick = onCollapse,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedContent(
            modifier = Modifier
                .padding(top = 1.dp, start = 10.dp, end = 10.dp)
                .clip(RoundedCornerShape(animatedCornerRadius))
                .background(Color.Black)
                .wrapContentSize(),
            targetState = isExpanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) togetherWith
                        fadeOut(animationSpec = tween(300)) using
                        SizeTransform { initialSize, targetSize ->
                            spring(
                                dampingRatio = 0.75f,
                                stiffness = 300f
                            )
                        }
            },
            label = "DynamicIslandAnimation"
        ) { expanded ->
            if (expanded) {
                ExpandedIslandView(
                    info = info,
                    stateText = largeText,
                    onCollapse = onCollapse,
                    onLinkClick = onLinkClick
                )
            } else {
                SmallIslandView(
                    stateText = smallText,
                    onExpand = onExpand,
                    onClick = onLinkClick
                )
            }
        }
    }
}

@Composable
private fun SmallIslandView(
    stateText: String,
    onExpand: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onLongPress = { onExpand() }
                )
            }
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(25.dp),
            painter = painterResource(id = R.drawable.honglib_ic_alarm),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(65.dp))

        Text(
            text = stateText,
            maxLines = 1,
            style = TextStyle(
                color = Color.White,
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = dpToSp(dp = 11),
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
    }
}

@Composable
private fun ExpandedIslandView(
    info: DynamicIslandInfo?,
    stateText: String,
    onCollapse: () -> Unit,
    onLinkClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCollapse()
                onLinkClick()
            }
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.honglib_ic_alarm),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "비행 알림",
                style = TextStyle(
                    color = Color.White,
                    fontFamily = pretendardFontFamily,
                    fontWeight = FontWeight.W700,
                    fontSize = dpToSp(dp = 12),
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = stateText,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = pretendardFontFamily,
                        fontWeight = FontWeight.W700,
                        fontSize = dpToSp(dp = 20),
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = info?.fromCity ?: "",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = pretendardFontFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = dpToSp(dp = 15),
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = info?.toCity ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = pretendardFontFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = dpToSp(dp = 15),
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }

            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
                model = info?.thumbnailUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}