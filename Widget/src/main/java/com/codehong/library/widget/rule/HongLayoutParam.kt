package com.codehong.library.widget.rule

enum class HongLayoutParam(val value: Int, val paramName: String) {
    MATCH_PARENT(-1, "MATCH_PARENT"),
    WRAP_CONTENT(-2, "WRAP_CONTENT")
    ;

    companion object {
        fun String?.toHongLayoutParamValue(): Int {
            return when (this) {
                MATCH_PARENT.paramName -> MATCH_PARENT.value
                WRAP_CONTENT.paramName -> WRAP_CONTENT.value
                else -> WRAP_CONTENT.value
            }
        }

        fun HongLayoutParam.toParamName(): String {
            return when (this) {
                MATCH_PARENT -> MATCH_PARENT.paramName
                WRAP_CONTENT -> WRAP_CONTENT.paramName
            }
        }

        // TODO HONG 구조 변경
        fun HongLayoutParam.toHongLayoutParamValue(): Int {
            return when (this) {
                MATCH_PARENT -> MATCH_PARENT.value
                WRAP_CONTENT -> WRAP_CONTENT.value
            }
        }

        fun Int?.toHongLayoutValueToParam(): String {
            return when (this) {
                MATCH_PARENT.value -> MATCH_PARENT.paramName
                WRAP_CONTENT.value -> WRAP_CONTENT.paramName
                else -> ""
            }
        }
    }
}
