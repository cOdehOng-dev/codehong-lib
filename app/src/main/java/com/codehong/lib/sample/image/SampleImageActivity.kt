package com.codehong.lib.sample.image

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.layout.ContentScale
import com.codehong.lib.sample.SampleMenu
import com.codehong.lib.sample.SampleScaffold
import com.codehong.library.widget.R
import com.codehong.library.widget.image.HongImage

class SampleImageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleScaffold(title = "Image") {
//                SampleMenu(
//                    title = "drawable image",
//                    horizontalPadding = 20
//                ) {
//                    HongImage(
//                        width = 200,
//                        height = 200,
//                        drawableResId = R.drawable.ic_sample_image,
//                        allRadius = 12,
//                        contentScale = ContentScale.Crop
//                    )
//                }

                SampleMenu(
                    title = "url image",
                    horizontalPadding = 20
                ) {
                    HongImage(
                        width = 200,
                        height = 200,
                        imageUrl = "https://images.unsplash.com/photo-1735832489994-96adfc4db2dd?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        allRadius = 12,
                        contentScale = ContentScale.Crop
                    )
                }

                SampleMenu(
                    title = "url image with error",
                    horizontalPadding = 20
                ) {
                    HongImage(
                        width = 200,
                        height = 200,
                        imageUrl = "https://images.unsplash.com/photo-173583248" +
                                "b=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        allRadius = 12,
                        contentScale = ContentScale.Crop,
                        error = R.drawable.honglib_bg_image_error
                    )
                }

                SampleMenu(
                    title = "url image with error",
                    horizontalPadding = 20
                ) {
                    HongImage(
                        width = 200,
                        height = 200,
                        imageUrl = "https://images.unsplash.com/photo-173583248" +
                                "b=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        allRadius = 12,
                        contentScale = ContentScale.Crop,
                        error = R.drawable.honglib_bg_image_error
                    )
                }

                SampleMenu(
                    title = "url image with placheholder",
                    horizontalPadding = 20
                ) {
                    HongImage(
                        width = 200,
                        height = 200,
                        imageUrl = "https://images.unsplash.com/photo-173583248" +
                                "b=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        allRadius = 12,
                        contentScale = ContentScale.Crop,
                        placeholder = R.drawable.honglib_bg_image_placeholder
                    )
                }
            }
        }
    }
}
