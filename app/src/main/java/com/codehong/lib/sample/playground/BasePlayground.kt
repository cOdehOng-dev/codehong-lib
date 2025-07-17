package com.codehong.lib.sample.playground

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType

interface BasePlayground<T: HongWidgetCommonOption> {

    val activity: PlaygroundActivity
    val widgetType: HongWidgetType
    var previewOption: T


    fun executePreview() {
        activity.applyPreview(previewOption)
    }

    fun commonPreviewOption(
        defWidth: Int = previewOption.width,
        defHeight: Int = previewOption.height,
        defMargin: HongSpacingInfo = previewOption.margin,
        defPadding: HongSpacingInfo = previewOption.padding,
        useWidth: Boolean = true,
        useHeight: Boolean = true,
        useMargin: Boolean = true,
        usePadding: Boolean = true,
        selectWidth: (Int) -> Unit = {},
        selectHeight: (Int) -> Unit = {},
        selectMargin: (HongSpacingInfo) -> Unit = {},
        selectPadding: (HongSpacingInfo) -> Unit = {}
    ) {
        if (useWidth || useHeight) {
            PlaygroundManager.addSizeOptionPreview(
                activity = activity,
                width = defWidth,
                height = defHeight,
                selectWidth = selectWidth,
                selectHeight = selectHeight,
                useWidth = useWidth,
                useHeight = useHeight,
            )
        }

        if (useMargin) {
            PlaygroundManager.addMarginOptionPreview(
                activity = activity,
                margin = defMargin,
                callback = selectMargin
            )
        }

        if (usePadding) {
            PlaygroundManager.addPaddingOptionPreview(
                activity = activity,
                padding = defPadding,
                selectPadding = selectPadding,
            )
        }
    }
}