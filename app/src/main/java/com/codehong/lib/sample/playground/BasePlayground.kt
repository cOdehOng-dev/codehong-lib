package com.codehong.lib.sample.playground

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongLayoutParam
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
        width: Int = HongLayoutParam.WRAP_CONTENT.value,
        height: Int = HongLayoutParam.WRAP_CONTENT.value,
        margin: HongSpacingInfo = HongSpacingInfo(),
        padding: HongSpacingInfo = HongSpacingInfo(),
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
                width = width,
                height = height,
                selectWidth = selectWidth,
                selectHeight = selectHeight,
                useWidth = useWidth,
                useHeight = useHeight,
            )
        }

        if (useMargin) {
            PlaygroundManager.addMarginOptionPreview(
                activity = activity,
                margin = margin,
                callback = selectMargin
            )
        }

        if (usePadding) {
            PlaygroundManager.addPaddingOptionPreview(
                activity = activity,
                padding = padding,
                selectPadding = selectPadding,
            )
        }
    }
}