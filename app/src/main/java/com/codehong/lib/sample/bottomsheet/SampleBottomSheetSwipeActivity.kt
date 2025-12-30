package com.codehong.lib.sample.bottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.bottomsheet.swipe.HongBottomSheetSwipe
import com.codehong.library.widget.bottomsheet.swipe.HongBottomSheetSwipeBuilder
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose

class SampleBottomSheetSwipeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HongBottomSheetSwipe(
                HongBottomSheetSwipeBuilder()
                    .onClose {
                        finish()
                    }
                    .content {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(300.dp)
                                    .hongBackground(
                                        color = HongColor.PURPLE_100
                                    )
                            )
                            HongTextCompose(
                                HongTextBuilder()
                                    .padding(
                                        HongSpacingInfo(
                                            top = 30f,
                                            bottom = 10f
                                        )
                                    )
                                    .text("아름다운 이땅에")
                                    .typography(HongTypo.BODY_15)
                                    .color(HongColor.WHITE_60.hex)
                                    .textAlign(HongTextAlign.CENTER)
                                    .applyOption()
                            )

                            HongTextCompose(
                                HongTextBuilder()
                                    .padding(
                                        HongSpacingInfo(
                                            bottom = 10f
                                        )
                                    )
                                    .text("금수 강산에")
                                    .typography(HongTypo.BODY_13)
                                    .color(HongColor.WHITE_60.hex)
                                    .textAlign(HongTextAlign.CENTER)
                                    .applyOption()
                            )

                            HongTextCompose(
                                HongTextBuilder()
                                    .padding(
                                        HongSpacingInfo(
                                            bottom = 10f
                                        )
                                    )
                                    .text("단군할아버지가 터를 잡으시고")
                                    .typography(HongTypo.BODY_17_B)
                                    .color(HongColor.WHITE_60.hex)
                                    .textAlign(HongTextAlign.CENTER)
                                    .applyOption()
                            )
                        }
                    }
                    .bottomSheetContent {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .hongBackground(
                                    color = HongColor.GRAY_10
                                )
                        )
                    }
                    .applyOption()
            )
        }
    }

}