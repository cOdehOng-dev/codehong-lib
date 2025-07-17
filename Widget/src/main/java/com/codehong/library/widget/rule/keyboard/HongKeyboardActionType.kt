package com.codehong.library.widget.rule.keyboard

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.text.input.ImeAction

enum class HongKeyboardActionType {
    GO, SEARCH, SEND, NEXT, DONE, PREVIOUS;

    companion object {
        fun HongKeyboardActionType.toKeyboardActions(
            callback: (KeyboardActionScope) -> Unit
        ): KeyboardActions {
            return when (this) {
                DONE -> KeyboardActions(
                    onDone = {
                        callback.invoke(this)
                    }
                )

                GO -> KeyboardActions(
                    onGo = {
                        callback.invoke(this)
                    }
                )

                SEARCH -> KeyboardActions(
                    onSearch = {
                        callback.invoke(this)
                    }
                )

                SEND -> KeyboardActions(
                    onSend = {
                        callback.invoke(this)
                    }
                )

                NEXT -> KeyboardActions(
                    onNext = {
                        callback.invoke(this)
                    }
                )

                PREVIOUS -> KeyboardActions(
                    onPrevious = {
                        callback.invoke(this)
                    }
                )
            }
        }
        
        fun HongKeyboardActionType.toImeAction(): ImeAction {
            return when (this) {
                DONE -> ImeAction.Done
                GO -> ImeAction.Go
                SEARCH -> ImeAction.Search
                SEND -> ImeAction.Send
                NEXT -> ImeAction.Next
                PREVIOUS -> ImeAction.Previous
            }
        }

    }
}