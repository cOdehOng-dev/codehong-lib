package com.codehong.library.widget.rule

enum class HongLayoutParam(val value: Int, val paramName: String) {
    MATCH_PARENT(-1, "MATCH_PARENT"),
    WRAP_CONTENT(-2, "WRAP_CONTENT")
}

fun HongLayoutParam.toHongLayoutParamValue(): Int {
    return when (this) {
        HongLayoutParam.MATCH_PARENT -> HongLayoutParam.MATCH_PARENT.value
        HongLayoutParam.WRAP_CONTENT -> HongLayoutParam.WRAP_CONTENT.value
    }
}

fun HongLayoutParam.toParamName(): String {
    return when (this) {
        HongLayoutParam.MATCH_PARENT -> HongLayoutParam.MATCH_PARENT.paramName
        HongLayoutParam.WRAP_CONTENT -> HongLayoutParam.WRAP_CONTENT.paramName
    }
}

fun Int?.toHongLayoutValueToParam(): String {
    return when (this) {
        HongLayoutParam.MATCH_PARENT.value -> HongLayoutParam.MATCH_PARENT.paramName
        HongLayoutParam.WRAP_CONTENT.value -> HongLayoutParam.WRAP_CONTENT.paramName
        else -> HongLayoutParam.WRAP_CONTENT.paramName
    }
}


fun String?.toHongLayoutParamValue(): Int {
    return when (this) {
        HongLayoutParam.MATCH_PARENT.paramName -> HongLayoutParam.MATCH_PARENT.value
        HongLayoutParam.WRAP_CONTENT.paramName -> HongLayoutParam.WRAP_CONTENT.value
        else -> HongLayoutParam.WRAP_CONTENT.value
    }
}
