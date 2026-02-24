package com.codehong.lib.sample.pager

import com.codehong.lib.sample.SampleConst.testImageUrlList
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.playground.PlaygroundManager
import com.codehong.lib.sample.playground.preview.HorizontalOptionView
import com.codehong.library.util.extensions.toFigureFloat
import com.codehong.library.util.extensions.toFigureInt
import com.codehong.library.util.extensions.toFigureLong
import com.codehong.library.util.extensions.toFigureString
import com.codehong.library.widget.pager.HongHorizontalPagerBuilder
import com.codehong.library.widget.pager.HongHorizontalPagerOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.util.HongToastUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HongHorizontalPagerPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongHorizontalPagerOption> {

    companion object {
        val DEFAULT_PREVIEW_OPTION = HongHorizontalPagerBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .verticalPadding(
                topPadding = 20f,
                bottomPadding = 20f
            )
            .backgroundColor(HongColor.WHITE_100.hex)
            .pageInfoList(testImageUrlList)
            .pageSpacing(10)
            .infiniteScroll(
                on = false,
                isRollbackFirst = false
            )
            .pageVisibleWidth(
                prev = 20f,
                next = 20f
            )
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongHorizontalPagerOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.HORIZONTAL_PAGER

    private var debounceJob: Job? = null

    fun preview() {
        executePreview()

        /** common */
        commonPreviewOption(
            height = previewOption.height,
            margin = previewOption.margin,
            useWidth = false,
            usePadding = false,
            selectHeight = { selectHeight ->
                this.previewOption = HongHorizontalPagerBuilder()
                    .copy(previewOption)
                    .height(selectHeight)
                    .applyOption()
                executePreview()
            },
            selectMargin = { selectMargin ->
                this.previewOption = HongHorizontalPagerBuilder()
                    .copy(previewOption)
                    .margin(selectMargin)
                    .applyOption()
                executePreview()
            },
        )

        /** vertical padding */
        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.labelInputPreview(
                activity = activity,
                input = previewOption.padding.top.toFigureString(),
                label = "top padding",
                useOnlyNumber = true,
                useTopPadding = false
            ) { topPadding ->
                previewOption = HongHorizontalPagerBuilder()
                    .copy(previewOption)
                    .verticalPadding(
                        topPadding = topPadding.toFigureFloat(),
                        bottomPadding = previewOption.padding.bottom
                    )
                    .applyOption()
                executePreview()
            },
            rightOptionView = PlaygroundManager.labelInputPreview(
                activity = activity,
                input = previewOption.padding.bottom.toFigureString(),
                label = "bottom padding",
                useOnlyNumber = true,
                useTopPadding = false
            ) { bottomPadding ->
                previewOption = HongHorizontalPagerBuilder()
                    .copy(previewOption)
                    .verticalPadding(
                        topPadding = previewOption.padding.top,
                        bottomPadding = bottomPadding.toFigureFloat()
                    )
                    .applyOption()
                executePreview()
            }
        ).also {
            activity.addOptionView(it)
        }

        /** background color */
        PlaygroundManager.addColorOptionPreview(
            activity = activity,
            label = "background",
            colorHex = previewOption.backgroundColorHex,
            useTopPadding = true,
        ) { selectHongColor ->
            this.previewOption = HongHorizontalPagerBuilder()
                .copy(previewOption)
                .backgroundColor(selectHongColor)
                .applyOption()
            executePreview()
        }

        /** page spacing */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            input = previewOption.pageSpacing.toFigureString(),
            label = "page spacing",
            useOnlyNumber = true,
            useTopPadding = true
        ) { pageSpacing ->
            previewOption = HongHorizontalPagerBuilder()
                .copy(previewOption)
                .pageSpacing(pageSpacing.toFigureInt())
                .applyOption()
            executePreview()
        }


        /** pageVisibleWidth */
        HorizontalOptionView(activity).set(
            leftOptionView = PlaygroundManager.labelInputPreview(
                activity = activity,
                input = previewOption.prevPageVisibleWidth.toFigureString(),
                label = "이전 페이지 노출 width",
                description = "이전 페이지 노출 width를 설정해요.",
                useOnlyNumber = true,
                useTopPadding = false
            ) { prevPageVisibleWidth ->
                previewOption = HongHorizontalPagerBuilder()
                    .copy(previewOption)
                    .pageVisibleWidth(
                        prev = prevPageVisibleWidth.toFigureFloat(),
                        next = previewOption.nextPageVisibleWidth
                    )
                    .applyOption()
                executePreview()
            },
            rightOptionView = PlaygroundManager.labelInputPreview(
                activity = activity,
                input = previewOption.nextPageVisibleWidth.toFigureString(),
                label = "다음 페이지 노출 width",
                description = "다음 페이지 노출 width를 설정해요.",
                useOnlyNumber = true,
                useTopPadding = false
            ) { nextPageVisibleWidth ->
                previewOption = HongHorizontalPagerBuilder()
                    .copy(previewOption)
                    .pageVisibleWidth(
                        prev = previewOption.prevPageVisibleWidth,
                        next = nextPageVisibleWidth.toFigureFloat()
                    )
                    .applyOption()
                executePreview()
            },
        ).also {
            activity.addOptionView(it)
        }

        /** autoScrollMillSecond */
        PlaygroundManager.addLabelInputOptionPreview(
            activity = activity,
            input = previewOption.autoScrollMillSecond.toFigureString(),
            label = "자동 스크롤 시간 설정",
            description = "자동 스크롤 시간 설정을 해요.",
            useOnlyNumber = true,
            useTopPadding = true
        ) { autoScrollMillSecond ->
            debounceJob?.cancel()
            if (autoScrollMillSecond.isEmpty()) {
                previewOption = HongHorizontalPagerBuilder()
                    .copy(previewOption)
                    .autoScrollMillSecond(autoScrollMillSecond.toFigureLong())
                    .applyOption()
                executePreview()
            } else {
                debounceJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    previewOption = HongHorizontalPagerBuilder()
                        .copy(previewOption)
                        .autoScrollMillSecond(autoScrollMillSecond.toFigureLong())
                        .applyOption()
                    executePreview()
                }
            }
        }

        /** infiniteScroll */
        PlaygroundManager.addLabelSwitchOptionPreview(
            activity = activity,
            label = "무한 스크롤 설정",
            switchState = previewOption.infiniteScroll.first,
        ) { isEnable ->
            previewOption = HongHorizontalPagerBuilder()
                .copy(previewOption)
                .infiniteScroll(
                    on = isEnable,
                    isRollbackFirst = previewOption.infiniteScroll.second
                )
                .applyOption()
            executePreview()
        }

        PlaygroundManager.addLabelSwitchOptionPreview(
            activity = activity,
            label = "페이지 롤백 여부",
            switchState = previewOption.infiniteScroll.second
        ) { isEnable ->
            if (isEnable && previewOption.autoScrollMillSecond == 0L) {
                HongToastUtil.showToast(activity, "페이지 롤백 활성화를 위해서 자동 스크롤 설정을 해주세요.")
            }
            previewOption = HongHorizontalPagerBuilder()
                .copy(previewOption)
                .infiniteScroll(
                    on = previewOption.infiniteScroll.first,
                    isRollbackFirst = isEnable
                )
                .applyOption()
        }
    }
}