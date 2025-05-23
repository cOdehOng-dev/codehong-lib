package com.codehong.library.widget.dialog

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

object HongDialog {

    @JvmStatic
    fun showSingleButtonDialog(
        context: Context?,
        title: String?,
        message: String?,
        buttonText: String?,
        cancel: ((DialogInterface) -> Unit)? = null,
        click: ((DialogInterface, Int) -> Unit)? = null,
        failure: (() -> Unit)? = null
    ) {
        if (context == null) {
            failure?.invoke()
            return
        }

        val alert = AlertDialog.Builder(context)
            .setTitle(
                title.takeIf {
                    !it.isNullOrEmpty()
                }?.let { title } ?: run { "알림" }
            )
            .setMessage(message)
            .setPositiveButton(buttonText ?: "확인", click)
            .setCancelable(cancel != null)
            .setOnCancelListener(cancel)
        try {
            if (context is Activity) {
                if (!context.isFinishing) alert.show()
            } else {
                alert.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun showSingleButtonDialog(
        context: Context?,
        @StringRes title: Int?,
        @StringRes message: Int,
        @StringRes buttonText: Int?,
        cancel: ((DialogInterface) -> Unit)? = null,
        click: ((DialogInterface, Int) -> Unit)? = null,
        failure: (() -> Unit)? = null
    ) {
        showSingleButtonDialog(
            context,
            title = title?.let { context?.getString(it) },
            message = context?.getString(message),
            buttonText = buttonText?.let { context?.getString(it) },
            cancel = cancel,
            click = click,
            failure = failure
        )
    }

    @JvmStatic
    fun showDoubleButtonDialog(
        context: Context?,
        title: String?,
        message: String?,
        positiveText: String?,
        negativeText: String?,
        positiveClick: ((DialogInterface, Int) -> Unit)? = null,
        negativeClick: ((DialogInterface, Int) -> Unit)? = null,
        cancel: ((DialogInterface) -> Unit)? = null,
        failure: (() -> Unit)? = null
    ) {
        if (context == null) {
            failure?.invoke()
            return
        }

        val alert = AlertDialog.Builder(context)
            .setTitle(
                title.takeIf {
                    !it.isNullOrEmpty()
                }?.let { title } ?: run { "알림" }
            )
            .setMessage(message)
            .setPositiveButton(
                positiveText ?: "확인",
                positiveClick
            )
            .setNegativeButton(
                negativeText ?: "취소",
                negativeClick
            )
            .setCancelable(cancel != null)
            .setOnCancelListener(cancel)

        try {
            if (context is Activity) {
                if (!context.isFinishing) alert.show()
            } else {
                alert.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun showDoubleButtonDialog(
        context: Context?,
        @StringRes title: Int?,
        @StringRes message: Int,
        @StringRes positiveText: Int?,
        @StringRes negativeText: Int?,
        positiveClick: ((DialogInterface, Int) -> Unit)? = null,
        negativeClick: ((DialogInterface, Int) -> Unit)? = null,
        cancel: ((DialogInterface) -> Unit)? = null,
        failure: (() -> Unit)? = null
    ) {
        showDoubleButtonDialog(
            context,
            title = title?.let { context?.getString(it) },
            message = context?.getString(message),
            positiveText = positiveText?.let { context?.getString(it) },
            negativeText = negativeText?.let { context?.getString(it) },
            positiveClick = positiveClick,
            negativeClick = negativeClick,
            cancel = cancel,
            failure = failure
        )
    }

}