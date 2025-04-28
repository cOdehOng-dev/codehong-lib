package com.codehong.library.widget.rule.typo

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import java.util.Hashtable

object HongFontManager {

    private val cache = hashMapOf<String, Typeface>()
    private val fontCache = Hashtable<String, Typeface?>()

    operator fun get(context: Context, name: String): Typeface? {
        var tf = fontCache[name]
        if (tf == null) {
            tf = try {
                ResourcesCompat.getFont(context, getFontResId(context, name))
            } catch (e: Exception) {
                return null
            }
            fontCache[name] = tf
        }
        return tf
    }

    fun getFont(context: Context, fontType: String?): Typeface? {
        if (fontType == null) return getPretendard400(context)
        return when (fontType) {
            HongFont.PRETENDARD_400.name -> getPretendard400(context)
            HongFont.PRETENDARD_500.name -> getPretendard500(context)
            HongFont.PRETENDARD_600.name -> getPretendard600(context)
            HongFont.PRETENDARD_700.name -> getPretendard700(context)
            HongFont.PRETENDARD_800.name -> getPretendard800(context)
            else -> get(context, fontType)
        }
    }

    fun getFont(context: Context, fontType: HongFont?): Typeface? {
        if (fontType == null) return getPretendard400(context)
        return when (fontType) {
            HongFont.PRETENDARD_400 -> getPretendard400(context)
            HongFont.PRETENDARD_500 -> getPretendard500(context)
            HongFont.PRETENDARD_600 -> getPretendard600(context)
            HongFont.PRETENDARD_700 -> getPretendard700(context)
            HongFont.PRETENDARD_800 -> getPretendard800(context)
        }
    }

    private fun getFontResId(context: Context, string: String?): Int {
        return context.resources.getIdentifier(string, "font", context.packageName)
    }

    fun getPretendard400(context: Context): Typeface? {
        return get(context, HongFont.PRETENDARD_400.fileName)
    }

    fun getPretendard500(context: Context): Typeface? {
        return get(context, HongFont.PRETENDARD_500.fileName)
    }

    fun getPretendard600(context: Context): Typeface? {
        return get(context, HongFont.PRETENDARD_600.fileName)
    }

    fun getPretendard700(context: Context): Typeface? {
        return get(context, HongFont.PRETENDARD_700.fileName)
    }

    fun getPretendard800(context: Context): Typeface? {
        return get(context, HongFont.PRETENDARD_800.fileName)
    }
}
