package com.codehong.lib.sample.swipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.swipe.HongSwipeContainer
import com.codehong.library.widget.swipe.HongSwipeContainerBuilder
import com.codehong.library.widget.util.HongToastUtil

class SampleSwipeContainerActivity : BaseSampleComposeActivity() {

    private val option1 = HongSwipeContainerBuilder()
        .buttonText("삭제")
        .onClickButton {
            HongToastUtil.showToast(this, "Button Clicked!")
        }
        .content {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .hongBackground(HongColor.DARK_GRAY_100)
            )
        }
        .applyOption()

    private val option2 = HongSwipeContainerBuilder()
        .buttonTextColor(HongColor.MAIN_ORANGE_100)
        .buttonColor(HongColor.BLUE_100                                                )
        .buttonText("삭제")
        .onClickButton {
            HongToastUtil.showToast(this, "Button Clicked!")
        }
        .content {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .hongBackground(HongColor.BLACK_100)
            )
        }
        .applyOption()

    private val option3 = HongSwipeContainerBuilder()
        .buttonText("삭제")
        .onClickButton {
            HongToastUtil.showToast(this, "Button Clicked!")
        }
        .content {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .hongBackground(HongColor.BLUE_100)
            )
        }
        .applyOption()

    @Composable
    override fun InitCompose() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HongSwipeContainer(option1)
            HongSwipeContainer(option2)
            HongSwipeContainer(option3)
        }
    }
}