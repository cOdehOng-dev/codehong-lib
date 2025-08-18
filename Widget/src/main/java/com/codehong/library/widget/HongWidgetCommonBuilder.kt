package com.codehong.library.widget

import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor

interface HongWidgetCommonBuilder<T : HongWidgetCommonOption, S> {

    val builder: S

    val option: HongWidgetCommonOption

    fun width(width: Int?): S {
        width?.let { option.width = it }
        return builder
    }

    fun height(height: Int?): S {
        height?.let { option.height = it }
        return builder
    }

    fun margin(margin: HongSpacingInfo): S {
        option.margin = margin
        return builder
    }

    fun padding(padding: HongSpacingInfo): S {
        option.padding = padding
        return builder
    }

    fun backgroundColor(color: HongColor): S {
        backgroundColor(color.hex)
        return builder
    }

    fun backgroundColor(colorHex: String): S {
        option.backgroundColorHex = colorHex
        return builder
    }

    fun onClick(onClick: ((T) -> Unit)?): S {
        option.click = {
            onClick?.invoke(it as T)
        }
        return builder
    }

    fun applyOption(): T {
        return option as T
    }
}