package com.codehong.library.widget.image

import android.graphics.Bitmap
import coil.size.Size
import coil.transform.Transformation

class WhiteBackgroundTransformation(
    private val threshold: Int = 230
) : Transformation {

    override val cacheKey = "WhiteBackgroundTransformation_$threshold"

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        val result = input.copy(Bitmap.Config.ARGB_8888, true)
        for (x in 0 until result.width) {
            for (y in 0 until result.height) {
                val pixel = result.getPixel(x, y)
                val r = android.graphics.Color.red(pixel)
                val g = android.graphics.Color.green(pixel)
                val b = android.graphics.Color.blue(pixel)
                if (r >= threshold && g >= threshold && b >= threshold) {
                    result.setPixel(x, y, android.graphics.Color.TRANSPARENT)
                }
            }
        }
        return result
    }
}