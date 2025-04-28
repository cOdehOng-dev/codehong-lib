package com.codehong.lib.sample.playground

import android.util.Log
import androidx.compose.ui.text.style.TextAlign
import com.codehong.lib.sample.playground.preview.InputOptionView
import com.codehong.lib.sample.playground.preview.MixPickerInputOptionView
import com.codehong.lib.sample.playground.preview.PickerOptionView
import com.codehong.lib.sample.playground.preview.QuarterInputOptionView
import com.codehong.lib.sample.playground.preview.ToggleOptionView
import com.codehong.library.widget.HongSpacingInfo
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.HongTextOverflow
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.toHongLayoutParamValue
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextOption
import com.codehong.library.widget.text.HongTextView

object PlaygroundManager {

    private const val FIXED_SIZE = "FIXED_SIZE"

    /**
     * TEXT
     */
    fun previewText(
        activity: PlaygroundActivity
    ) {
        val builder =
            HongTextOption.Builder().text("Text").width(150).typography(HongTypo.BODY_16_B)
                .color(HongColor.BLACK_100).lineBreak(HongTextLineBreak.DEFAULT)

        val defTextOption = builder.build()

        activity.applyPreview(HongTextView(activity).set(option = defTextOption))

        /** 텍스트 내용 */
        val textOptionView = InputOptionView(activity).apply {
            init(
                title = "text",
                initial = defTextOption.text
            )
            observe { text ->
                Log.e("TAG", "옵션 text = $text")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.text(text).build()
                    )
                )
            }
        }
        activity.addOptionView(textOptionView)

        commonOptionView(
            activity = activity,
            type = HongWidgetType.TEXT,
            defOption = defTextOption,
            inputBuilder = builder
        )

        /** 텍스트 컬러 */
        val hexColorOptionView = InputOptionView(activity).apply {
            init(
                title = "hexColor",
                initial = defTextOption.colorHex
            )
            observe { hexColor ->
                var resultHexColor = hexColor
                if (!hexColor.isNullOrEmpty() && !hexColor.startsWith("#")) {
                    resultHexColor = "#$hexColor"
                }
                Log.e("TAG", "옵션 hexColor = $resultHexColor")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.color(resultHexColor).build()
                    )
                )
            }
        }
        activity.addOptionView(hexColorOptionView)

        val hongColorOptionView = PickerOptionView(activity).apply {
            val initialSemanticColor = hongColorList.firstOrNull {
                it.hex == defTextOption.colorHex
            }
            init(
                title = "color",
                initial = initialSemanticColor?.colorName
            )
            observe(
                title = "color",
                pickerList = hongColorNameList,
                selectPosition = hongColorList.indexOf(initialSemanticColor ?: 0),
                isDirectCallback = true
            ) { selectColor, index ->
                val hongColor =
                    hongColorList.firstOrNull { it.colorName == selectColor } ?: HongColor.BLACK_100

                Log.e("TAG", "옵션 hongColor = $hongColor, index = $index")
                hexColorOptionView.setOption(hongColor.hex)
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.color(hongColor.hex).build()
                    )
                )
            }
        }
        activity.addOptionView(hongColorOptionView)

        /** 텍스트 타이포그라피 */
        val typographyOptionView = PickerOptionView(activity).apply {
            val initialTypography = typographyList.firstOrNull {
                it == defTextOption.typography
            }
            init(
                title = "typography",
                initial = initialTypography?.styleName
            )
            observe(
                title = "typography",
                pickerList = typographyNameList,
                selectPosition = typographyList.indexOf(initialTypography ?: 0),
                isDirectCallback = true
            ) { selectTypography, index ->
                val typography = typographyList.firstOrNull { it.styleName == selectTypography }
                    ?: HongTypo.BODY_16_B

                Log.e("TAG", "옵션 typography = $typography, index = $index")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.typography(typography).build()
                    )
                )
            }
        }
        activity.addOptionView(typographyOptionView)

        /**
         * 텍스트 정렬
         * 좌측 / 우측 / 가운데
         * width가 WRAP_CONTENT인 경우에는 의도한 대로 적용되지 않으므로 주의!
         */
        val textAlignOptionView = PickerOptionView(activity).apply {
            val initialTextAlign = when (defTextOption.align.value) {
                TextAlign.Left -> "left"
                TextAlign.Right -> "right"
                else -> "center"
            }
            init(
                title = "textAlign",
                desp = "width가 WRAP_CONTENT인 경우에는 의도한 대로 적용되지 않으므로 주의!",
                initial = initialTextAlign
            )
            observe(
                title = "textAlign",
                pickerList = textAlignList,
                selectPosition = textAlignList.indexOf(initialTextAlign),
                isDirectCallback = true
            ) { selectTextAlign, index ->
                val textAlign = when (selectTextAlign) {
                    "left" -> HongTextAlign.LEFT
                    "right" -> HongTextAlign.RIGHT
                    else -> HongTextAlign.CENTER
                }
                Log.e("TAG", "옵션 textAlign = $selectTextAlign, index = $index")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.textAlign(textAlign).build()
                    )
                )
            }
        }
        activity.addOptionView(textAlignOptionView)

        /** 최대 라인 수 */
        val maxLineOptionView = InputOptionView(activity).apply {
            val initialMaxLine = defTextOption.maxLines
            init(
                title = "maxLine",
                initial = initialMaxLine.toString(),
                isNumberType = true
            )
            observe { maxLines ->
                val resultMaxLines = if (maxLines.isNullOrEmpty()) {
                    Int.MAX_VALUE
                } else {
                    maxLines.toInt()
                }
                Log.e("TAG", "옵션 maxLines = $resultMaxLines")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.maxLines(resultMaxLines).build()
                    )
                )
            }
        }
        activity.addOptionView(maxLineOptionView)

        /**
         * 화면에 표현할 수 있는 최대 글자수를 넘어간 경우 처리 방법
         * 자름 (Clip) / ... 처리 (Ellipsis)
         */
        val overflowList = listOf("clip", "ellipsis", "visible")
        val overflowOptionView = PickerOptionView(activity).apply {
            val initialOverflow = when (defTextOption.overflow) {
                HongTextOverflow.CLIP -> "clip"
                HongTextOverflow.ELLIPSIS -> "ellipsis"
                HongTextOverflow.VISIBLE -> "visible"
                else -> "ellipsis"
            }
            init(
                title = "overflow",
                initial = initialOverflow
            )
            observe(
                title = "overflow",
                pickerList = overflowList,
                selectPosition = overflowList.indexOf(initialOverflow),
                isDirectCallback = true
            ) { selectOverflow, index ->

                val resultOverflow = when (selectOverflow) {
                    "clip" -> HongTextOverflow.CLIP
                    "ellipsis" -> HongTextOverflow.ELLIPSIS
                    "visible" -> HongTextOverflow.VISIBLE
                    else -> HongTextOverflow.ELLIPSIS
                }

                Log.e("TAG", "옵션 selectOverflow = $resultOverflow, index = $index")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.overflow(resultOverflow).build()
                    )
                )
            }
        }
        activity.addOptionView(overflowOptionView)

        /**
         * 줄바꿈 타입
         */
        val lineBreakOptionView = PickerOptionView(activity).apply {
            val lineBreakList = HongTextLineBreak.values().map { it.alias }.toList()
            val initialLineBreak = when (defTextOption.lineBreak) {
                HongTextLineBreak.DEFAULT -> "default"
                HongTextLineBreak.SYLLABLE -> "syllable"
                HongTextLineBreak.SPACE -> "space"
                else -> "default"
            }
            init(
                title = "lineBreak",
                initial = initialLineBreak,
                desp = "시스템에 따름 / 음절 단위로 줄바꿈 / 단어 단위로 줄바꿈"
            )
            observe(
                title = "lineBreak",
                pickerList = lineBreakList,
                selectPosition = lineBreakList.indexOf(initialLineBreak),
                isDirectCallback = true
            ) { selectLineBreak, index ->
                val lineBreak = when (selectLineBreak) {
                    HongTextLineBreak.DEFAULT.alias -> HongTextLineBreak.DEFAULT
                    HongTextLineBreak.SYLLABLE.alias -> HongTextLineBreak.SYLLABLE
                    HongTextLineBreak.SPACE.alias -> HongTextLineBreak.SPACE
                    else -> HongTextLineBreak.DEFAULT
                }
                Log.e("TAG", "옵션 lineBreak = $selectLineBreak, index = $index")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.lineBreak(lineBreak).build()
                    )
                )
            }
        }
        activity.addOptionView(lineBreakOptionView)

        /** 취소선 여부 */
        val isEnableCancelLineOptionView = ToggleOptionView(activity).apply {
            init(
                title = "isEnableCancelLine",
                initial = defTextOption.isEnableCancelLine
            )
            observe { isEnable ->
                Log.e("TAG", "옵션 isEnableCancelLine = $isEnable")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.isEnableCancelLine(isEnable).build()
                    )
                )
            }
        }
        activity.addOptionView(isEnableCancelLineOptionView)

        val isEnableUnderLineOptionView = ToggleOptionView(activity).apply {
            init(
                title = "isEnableUnderLine",
                initial = defTextOption.isEnableUnderLine
            )
            observe { isEnable ->
                Log.e("TAG", "옵션 isEnableUnderLine = $isEnable")
                activity.applyPreview(
                    HongTextView(activity).set(
                        option = builder.isEnableUnderLine(isEnable).build()
                    )
                )
            }
        }
        activity.addOptionView(isEnableUnderLineOptionView)
    }

    private val hongColorList: List<HongColor> get() = HongColor.values().toList()

    private val hongColorNameList: List<String> get() = hongColorList.map { it.colorName }

    private val typographyList get() = HongTypo.values().toList()
    private val typographyNameList get() = typographyList.map { it.styleName }

    private val textAlignList get() = listOf("left", "right", "center")

    private fun commonOptionView(
        activity: PlaygroundActivity,
        type: HongWidgetType,
        defOption: HongWidgetCommonOption,
        inputBuilder: Any
    ) {
        val builder = when (type) {
            HongWidgetType.TEXT -> inputBuilder as HongTextOption.Builder
            else -> inputBuilder as? HongTextOption.Builder
        } ?: return

        val widthHeightSizeList = listOf(
            HongLayoutParam.MATCH_PARENT.paramName,
            HongLayoutParam.WRAP_CONTENT.paramName,
            FIXED_SIZE
        )

        val defWidth = defOption.width

        val initialWidth = when (defWidth) {
            HongLayoutParam.MATCH_PARENT.value -> HongLayoutParam.MATCH_PARENT.paramName
            HongLayoutParam.WRAP_CONTENT.value -> HongLayoutParam.WRAP_CONTENT.paramName
            else -> ""
        }

        val widthLayoutParamOptionView = MixPickerInputOptionView(activity).apply {
            if (initialWidth.isEmpty()) {
                showInput()
                setOption(defWidth.toString())
            } else {
                hideInput()
            }
            init(
                title = "width",
                initial = initialWidth,
                isNumberType = true
            )
            observe(
                title = "width",
                pickerList = widthHeightSizeList,
                selectPosition = widthHeightSizeList.indexOf(initialWidth.ifEmpty { "FIXED_SIZE" }),
                pickerCallback = { selectSize, index ->
                    Log.e("TAG", "옵션 selectWidth = $selectSize, index = $index")
                    if (selectSize == HongLayoutParam.MATCH_PARENT.paramName
                        || selectSize == HongLayoutParam.WRAP_CONTENT.paramName
                    ) {
                        hideInput()
                        activity.applyPreview(
                            HongTextView(activity).set(
                                option = builder.width(selectSize.toHongLayoutParamValue()).build()
                            )
                        )
                    } else {
                        showInput()
                        setOption("200")
                    }
                },
                inputCallback = { inputSize ->
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.width(
                                if (inputSize.isNullOrEmpty()) {
                                    defWidth
                                } else {
                                    inputSize.toInt()
                                }
                            ).build()
                        )
                    )
                }
            )
        }
        activity.addOptionView(widthLayoutParamOptionView)

        val defHeight = defOption.height

        val initialHeight = when (defHeight) {
            HongLayoutParam.MATCH_PARENT.value -> HongLayoutParam.MATCH_PARENT.paramName
            HongLayoutParam.WRAP_CONTENT.value -> HongLayoutParam.WRAP_CONTENT.paramName
            else -> ""
        }

        val heightLayoutParamOptionView = MixPickerInputOptionView(activity).apply {
            if (initialHeight.isEmpty()) {
                setOption(defHeight.toString())
                showInput()
            } else {
                hideInput()
            }
            init(
                title = "height",
                initial = initialHeight,
                isNumberType = true
            )
            observe(
                title = "height",
                pickerList = widthHeightSizeList,
                selectPosition = widthHeightSizeList.indexOf(
                    initialHeight.ifEmpty {
                        "FIXED_SIZE"
                    }
                ),
                pickerCallback = { selectSize, index ->
                    Log.e("TAG", "옵션 selectHeight = $selectSize, index = $index")
                    if (selectSize == HongLayoutParam.MATCH_PARENT.paramName
                        || selectSize == HongLayoutParam.WRAP_CONTENT.paramName
                    ) {
                        hideInput()
                        activity.applyPreview(
                            HongTextView(activity).set(
                                option = builder.height(selectSize.toHongLayoutParamValue()).build()
                            )
                        )
                    } else {
                        showInput()
                        setOption("50")
                    }
                },
                inputCallback = { inputSize ->
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.height(
                                if (inputSize.isNullOrEmpty()) {
                                    defHeight
                                } else {
                                    inputSize.toInt()
                                }
                            ).build()
                        )
                    )
                }
            )
        }
        activity.addOptionView(heightLayoutParamOptionView)

        val marginOptionView = QuarterInputOptionView(activity).apply {
            val defTopMarginFloat = defOption.margin.top
            val initialTopMargin = if (defTopMarginFloat > 0f) {
                defTopMarginFloat.toString()
            } else {
                ""
            }
            val defBottomMarginFloat = defOption.margin.bottom
            val initialBottomMargin = if (defBottomMarginFloat > 0f) {
                defBottomMarginFloat.toString()
            } else {
                ""
            }
            val defLeftMarginFloat = defOption.margin.left
            val initialLeftMargin = if (defLeftMarginFloat > 0f) {
                defLeftMarginFloat.toString()
            } else {
                ""
            }
            val defRightMarginFloat = defOption.margin.right
            val initialRightMargin = if (defRightMarginFloat > 0f) {
                defRightMarginFloat.toString()
            } else {
                ""
            }

            init(
                mainTitle = "margin",
                inputInit1 = Triple("top", initialTopMargin, true),
                inputInit2 = Triple("bottom", initialBottomMargin, true),
                inputInit3 = Triple("left", initialLeftMargin, true),
                inputInit4 = Triple("right", initialRightMargin, true)
            )

            observe(
                inputCallback1 = { topMargin ->
                    val resultTopMargin = if (topMargin.isNullOrEmpty()) {
                        0f
                    } else {
                        topMargin.toFloat()
                    }
                    Log.e("TAG", "옵션 topMargin = $resultTopMargin")
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.margin(
                                HongSpacingInfo(
                                    left = defOption.margin.left,
                                    top = resultTopMargin,
                                    right = defOption.margin.right,
                                    bottom = defOption.margin.bottom
                                )
                            ).build()
                        )
                    )
                },
                inputCallback2 = { bottomMargin ->
                    val resultBottomMargin = if (bottomMargin.isNullOrEmpty()) {
                        0f
                    } else {
                        bottomMargin.toFloat()
                    }
                    Log.e("TAG", "옵션 bottomMargin = $resultBottomMargin")
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.margin(
                                HongSpacingInfo(
                                    left = defOption.margin.left,
                                    top = defOption.margin.top,
                                    right = defOption.margin.right,
                                    bottom = resultBottomMargin
                                )
                            ).build()
                        )
                    )
                },
                inputCallback3 = { leftMargin ->
                    val resultLeftMargin = if (leftMargin.isNullOrEmpty()) {
                        0f
                    } else {
                        leftMargin.toFloat()
                    }
                    Log.e("TAG", "옵션 leftMargin = $resultLeftMargin")
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.margin(
                                HongSpacingInfo(
                                    left = resultLeftMargin,
                                    top = defOption.margin.top,
                                    right = defOption.margin.right,
                                    bottom = defOption.margin.bottom
                                )
                            ).build()
                        )
                    )
                },
                inputCallback4 = { rightMargin ->
                    val resultRightMargin = if (rightMargin.isNullOrEmpty()) {
                        0f
                    } else {
                        rightMargin.toFloat()
                    }
                    Log.e("TAG", "옵션 rightMargin = $resultRightMargin")
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.margin(
                                HongSpacingInfo(
                                    left = defOption.margin.left,
                                    top = defOption.margin.top,
                                    right = resultRightMargin,
                                    bottom = defOption.margin.bottom
                                )
                            ).build()
                        )
                    )
                }
            )
        }
        activity.addOptionView(marginOptionView)

        val paddingOptionView = QuarterInputOptionView(activity).apply {
            val defTopPaddingFloat = defOption.padding.top
            val initialTopPadding = if (defTopPaddingFloat > 0f) {
                defTopPaddingFloat.toString()
            } else {
                ""
            }
            val defBottomPaddingFloat = defOption.padding.bottom
            val initialBottomPadding = if (defBottomPaddingFloat > 0f) {
                defBottomPaddingFloat.toString()
            } else {
                ""
            }
            val defLeftPaddingFloat = defOption.padding.left
            val initialLeftPadding = if (defLeftPaddingFloat > 0f) {
                defLeftPaddingFloat.toString()
            } else {
                ""
            }
            val defRightPaddingFloat = defOption.padding.right
            val initialRightPadding = if (defRightPaddingFloat > 0f) {
                defRightPaddingFloat.toString()
            } else {
                ""
            }

            init(
                mainTitle = "padding",
                inputInit1 = Triple("top", initialTopPadding, true),
                inputInit2 = Triple("bottom", initialBottomPadding, true),
                inputInit3 = Triple("left", initialLeftPadding, true),
                inputInit4 = Triple("right", initialRightPadding, true)
            )

            observe(
                inputCallback1 = { topPadding ->
                    val resultTopPadding = if (topPadding.isNullOrEmpty()) {
                        0f
                    } else {
                        topPadding.toFloat()
                    }
                    Log.e("TAG", "옵션 topPadding = $resultTopPadding")
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.padding(
                                HongSpacingInfo(
                                    left = defOption.padding.left,
                                    top = resultTopPadding,
                                    right = defOption.padding.right,
                                    bottom = defOption.padding.bottom
                                )
                            ).build()
                        )
                    )
                },
                inputCallback2 = { bottomPadding ->
                    val resultBottomPadding = if (bottomPadding.isNullOrEmpty()) {
                        0f
                    } else {
                        bottomPadding.toFloat()
                    }
                    Log.e("TAG", "옵션 bottomPadding = $resultBottomPadding")
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.padding(
                                HongSpacingInfo(
                                    left = defOption.padding.left,
                                    top = defOption.padding.top,
                                    right = defOption.padding.right,
                                    bottom = resultBottomPadding
                                )
                            ).build()
                        )
                    )
                },
                inputCallback3 = { leftPadding ->
                    val resultLeftPadding = if (leftPadding.isNullOrEmpty()) {
                        0f
                    } else {
                        leftPadding.toFloat()
                    }
                    Log.e("TAG", "옵션 leftPadding = $resultLeftPadding")
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.padding(
                                HongSpacingInfo(
                                    left = resultLeftPadding,
                                    top = defOption.padding.top,
                                    right = defOption.padding.right,
                                    bottom = defOption.padding.bottom
                                )
                            ).build()
                        )
                    )
                },
                inputCallback4 = { rightPadding ->
                    val resultRightPadding = if (rightPadding.isNullOrEmpty()) {
                        0f
                    } else {
                        rightPadding.toFloat()
                    }
                    Log.e("TAG", "옵션 rightPadding = $resultRightPadding")
                    activity.applyPreview(
                        HongTextView(activity).set(
                            option = builder.padding(
                                HongSpacingInfo(
                                    left = defOption.padding.left,
                                    top = defOption.padding.top,
                                    right = resultRightPadding,
                                    bottom = defOption.padding.bottom
                                )
                            ).build()
                        )
                    )
                }
            )
        }
        activity.addOptionView(paddingOptionView)
    }
}
