package com.codehong.library.widget.extensions

import android.content.Context
import android.text.ParcelableSpan
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.core.text.set
import com.codehong.library.debugtool.log.TimberUtil
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongFontManager
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.util.TextSpan
fun String?.toColor(): Color {
    try {
        if (this.isNullOrEmpty()) {
            TimberUtil.e("컬러 hex 문자열이 비어있거나 null입니다")
            return HongColor.TRANSPARENT.hex.toColor()
        }

        val hex = this.removePrefix("#")

        return when (hex.length) {
            6 -> { // RGB (e.g. #RRGGBB)
                val r = hex.substring(0, 2).toInt(16)
                val g = hex.substring(2, 4).toInt(16)
                val b = hex.substring(4, 6).toInt(16)
                Color(r, g, b)
            }

            8 -> { // ARGB (e.g. #AARRGGBB)
                val a = hex.substring(0, 2).toInt(16)
                val r = hex.substring(2, 4).toInt(16)
                val g = hex.substring(4, 6).toInt(16)
                val b = hex.substring(6, 8).toInt(16)
                Color(r, g, b, a)
            }

            else -> HongColor.TRANSPARENT.hex.toColor()
        }
    } catch (e: Exception) {
        return HongColor.TRANSPARENT.hex.toColor()
    }
}

fun String?.toParseColor(): Int {
    if (this.isNullOrEmpty()) {
        TimberUtil.d("컬러 hex 문자열이 비어있거나 null입니다")
        return HongColor.TRANSPARENT.hex.parseColor()
    }

    return android.graphics.Color.parseColor(this)
}


fun String?.toFigureInt(): Int {
    return if (this.isNullOrEmpty()) {
        0
    } else {
        this.toInt()
    }
}

fun String?.toFigureFloat(): Float {
    return if (this.isNullOrEmpty()) {
        0f
    } else {
        this.toFloat()
    }
}

fun String?.toFigureLong(): Long {
    return if (this.isNullOrEmpty()) {
        0L
    } else {
        this.toLong()
    }
}

fun String?.parseColor(): Int {
    return try {
        android.graphics.Color.parseColor(
            if (this.isNullOrEmpty()
                || this.equals("null", true)
                || this.equals("none", true)
                || this.equals("blank", true)
                || this.equals("empty", true)
            ) {
                "#00000000"
            } else if (this.startsWith("#")) {
                when (this.length) {
                    7 -> this.replace("#", "#ff")
                    9 -> this
                    else -> "#00000000"
                }
            } else {
                when (this.length) {
                    6 -> "#ff$this"
                    8 -> "#$this"
                    else -> "#00000000"
                }
            }
        )
    } catch (e: Exception) {
        Log.e("ERROR", e.toString())
        android.graphics.Color.TRANSPARENT
    }
}

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

fun String?.aspectRatio(): Float {
    if (this.isNullOrEmpty()) return 0f
    val parts = this.split(":")
    return if (parts.size == 2) {
        parts[0].toFloat() / parts[1].toFloat()
    } else {
        1f / 1f
    }
}

fun String?.splitAtFirstDigit(): Pair<String, String> {
    if (this.isNullOrEmpty()) return Pair("", "")
    // 1. 처음으로 숫자가 나오는 인덱스를 찾습니다.
    val index = this.indexOfFirst { it.isDigit() }

    // 2. 숫자가 하나도 없다면, 전체를 첫 번째 요소로 반환합니다.
    if (index == -1) {
        return this.trim() to ""
    }

    // 3. 인덱스를 기준으로 자르고, 앞뒤 공백을 제거(trim)하여 반환합니다.
    val firstPart = this.substring(0, index).trim()
    val secondPart = this.substring(index).trim()

    return firstPart to secondPart
}

/**
 * 문자열을 콤마(,)로 구분하되 괄호 안의 콤마는 무시하며,
 * "메인 텍스트" to "괄호 안 텍스트" 형태의 Pair 리스트로 반환합니다.
 */
fun String?.splitAndParseWithParentheses(): List<Pair<String, String>> {
    if (this.isNullOrEmpty()) return emptyList()

    val result = mutableListOf<Pair<String, String>>()
    val buffer = StringBuilder()
    var depth = 0

    // 내부 함수: 버퍼에 쌓인 문자열을 분석해서 결과에 추가
    fun addBufferToResult() {
        if (buffer.isBlank()) return // 공백만 있는 경우 무시

        val rawItem = buffer.toString().trim()
        val firstParenIndex = rawItem.indexOf('(')
        val lastParenIndex = rawItem.lastIndexOf(')')

        if (firstParenIndex != -1 && lastParenIndex > firstParenIndex) {
            // 괄호가 있는 경우: "Banana (Yellow)" -> "Banana" to "Yellow"
            val outerText = rawItem.take(firstParenIndex).trim()
            val innerText = rawItem.substring(firstParenIndex + 1, lastParenIndex).trim()
            result.add(outerText to innerText)
        } else {
            // 괄호가 없는 경우: "Apple" -> "Apple" to ""
            result.add(rawItem to "")
        }
        buffer.clear()
    }

    // 문자열 순회
    for (char in this) {
        when (char) {
            '(' -> {
                depth++
                buffer.append(char)
            }
            ')' -> {
                if (depth > 0) depth--
                buffer.append(char)
            }
            ',' -> {
                if (depth == 0) {
                    // 괄호 밖의 콤마일 때만 아이템 분리
                    addBufferToResult()
                } else {
                    // 괄호 안의 콤마는 문자로 취급
                    buffer.append(char)
                }
            }
            else -> buffer.append(char)
        }
    }

    // 마지막 남은 버퍼 처리
    addBufferToResult()

    return result
}