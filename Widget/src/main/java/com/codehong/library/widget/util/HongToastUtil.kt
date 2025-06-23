package com.codehong.library.widget.util

import android.content.Context
import android.widget.Toast

object HongToastUtil {

    fun showToast(context: Context?, message: Int, length: Int? = Toast.LENGTH_SHORT) {
        showToast(context, context?.getString(message) ?: "", length)
    }

    fun showToast(context: Context?, message: String?, length: Int? = Toast.LENGTH_SHORT) {
        if (context == null || message.isNullOrEmpty()) return

        try {
            Toast.makeText(context, message, length ?: Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
