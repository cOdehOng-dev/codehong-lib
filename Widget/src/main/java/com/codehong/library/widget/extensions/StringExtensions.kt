package com.codehong.library.widget.extensions

import android.content.Context
import android.text.ParcelableSpan
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import androidx.core.text.set
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongFontManager
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.util.TextSpan

fun CharSequence?.lineBreakSyllable(): String? {
    return this?.toString()?.replace(" ", "\u00A0")
}


fun SpannableString.setTextSpan(
    context: Context?,
    builder: HongTextBuilder,
    lineBreakType: HongTextLineBreak
): SpannableString {
    if (context == null || builder.option.text.isNullOrEmpty()) return this

    val result = SpannableString.valueOf(this)
    val target = if (lineBreakType == HongTextLineBreak.SYLLABLE) {
        builder.option.text?.lineBreakSyllable()
    } else {
        builder.option.text
    }

    target?.toRegex()?.findAll(result)?.forEach {
        val spanList = arrayListOf<ParcelableSpan>()

        HongFontManager.getFont(
            context,
            (builder.option.fontType ?: HongFont.PRETENDARD_400).name
        )?.let {
            spanList.add(TextSpan(it))
        }

        spanList.add(ForegroundColorSpan(builder.option.colorHex.parseColor()))

        if (builder.option.isEnableUnderLine) {
            spanList.add(UnderlineSpan())
        }

        if (builder.option.isEnableCancelLine) {
            spanList.add(StrikethroughSpan())
        }

        spanList.forEach { span ->
            result[it.range.first, it.range.last + 1] = span
        }
    }

    return result
}