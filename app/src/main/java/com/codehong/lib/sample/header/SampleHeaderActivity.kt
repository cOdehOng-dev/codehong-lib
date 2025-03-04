package com.codehong.lib.sample.header

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codehong.lib.ui.SampleHeader
import com.codehong.lib.ui.SampleMenu
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.header.HongComposeCloseHeader

class SampleHeaderActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    SampleHeader(title = "헤더")
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = ColorType.WHITE_100.colorResId))
                        .padding(it)
                        .padding(horizontal = 20.dp)
                ) {
                    MarginTopOrBottom(30)
                    SampleMenu(title = "CloseHeader(gravity: CenterStart)") {
                        HongComposeCloseHeader(
                            closeClick = {
                                finish()
                            },
                            contentGravity = Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp)
                                    .background(
                                        colorResource(id = ColorType.PRIMARY_MINT.colorResId)
                                    )
                            )
                        }
                    }

                    SampleMenu(title = "CloseHeader(gravity: Center)") {
                        HongComposeCloseHeader(
                            closeClick = {
                                finish()
                            },
                            contentGravity = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp)
                                    .background(
                                        colorResource(id = ColorType.PRIMARY_MINT.colorResId)
                                    )
                            )
                        }
                    }

                    SampleMenu(title = "CloseHeader(gravity: CenterEnd)") {
                        HongComposeCloseHeader(
                            closeClick = {
                                finish()
                            },
                            contentGravity = Alignment.CenterEnd
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp)
                                    .background(
                                        colorResource(id = ColorType.PRIMARY_MINT.colorResId)
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}
