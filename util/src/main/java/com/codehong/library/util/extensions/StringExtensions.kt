package com.codehong.library.util.extensions

import androidx.core.net.toUri
import com.codehong.library.debugtool.log.TimberUtil

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

fun String?.aspectRatio(): Float {
    if (this.isNullOrEmpty()) return 0f
    val parts = this.split(":")
    return if (parts.size == 2) {
        parts[0].toFloat() / parts[1].toFloat()
    } else {
        1f / 1f
    }
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

fun String?.isUrl(): Boolean {
    if (this.isNullOrEmpty()) return false
    return try {
        val uri = this.toUri()
        uri.scheme in listOf("http", "https") && !uri.host.isNullOrEmpty()
    } catch (e: Exception) {
        TimberUtil.e("$e")
        false
    }
}