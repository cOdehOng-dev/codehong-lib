package com.codehong.library.widget.rule.keyboard

import androidx.compose.ui.text.input.KeyboardType

enum class HongKeyboardType {
    TEXT, NUMBER, PHONE, EMAIL, PASSWORD, NUMBER_PASSWORD, URL, DECIMAL, ASCIITEXT;
    
    companion object {
        fun HongKeyboardType.toKeyboardType(): KeyboardType {
            return when (this) {
                TEXT -> KeyboardType.Text
                NUMBER -> KeyboardType.Number
                PHONE -> KeyboardType.Phone
                EMAIL -> KeyboardType.Email
                PASSWORD -> KeyboardType.Password
                NUMBER_PASSWORD -> KeyboardType.NumberPassword
                URL -> KeyboardType.Uri
                DECIMAL -> KeyboardType.Decimal
                ASCIITEXT -> KeyboardType.Ascii
            }
        }
    }
}