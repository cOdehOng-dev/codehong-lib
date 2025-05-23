package com.codehong.library.widget.dynamicisland

import android.os.Parcelable
import com.codehong.library.widget.util.HongDateUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class DynamicIslandInfo(
    val appName: String? = null,
    val fromCity: String? = null,
    val toCity: String? = null,
    val thumbnailUrl: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val link: String? = null
) : Parcelable {
    val startTime get() = HongDateUtil.parseDateTimeString(startDate, "yyyyMMddHHmmss")
    val endTime get() = HongDateUtil.parseDateTimeString(endDate, "yyyyMMddHHmmss")

    val dispStartTime
        get() = startTime - 1000 * 60 * 120

    val dispEndTime
        get() = startTime + 1000 * 60 * 10

    override fun toString(): String {
        return "DynamicIslandInfo( " +
                "appName=$appName, " +
                "fromCity=$fromCity, " +
                "toCity=$toCity, " +
                "thumbnailUrl=$thumbnailUrl, " +
                "startDate=$startDate, " +
                "endDate=$endDate, " +
                "link=$link, " +
                "startTime=$startTime, " +
                "endTime=$endTime, " +
                "dispStartTime=$dispStartTime, " +
                "dispEndTime=$dispEndTime" +
                ")"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DynamicIslandInfo

        if (appName != other.appName) return false
        if (fromCity != other.fromCity) return false
        if (toCity != other.toCity) return false
        if (thumbnailUrl != other.thumbnailUrl) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false
        if (link != other.link) return false

        return true
    }

    override fun hashCode(): Int {
        var result = appName?.hashCode() ?: 0
        result = 31 * result + (fromCity?.hashCode() ?: 0)
        result = 31 * result + (toCity?.hashCode() ?: 0)
        result = 31 * result + (thumbnailUrl?.hashCode() ?: 0)
        result = 31 * result + (startDate?.hashCode() ?: 0)
        result = 31 * result + (endDate?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        return result
    }
}
