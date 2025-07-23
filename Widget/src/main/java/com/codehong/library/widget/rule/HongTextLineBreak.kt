package com.codehong.library.widget.rule

enum class HongTextLineBreak(val alias: String) {
    DEFAULT("default"), // 언어별 단말기 기본값
    SYLLABLE("syllable"), // 음절 단위 줄바꿈
    SPACE("space"); // 단어 단위 줄바꿈

    companion object {
        fun String?.toHongTextLineBreak(): HongTextLineBreak {
            return entries.firstOrNull { it.alias.equals(this, true) } ?: DEFAULT
        }

        fun HongTextLineBreak?.toHongTextLineBreakToAlias(): String {
            return this?.alias ?: DEFAULT.alias
        }


    }
}
