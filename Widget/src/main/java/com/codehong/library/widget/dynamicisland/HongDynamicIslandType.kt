package com.codehong.library.widget.dynamicisland

enum class HongDynamicIslandType(val type: Int) {
    LODGING(1),
    AIR(2),
    ETC(3)
    ;

    companion object {
        fun Int.toType(): HongDynamicIslandType {
            return when (this) {
                LODGING.type -> LODGING
                AIR.type -> AIR
                ETC.type -> ETC
                else -> ETC
            }
        }
        fun HongDynamicIslandType.toStateMessage(
            diffMillis: Long,
            isSmall: Boolean
        ): String {
            val diffMinutes = diffMillis / (60 * 1000)

            val betweenOneMinOneHourMessage = if (isSmall) {
                when (this) {
                    LODGING -> "체크인 전"
                    AIR -> "탑승 전"
                    else -> ""
                }
            } else {
                when (this) {
                    LODGING -> "${diffMinutes}분 뒤 체크인 예정"
                    AIR -> "${diffMinutes}분 뒤 탑승 예정"
                    else -> ""
                }
            }

            val underOneMinMessage = if (isSmall) {
                when (this) {
                    LODGING -> "체크인 준비"
                    AIR -> "탑승 준비"
                    else -> ""
                }
            } else {
                when (this) {
                    LODGING -> "잠시 후 체크인 예정"
                    AIR -> "잠시 후 탑승 예정"
                    else -> ""
                }
            }

            val startMessage = if (isSmall) {
                when (this) {
                    LODGING -> "체크인"
                    AIR -> "탑승"
                    else -> ""
                }
            } else {
                when (this) {
                    LODGING -> "지금 체크인하세요!"
                    AIR -> "지금 탑승하세요!"
                    else -> ""
                }
            }

            return when {
                diffMinutes in 1..60 -> betweenOneMinOneHourMessage
                diffMinutes < 1 && diffMillis > 0 -> underOneMinMessage
                else -> startMessage
            }
        }
    }
}