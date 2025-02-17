package com.codehong.library.widget.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.WindowManager
import android.widget.Toast

object ToastUtil {
    @JvmStatic
    fun showToast(context: Context?, message: Int, length: Int? = Toast.LENGTH_SHORT) {
        showToast(context, context?.getString(message) ?: "", length)
    }

    @JvmStatic
    fun showToast(context: Context?, message: String?, length: Int? = Toast.LENGTH_SHORT) {
        if (context == null || message.isNullOrEmpty()) return

        try {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                try {
                    if (isSafeContext(context)) {
                        Toast.makeText(context, message, length ?: Toast.LENGTH_SHORT).show()
                    }
                } catch (e: WindowManager.BadTokenException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(context, message, length ?: Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun isSafeContext(context: Context?): Boolean {
        if (context == null) return false

        if (context is Activity) {
            return !context.isFinishing && !context.isDestroyed
        } else if (context is Activity) {
            return !context.isFinishing
        }

        return true
    }
}
