package com.codehong.library.widget.model.keyboard

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction

object KeyboardTypeUtil {
    fun KeyboardType.getOption(): KeyboardOptions {
        return when (this) {
            KeyboardType.DONE -> KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            KeyboardType.GO -> KeyboardOptions.Default.copy(imeAction = ImeAction.Go)
            KeyboardType.SEARCH -> KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
            KeyboardType.SEND -> KeyboardOptions.Default.copy(imeAction = ImeAction.Send)
            KeyboardType.NEXT -> KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            KeyboardType.PREVIOUS -> KeyboardOptions.Default.copy(imeAction = ImeAction.Previous)
        }
    }

    fun KeyboardType.getAction(
        callback: (KeyboardActionScope) -> Unit
    ): KeyboardActions {
        return when (this) {
            KeyboardType.DONE -> KeyboardActions(
                onDone = {
                    callback.invoke(this)
                }
            )

            KeyboardType.GO -> KeyboardActions(
                onGo = {
                    callback.invoke(this)
                }
            )

            KeyboardType.SEARCH -> KeyboardActions(
                onSearch = {
                    callback.invoke(this)
                }
            )

            KeyboardType.SEND -> KeyboardActions(
                onSend = {
                    callback.invoke(this)
                }
            )

            KeyboardType.NEXT -> KeyboardActions(
                onNext = {
                    callback.invoke(this)
                }
            )

            KeyboardType.PREVIOUS -> KeyboardActions(
                onPrevious = {
                    callback.invoke(this)
                }
            )
        }
    }
}
