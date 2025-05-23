package com.codehong.library.widget.util

import android.graphics.Paint
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import java.util.regex.Pattern

class TextSpan(private val newType: Typeface) : TypefaceSpan("") {

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType)
    }


    companion object {

        private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
            val oldStyle: Int
            val old: Typeface = paint.typeface
            oldStyle = old.style
            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }
            paint.typeface = tf
        }

        fun setFont(source: SpannableStringBuilder, target: String, typeFace: Typeface): SpannableStringBuilder {

            val replaceStr = StringBuffer()
            for(i in target.indices) {
                replaceStr.append("X")
            }

            var full = SpannableStringBuilder(source)
            while (full.indexOf(target) > -1) {

                val start = full.indexOf(target)
                if(start > -1) {

                    val end = start + target.length

                    source.setSpan(
                        TextSpan(typeFace),
                        start,
                        end,
                        Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                    )

                    full = full.replace(start, end, replaceStr)
                }

            }

            return source
        }

        fun setColor(source: SpannableStringBuilder, target: String, color: Int): SpannableStringBuilder {

            val replaceStr = StringBuffer()
            for(i in target.indices) {
                replaceStr.append("X")
            }

            var full = SpannableStringBuilder(source)
            while (full.indexOf(target) > -1) {

                val start = full.indexOf(target)
                if(start > -1) {

                    val end = start + target.length

                    source.setSpan(
                        ForegroundColorSpan(color),
                        start,
                        end,
                        Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                    )

                    full = full.replace(start, end, replaceStr)
                }

            }

            return source
        }

        fun setSize(source: SpannableStringBuilder, target: String, rate: Float): SpannableStringBuilder {

            val replaceStr = StringBuffer()
            for(i in target.indices) {
                replaceStr.append("X")
            }

            var full = SpannableStringBuilder(source)
            while (full.indexOf(target) > -1) {

                val start = full.indexOf(target)
                if(start > -1) {

                    val end = start + target.length

                    source.setSpan(
                        RelativeSizeSpan(rate),
                        start,
                        end,
                        Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                    )

                    full = full.replace(start, end, replaceStr)
                }

            }

            return source
        }

        fun setTextLine(
            source: SpannableStringBuilder,
            target: String,
            isUnderLine: Boolean = false,
            isCancelLine: Boolean = false
        ): SpannableStringBuilder {
            val replaceStr = StringBuffer()

            for (i in target.indices) {
                replaceStr.append("X")
            }

            var full = SpannableStringBuilder(source)
            while (full.indexOf(target) > -1) {
                val start = full.indexOf(target)

                if (start > -1) {
                    val end = start + target.length

                    if (isUnderLine) {
                        source.setSpan(UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
                    }

                    if (isCancelLine) {
                        source.setSpan(StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
                    }

                    full = full.replace(start, end, replaceStr)
                }
            }

            return source
        }

        private fun getPattern(target: String): Pattern {
            return Pattern.compile(target, Pattern.MULTILINE or Pattern.CASE_INSENSITIVE or Pattern.UNICODE_CASE)
        }
    }
}
