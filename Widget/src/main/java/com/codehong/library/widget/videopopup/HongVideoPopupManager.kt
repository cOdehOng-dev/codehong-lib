package com.codehong.library.widget.videopopup

import android.content.Context
import com.codehong.library.widget.const.HongConst
import com.codehong.library.widget.util.DateUtil

object HongVideoPopupManager {

    fun isAllowDisplaying(context: Context?): Boolean {
        if (context == null) {
            return false
        }

        try {
            val lastHiddenMillis = getOneDayLastSeenTimestamp(context)
            if (lastHiddenMillis == HongVideoPopupConst.NO_VALUE) return true

            val checkNoShowTime = DateUtil.checkNoShowTime(lastHiddenMillis, DateUtil.MINUTES_IN_A_DAY)
            return checkNoShowTime.first
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun getOneDayLastSeenTimestamp(context: Context?): Long {
        if (context == null) {
            return HongVideoPopupConst.NO_VALUE
        }

        val prefs =
            context.getSharedPreferences(HongConst.PREF_DATA_STORE, Context.MODE_PRIVATE)
        return prefs.getLong(HongVideoPopupConst.KEY_VIDEO_POPUP_NO_SHOW_ONE_DAY, HongVideoPopupConst.NO_VALUE)
    }

    fun saveOneDayLastSeenTimestamp(context: Context?) {
        if (context == null) {
            return
        }
        val prefs = context.getSharedPreferences(HongConst.PREF_DATA_STORE, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val nowMillis = System.currentTimeMillis()
        editor.putLong(HongVideoPopupConst.KEY_VIDEO_POPUP_NO_SHOW_ONE_DAY, nowMillis)
        editor.apply()
    }


    fun resetLastSeenTimestamp(context: Context?) {
        if (context == null) {
            return
        }
        val prefs =
            context.getSharedPreferences(HongConst.PREF_DATA_STORE, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putLong(HongVideoPopupConst.KEY_VIDEO_POPUP_NO_SHOW_ONE_DAY, HongVideoPopupConst.NO_VALUE)
        editor.apply()
    }
}
