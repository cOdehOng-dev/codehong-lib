package com.codehong.lib.sample.playground

import android.content.Context
import android.util.Log
import android.view.View
import com.codehong.lib.sample.R
import com.codehong.lib.sample.playground.preview.HorizontalOptionView
import com.codehong.lib.sample.playground.preview.QuarterInputOptionView
import com.codehong.library.network.debug.TimberUtil
import com.codehong.library.widget.Consts
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.extensions.toFigureFloat
import com.codehong.library.widget.extensions.toFigureInt
import com.codehong.library.widget.extensions.toFigureString
import com.codehong.library.widget.label.def.HongLabelBuilder
import com.codehong.library.widget.label.def.HongLabelView
import com.codehong.library.widget.label.input.HongLabelInputBuilder
import com.codehong.library.widget.label.input.HongLabelInputView
import com.codehong.library.widget.label.select.HongLabelSelectInputBuilder
import com.codehong.library.widget.label.select.HongLabelSelectInputView
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchBuilder
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchView
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongLayoutParam.Companion.isHongLayoutParam
import com.codehong.library.widget.rule.HongLayoutParam.Companion.toHongLayoutParamValue
import com.codehong.library.widget.rule.HongLayoutParam.Companion.toHongLayoutValueToParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder

object PlaygroundManager {

    val widthHeightSizeList = listOf(
        HongLayoutParam.MATCH_PARENT.paramName,
        HongLayoutParam.WRAP_CONTENT.paramName,
        Consts.DIRECT_INPUT
    )

    val hongColorList: List<HongColor> = HongColor.entries

    val typographyList = HongTypo.entries
    val typoNameList = typographyList.map { it.styleName }

    /**
     * 옵션 제목 뷰
     */
    fun addOptionTitleView(
        activity: PlaygroundActivity,
        label: String,
        description: String = "",
        labelTypo: HongTypo? = null,
        descriptionTypo: HongTypo? = null,
        useTopPadding: Boolean = true
    ) {
        HongLabelView(activity)
            .set(
                HongLabelBuilder()
                    .padding(
                        HongSpacingInfo(
                            top = (if (useTopPadding) Consts.PLAYGROUND_TOP_PADDING else 0).toFloat(),
                        )
                    )
                    .label(label)
                    .labelTypo(labelTypo ?: HongTypo.BODY_17_B)
                    .labelColor(HongColor.MAIN_ORANGE_100)
                    .description(description)
                    .descriptionTypo(descriptionTypo ?: HongTypo.CONTENTS_12)
                    .descriptionColor(HongColor.BLACK_100)
                    .applyOption()
            ).apply {
                activity.addOptionView(this)
            }
    }

    /**
     * width/height
     */
    fun addSizeOptionPreview(
        activity: PlaygroundActivity,
        width: Int,
        height: Int,
        label: String = "",
        description: String = "",
        useTopPadding: Boolean = true,
        useWidth: Boolean = true,
        useHeight: Boolean = true,
        selectWidth: (Int) -> Unit,
        selectHeight: (Int) -> Unit
    ) {
        HorizontalOptionView(activity).set(
            label = label,
            description = description,
            useTopPadding = useTopPadding,
            leftOptionView = useWidth.takeIf { it }?.let {
                HongLabelSelectInputView(activity).apply {
                    val initialWidth =
                        width.toHongLayoutValueToParam().ifEmpty { Consts.DIRECT_INPUT }
                    setSelectInputView(
                        HongLabelSelectInputBuilder()
                            .label("${label}width")
                            .description(description)
                            .buttonText(initialWidth)
                            .inputText(
                                if (initialWidth == Consts.DIRECT_INPUT) {
                                    width.toString()
                                } else {
                                    initialWidth
                                }
                            )
                            .selectList(widthHeightSizeList)
                            .selectPosition(widthHeightSizeList.indexOf(initialWidth.ifEmpty { Consts.DIRECT_INPUT }))
                            .useDirectCallback(true)
                            .useOnlyNumber(true)
                            .showInput(initialWidth == Consts.DIRECT_INPUT)
                            .inputCallback { inputSize ->
                                if (inputSize.isHongLayoutParam()) {
                                    return@inputCallback
                                }
                                val selectWidthSize = if (inputSize.isNullOrEmpty()) {
                                    width
                                } else {
                                    inputSize.toInt()
                                }
                                selectWidth.invoke(selectWidthSize)
                            }
                            .pickerCallback { selectSize, index ->
                                if (selectSize.isHongLayoutParam()) {
                                    hideInput()
                                    selectWidth.invoke(selectSize.toHongLayoutParamValue())
                                } else {
                                    showInput()
                                    setInputText("200")
                                    selectWidth.invoke(200)
                                }
                            }
                            .applyOption()
                    )
                }
            },
            rightOptionView = useHeight.takeIf { it }?.let {
                HongLabelSelectInputView(activity).apply {
                    val initialHeight =
                        height.toHongLayoutValueToParam().ifEmpty { Consts.DIRECT_INPUT }
                    setSelectInputView(
                        HongLabelSelectInputBuilder()
                            .label("${label}height")
                            .description(description)
                            .buttonText(initialHeight)
                            .inputText(
                                if (initialHeight == Consts.DIRECT_INPUT) {
                                    height.toString()
                                } else {
                                    initialHeight
                                }
                            )
                            .selectList(widthHeightSizeList)
                            .selectPosition(widthHeightSizeList.indexOf(initialHeight.ifEmpty { Consts.DIRECT_INPUT }))
                            .useDirectCallback(true)
                            .useOnlyNumber(true)
                            .showInput(initialHeight == Consts.DIRECT_INPUT)
                            .inputCallback { inputSize ->
                                if (inputSize.isHongLayoutParam()) {
                                    return@inputCallback
                                }
                                val selectHeightSize = if (inputSize.isNullOrEmpty()) {
                                    height
                                } else {
                                    inputSize.toInt()
                                }
                                selectHeight.invoke(selectHeightSize)
                            }
                            .pickerCallback { selectSize, index ->
                                if (selectSize.isHongLayoutParam()) {
                                    hideInput()
                                    selectHeight.invoke(selectSize.toHongLayoutParamValue())
                                } else {
                                    showInput()
                                    setInputText("50")
                                    selectHeight.invoke(50)
                                }
                            }
                            .applyOption()
                    )
                }
            },
        ).also {
            activity.addOptionView(it)
        }
    }

    /**
     * margin
     */
    fun addMarginOptionPreview(
        activity: PlaygroundActivity,
        margin: HongSpacingInfo,
        label: String = "",
        description: String = "",
        useTopPadding: Boolean = true,
        callback: (HongSpacingInfo) -> Unit
    ) {
        var initMargin = margin
        QuarterInputOptionView(activity).apply {
            set(
                label = "${label}margin",
                description = description,
                useTopPadding = useTopPadding,
                inputInit1 = Triple("top", initMargin.top.toFigureString(), true),
                inputInit2 = Triple("bottom", initMargin.bottom.toFigureString(), true),
                inputInit3 = Triple("left", initMargin.left.toFigureString(), true),
                inputInit4 = Triple("right", initMargin.right.toFigureString(), true),
                inputCallback1 = { topMargin ->
                    callback.invoke(
                        HongSpacingInfo(
                            left = initMargin.left,
                            top = topMargin.toFigureFloat(),
                            right = initMargin.right,
                            bottom = initMargin.bottom
                        ).apply {
                            initMargin = this
                        }
                    )
                },
                inputCallback2 = { bottomMargin ->
                    callback.invoke(
                        HongSpacingInfo(
                            left = initMargin.left,
                            top = initMargin.top,
                            right = initMargin.right,
                            bottom = bottomMargin.toFigureFloat()
                        ).apply {
                            initMargin = this
                        }
                    )
                },
                inputCallback3 = { leftMargin ->
                    callback.invoke(
                        HongSpacingInfo(
                            left = leftMargin.toFigureFloat(),
                            top = initMargin.top,
                            right = initMargin.right,
                            bottom = initMargin.bottom
                        ).apply {
                            initMargin = this
                        }
                    )
                },
                inputCallback4 = { rightMargin ->
                    callback.invoke(
                        HongSpacingInfo(
                            left = initMargin.left,
                            top = initMargin.top,
                            right = rightMargin.toFigureFloat(),
                            bottom = initMargin.bottom
                        ).apply {
                            initMargin = this
                        }
                    )
                }
            )
        }.apply {
            activity.addOptionView(this)
        }
    }

    /**
     * padding
     */
    fun addPaddingOptionPreview(
        activity: PlaygroundActivity,
        padding: HongSpacingInfo,
        label: String = "",
        description: String = "",
        selectPadding: (HongSpacingInfo) -> Unit
    ) {
        var initPadding = padding
        QuarterInputOptionView(activity).apply {
            set(
                label = "${label}padding",
                description = description,
                inputInit1 = Triple("top", initPadding.top.toFigureString(), true),
                inputInit2 = Triple("bottom", initPadding.bottom.toFigureString(), true),
                inputInit3 = Triple("left", initPadding.left.toFigureString(), true),
                inputInit4 = Triple("right", initPadding.right.toFigureString(), true),
                inputCallback1 = { topPadding ->
                    selectPadding.invoke(
                        HongSpacingInfo(
                            left = initPadding.left,
                            top = topPadding.toFigureFloat(),
                            right = initPadding.right,
                            bottom = initPadding.bottom
                        ).apply {
                            initPadding = this
                        }
                    )
                },
                inputCallback2 = { bottomPadding ->
                    selectPadding.invoke(
                        HongSpacingInfo(
                            left = initPadding.left,
                            top = initPadding.top,
                            right = initPadding.right,
                            bottom = bottomPadding.toFigureFloat()
                        ).apply {
                            initPadding = this
                        }
                    )
                },
                inputCallback3 = { leftPadding ->
                    selectPadding.invoke(
                        HongSpacingInfo(
                            left = leftPadding.toFigureFloat(),
                            top = initPadding.top,
                            right = initPadding.right,
                            bottom = initPadding.bottom
                        ).apply {
                            initPadding = this
                        }
                    )
                },
                inputCallback4 = { rightPadding ->
                    selectPadding.invoke(
                        HongSpacingInfo(
                            left = initPadding.left,
                            top = initPadding.top,
                            right = rightPadding.toFigureFloat(),
                            bottom = initPadding.bottom
                        ).apply {
                            initPadding = this
                        }
                    )
                }
            )
        }.apply {
            activity.addOptionView(this)
        }
    }

    /**
     * radius
     */
    fun addViewRadiusOption(
        activity: PlaygroundActivity,
        radius: HongRadiusInfo,
        label: String = "",
        description: String = "",
        useTopPadding: Boolean = true,
        callback: (HongRadiusInfo) -> Unit
    ) {
        QuarterInputOptionView(activity).apply {
            var initRadius = radius
            set(
                label = "${label}radius",
                useTopPadding = useTopPadding,
                description = description,
                inputInit1 = Triple("topL", initRadius.topLeft.toFigureString(), true),
                inputInit2 = Triple("topR", initRadius.topRight.toFigureString(), true),
                inputInit3 = Triple("bottomL", initRadius.bottomLeft.toFigureString(), true),
                inputInit4 = Triple("bottomR", initRadius.bottomRight.toFigureString(), true),
                inputCallback1 = { topLeft ->
                    callback.invoke(
                        HongRadiusInfo(
                            topLeft = topLeft.toFigureInt(),
                            topRight = initRadius.topRight,
                            bottomLeft = initRadius.bottomLeft,
                            bottomRight = initRadius.bottomRight
                        ).apply {
                            initRadius = this
                        }
                    )
                },
                inputCallback2 = { topRight ->
                    callback.invoke(
                        HongRadiusInfo(
                            topLeft = initRadius.topLeft,
                            topRight = topRight.toFigureInt(),
                            bottomLeft = initRadius.bottomLeft,
                            bottomRight = initRadius.bottomRight
                        ).apply {
                            initRadius = this
                        }
                    )
                },
                inputCallback3 = { bottomLeft ->
                    callback.invoke(
                        HongRadiusInfo(
                            topLeft = initRadius.topLeft,
                            topRight = initRadius.topRight,
                            bottomLeft = bottomLeft.toFigureInt(),
                            bottomRight = initRadius.bottomRight
                        ).apply {
                            initRadius = this
                        }
                    )
                },
                inputCallback4 = { bottomRight ->
                    callback.invoke(
                        HongRadiusInfo(
                            topLeft = initRadius.topLeft,
                            topRight = initRadius.topRight,
                            bottomLeft = initRadius.bottomLeft,
                            bottomRight = bottomRight.toFigureInt()
                        ).apply {
                            initRadius = this
                        }
                    )
                }
            )
        }.apply {
            activity.addOptionView(this)
        }
    }

    /**
     * color
     */
    fun selectColorOptionView(
        activity: PlaygroundActivity,
        colorHex: String?,
        useTopPadding: Boolean,
        label: String = "",
        description: String = "",
        callback: (hex: String) -> Unit
    ): HongLabelSelectInputView {
        return HongLabelSelectInputView(activity).apply {
            val colorOptionList = hongColorList.map { it.colorName }.toMutableList()
            colorOptionList.add(0, Consts.DIRECT_INPUT)
            val initialColor = hongColorList.firstOrNull { it.hex == colorHex }

            setSelectInputView(
                HongLabelSelectInputBuilder()
                    .padding(
                        HongSpacingInfo(
                            top = if (useTopPadding) Consts.PLAYGROUND_TOP_PADDING_FLOAT else 0f,
                        )
                    )
                    .label("${label}color")
                    .description(description)
                    .placeholder("hexCode를 입력하세요. (ex: #ff000000)")
                    .buttonText(if (!initialColor?.colorName.isNullOrEmpty()) initialColor?.colorName else Consts.DIRECT_INPUT)
                    .inputText(initialColor?.hex ?: (colorHex ?: ""))
                    .selectList(colorOptionList)
                    .selectPosition(if (initialColor != null) colorOptionList.indexOf(initialColor.colorName) else 0)
                    .useDirectCallback(true)
                    .useOnlyNumber(false)
                    .showInput(initialColor == null)
                    .inputCallback { hexColor ->
                        var resultHexColor = hexColor
                        if (!hexColor.isNullOrEmpty() && !hexColor.startsWith("#")) {
                            resultHexColor = "#$hexColor"
                        }
                        Log.e("TAG", "옵션 hexColor = $resultHexColor")
                        callback.invoke(resultHexColor ?: HongColor.TRANSPARENT.hex)
                    }
                    .pickerCallback { selectColor, index ->
                        val hongColor =
                            hongColorList.firstOrNull { it.colorName == selectColor }
                                ?: HongColor.BLACK_100
                        Log.e("TAG", "옵션 selectColor = $selectColor")
                        if (selectColor == Consts.DIRECT_INPUT) {
                            showInput()
                            setInputText("")
                            callback.invoke("")
                        } else {
                            hideInput()
                            callback.invoke(hongColor.hex)
                        }
                    }
                    .applyOption()
            )
        }
    }

    fun addColorOptionPreview(
        activity: PlaygroundActivity,
        colorHex: String?,
        label: String = "",
        description: String = "",
        useTopPadding: Boolean = true,
        callback: (hex: String) -> Unit
    ) {
        selectColorOptionView(
            activity,
            colorHex = colorHex,
            label = label,
            description = description,
            useTopPadding = useTopPadding,
            callback = callback
        ).also {
            activity.addOptionView(it)
        }
    }

    /**
     * shadow
     */
    fun addShadowOptionPreview(
        activity: PlaygroundActivity,
        shadow: HongShadowInfo,
        title: String = "",
        useTopPadding: Boolean = true,
        selectShadow: (HongShadowInfo) -> Unit
    ) {
        var initShadow = shadow
        addColorOptionPreview(
            activity,
            label = "${title}shadow ",
            colorHex = initShadow.color,
            useTopPadding = useTopPadding,
        ) { selectColorHex ->
            selectShadow.invoke(
                HongShadowInfo(
                    color = selectColorHex,
                    blur = initShadow.blur,
                    offsetY = initShadow.offsetY,
                    offsetX = initShadow.offsetX,
                    spread = initShadow.spread
                ).apply {
                    initShadow = this
                }
            )
        }

        QuarterInputOptionView(activity).apply {
            set(
                label = "${title}shadow option",
                inputInit1 = Triple("blur", initShadow.blur.toFigureString(), true),
                inputInit2 = Triple("spread", initShadow.spread.toFigureString(), true),
                inputInit3 = Triple("offsetX", initShadow.offsetX.toFigureString(), true),
                inputInit4 = Triple("offsetY", initShadow.offsetY.toFigureString(), true),
                inputCallback1 = { blur ->
                    selectShadow.invoke(
                        HongShadowInfo(
                            color = initShadow.color,
                            blur = blur.toFigureFloat(),
                            offsetY = initShadow.offsetY,
                            offsetX = initShadow.offsetX,
                            spread = initShadow.spread
                        ).apply {
                            initShadow = this
                        }
                    )
                },
                inputCallback2 = { spread ->
                    selectShadow.invoke(
                        HongShadowInfo(
                            color = initShadow.color,
                            blur = initShadow.blur,
                            offsetY = initShadow.offsetY,
                            offsetX = initShadow.offsetX,
                            spread = spread.toFigureFloat()
                        ).apply {
                            initShadow = this
                        }
                    )
                },
                inputCallback3 = { offsetX ->
                    selectShadow.invoke(
                        HongShadowInfo(
                            color = initShadow.color,
                            blur = initShadow.blur,
                            offsetY = initShadow.offsetY,
                            offsetX = offsetX.toFigureFloat(),
                            spread = initShadow.spread
                        ).apply {
                            initShadow = this
                        }
                    )
                },
                inputCallback4 = { offsetY ->
                    selectShadow.invoke(
                        HongShadowInfo(
                            color = initShadow.color,
                            blur = initShadow.blur,
                            offsetY = offsetY.toFigureFloat(),
                            offsetX = initShadow.offsetX,
                            spread = initShadow.spread
                        ).apply {
                            initShadow = this
                        }
                    )
                }
            )
        }.apply {
            activity.addOptionView(this)
        }
    }

    /**
     * useShapeCircle
     */
    fun addUseShapeCircleOptionPreview(
        activity: PlaygroundActivity,
        useShapeCircle: Boolean,
        label: String = "",
        description: String = "",
        callback: (Boolean) -> Unit
    ) {
        addLabelSwitchOptionPreview(
            activity = activity,
            label = "${label}원형 설정",
            description = "${description}원형 모양으로 사용해요.",
            switchState = useShapeCircle,
            switchCallback = callback
        )
    }

    /**
     * border
     */
    fun addBorderOptionPreview(
        activity: PlaygroundActivity,
        border: HongBorderInfo,
        label: String = "",
        despWidth: String = "",
        despColor: String = "",
        useTopPadding: Boolean = true,
        callback: (HongBorderInfo) -> Unit
    ) {
        HorizontalOptionView(activity).set(
            leftOptionView = labelInputPreview(
                activity = activity,
                input = border.width.toFigureString(),
                label = "${label}border width",
                description = despWidth,
                useTopPadding = false,
                useOnlyNumber = true,
            ) { borderWidth ->
                val currentBorder = (activity.previewOption as HongWidgetCommonOption).border
                callback(
                    HongBorderInfo(
                        color = currentBorder.color,
                        width = borderWidth.toFigureInt()
                    )
                )
            },
            rightOptionView = selectColorOptionView(
                activity = activity,
                colorHex = border.color,
                label = "${label}border ",
                description = despColor,
                useTopPadding = false
            ) { borderColor ->
                val currentBorder = (activity.previewOption as HongWidgetCommonOption).border
                callback(
                    HongBorderInfo(
                        color = borderColor,
                        width = if (currentBorder.width > 0) currentBorder.width else 1
                    )
                )
            },
            useTopPadding = useTopPadding
        ).also {
            activity.addOptionView(it)
        }
    }

    fun addLabelInputOptionPreview(
        activity: PlaygroundActivity,
        input: String?,
        label: String,
        description: String = "",
        useTopPadding: Boolean = true,
        useOnlyNumber: Boolean = false,
        callback: (String) -> Unit
    ) {
        labelInputPreview(
            activity = activity,
            input = input,
            label = label,
            description = description,
            useTopPadding = useTopPadding,
            useOnlyNumber = useOnlyNumber,
            callback = callback
        ).also {
            activity.addOptionView(it)
        }
    }

    fun labelInputPreview(
        activity: PlaygroundActivity,
        input: String?,
        label: String,
        description: String = "",
        useTopPadding: Boolean = true,
        useOnlyNumber: Boolean = false,
        callback: (String) -> Unit
    ): HongLabelInputView {
        return HongLabelInputView(activity).set(
            HongLabelInputBuilder()
                .width(HongLayoutParam.MATCH_PARENT.value)
                .padding(
                    HongSpacingInfo(
                        top = (if (useTopPadding) Consts.PLAYGROUND_TOP_PADDING else 0).toFloat(),
                    )
                )
                .label(label)
                .input(input)
                .description(description)
                .keyboardOption(
                    Pair(
                        if (useOnlyNumber) {
                            HongKeyboardType.NUMBER
                        } else {
                            HongKeyboardType.TEXT
                        }, HongKeyboardActionType.DONE
                    )
                )
                .onTextChanged(callback)
                .applyOption()
        )
    }

    fun addLabelSwitchOptionPreview(
        activity: PlaygroundActivity,
        label: String,
        description: String  ="",
        switchState: Boolean,
        useTopPadding: Boolean = true,
        switchCallback: (Boolean) -> Unit
    ) {
        labelSwitchView(
            activity = activity,
            label = label,
            description = description,
            switchState = switchState,
            useTopPadding = useTopPadding,
            switchCallback = switchCallback
        ).also {
            activity.addOptionView(it)
        }
    }
    fun labelSwitchView(
        activity: PlaygroundActivity,
        label: String,
        description: String  ="",
        switchState: Boolean,
        useTopPadding: Boolean = true,
        switchCallback: (Boolean) -> Unit
    ): HongLabelSwitchView {
        return HongLabelSwitchView(activity).set(
            HongLabelSwitchBuilder()
                .padding(
                    HongSpacingInfo(
                        top = if (useTopPadding) Consts.PLAYGROUND_TOP_PADDING_FLOAT else 0f
                    )
                )
                .label(label)
                .description(description)
                .switchOption(
                    HongSwitchBuilder()
                        .width(55)
                        .height(30)
                        .onColor(HongColor.MAIN_ORANGE_100)
                        .offColor(HongColor.GRAY_20)
                        .cursorSize(25)
                        .cursorHorizontalMargin(3)
                        .cursorColor(HongColor.WHITE_100)
                        .initialState(switchState)
                        .switchClick { _, isEnable ->
                            switchCallback(isEnable)
                        }
                        .applyOption()
                )
                .applyOption()
        )
    }


    /**
     * drawable 리소스 이름 목록 가져오기
     */
    fun addLocalResourceOptionPreview(
        activity: PlaygroundActivity,
        label: String,
        drawableResId: Int?,
        selectResId: (Int?) -> Unit
    ) {
        val drawableList = getLibraryDrawableResNameList()
        val initial = if (drawableResId != null) {
            drawableList.firstOrNull {
                it == activity.resources.getResourceEntryName(drawableResId)
            }
        } else {
            drawableList.first()
        }
        addViewSelectOption(
            activity = activity,
            initialText = initial,
            selectList = drawableList,
            selectedPosition = drawableList.indexOf(initial ?: 0),
            label = label,
            useDirectCallback = true,
        ) { selectDrawable, index ->
            val drawable =
                drawableList.firstOrNull { it == selectDrawable }
            TimberUtil.i("test here 옵션 drawable = $drawable, index = $index")
            selectResId.invoke(
                if (drawable.isNullOrEmpty()) null
                else getDrawableResIdByNameFromLibrary(drawable)
            )
        }
    }

    fun addViewSelectOption(
        activity: PlaygroundActivity,
        initialText: String?,
        label: String,
        useDirectCallback: Boolean,
        selectList: List<String>,
        selectedPosition: Int,
        description: String = "",
        useTopPadding: Boolean = true,
        selectOptionCallback: (String, Int) -> Unit
    ) {
        selectOptionView(
            activity = activity,
            initialText = initialText,
            label = label,
            selectList = selectList,
            selectedPosition = selectedPosition,
            description = description,
            useDirectCallback = useDirectCallback,
            useTopPadding = useTopPadding,
            selectOptionCallback = selectOptionCallback
        ).also {
            activity.addOptionView(it)
        }
    }

    fun selectOptionView(
        activity: PlaygroundActivity,
        initialText: String?,
        label: String,
        useDirectCallback: Boolean,
        selectList: List<String>,
        selectedPosition: Int,
        description: String = "",
        useTopPadding: Boolean = true,
        selectOptionCallback: (String, Int) -> Unit
    ): HongLabelSelectInputView {
        return HongLabelSelectInputView(activity).setSelectView(
            HongLabelSelectInputBuilder()
                .padding(
                    HongSpacingInfo(
                        top = if (useTopPadding) Consts.PLAYGROUND_TOP_PADDING_FLOAT else 0f
                    )
                )
                .label(label)
                .description(description)
                .buttonText(initialText)
                .selectList(selectList)
                .selectPosition(selectedPosition)
                .useDirectCallback(useDirectCallback)
                .pickerCallback(selectOptionCallback)
                .applyOption()
        )
    }

    fun addSelectInputOptionView(
        activity: PlaygroundActivity,
        initialButtonText: String?,
        initialInputText: String?,
        label: String,
        selectList: List<String>,
        selectedPosition: Int,
        showInput: Boolean,
        useDirectCallback: Boolean,
        description: String = "",
        useOnlyNumber: Boolean = false,
        useTopPadding: Boolean = true,
        inputCallback: (String?) -> Unit,
        selectOptionCallback: (String, Int) -> Unit
    ) {
        selectInputOptionView(
            activity = activity,
            initialButtonText = initialButtonText,
            initialInputText = initialInputText,
            label = label,
            selectList = selectList,
            selectedPosition = selectedPosition,
            showInput = showInput,
            description = description,
            useDirectCallback = useDirectCallback,
            useOnlyNumber = useOnlyNumber,
            useTopPadding = useTopPadding,
            inputCallback = inputCallback,
            selectOptionCallback = selectOptionCallback
        ).also {
            activity.addOptionView(it)
        }
    }

    fun selectInputOptionView(
        activity: PlaygroundActivity,
        initialButtonText: String?,
        initialInputText: String?,
        label: String,
        selectList: List<String>,
        selectedPosition: Int,
        showInput: Boolean,
        useDirectCallback: Boolean,
        description: String = "",
        useOnlyNumber: Boolean = false,
        useTopPadding: Boolean = true,
        inputCallback: (String?) -> Unit,
        selectOptionCallback: (String, Int) -> Unit,
    ): HongLabelSelectInputView {
        return HongLabelSelectInputView(activity).setSelectInputView(
            HongLabelSelectInputBuilder()
                .padding(
                    HongSpacingInfo(
                        top = if (useTopPadding) Consts.PLAYGROUND_TOP_PADDING_FLOAT else 0f
                    )
                )
                .label(label)
                .description(description)
                .buttonText(initialButtonText)
                .inputText(initialInputText)
                .selectList(selectList)
                .selectPosition(selectedPosition)
                .useDirectCallback(useDirectCallback)
                .useOnlyNumber(useOnlyNumber)
                .showInput(showInput)
                .inputCallback(inputCallback)
                .pickerCallback(selectOptionCallback)
                .applyOption()
        )
    }

    fun addViewSelectTypoOption(
        activity: PlaygroundActivity,
        typo: HongTypo,
        label: String,
        description: String = "",
        useTopPadding: Boolean = true,
        callback: (HongTypo) -> Unit
    ) {
        val initialTypography = typographyList.firstOrNull {
            it == typo
        } ?: HongTypo.BODY_14
        addViewSelectOption(
            activity = activity,
            initialText = initialTypography.styleName,
            selectList = typoNameList,
            selectedPosition = typographyList.indexOf(initialTypography),
            label = label,
            description = description,
            useDirectCallback = true,
            useTopPadding = useTopPadding
        ) { selectTypography, _ ->
            val typography = typographyList.firstOrNull { it.styleName == selectTypography }
                    ?: HongTypo.BODY_16_B
            callback(typography)
        }
    }

    fun getSelectTypoOptionView(
        activity: PlaygroundActivity,
        typo: HongTypo,
        label: String,
        description: String = "",
        useTopPadding: Boolean = true,
        callback: (HongTypo) -> Unit
    ): View {
        val initialTypo = typographyList.firstOrNull {
            it == typo
        } ?: HongTypo.BODY_14

        return selectOptionView(
            activity = activity,
            initialText = initialTypo.styleName,
            label = label,
            selectList = typoNameList,
            selectedPosition = typographyList.indexOf(initialTypo),
            description = description,
            useDirectCallback = true,
            useTopPadding = useTopPadding,
            selectOptionCallback =  { selectTypography, _ ->
                val typography = typographyList.firstOrNull { it.styleName == selectTypography }
                    ?: HongTypo.BODY_16_B
                callback(typography)
            }
        )
    }


    fun getDrawableResourceNameList(context: Context): List<String> {
        val drawables = R.drawable::class.java.fields
        return drawables.mapNotNull { field ->
            try {
                val resourceId = field.getInt(null)
                context.resources.getResourceEntryName(resourceId)
            } catch (e: Exception) {
                null
            }
        }
    }

    fun getDrawableResIdByName(name: String): Int? {
        return try {
            val field = R.drawable::class.java.getDeclaredField(name)
            field.getInt(null)
        } catch (e: Exception) {
            null // 이름이 없거나 오류 발생 시
        }
    }

    fun getLibraryDrawableResNameList(): List<String> {
        return try {
            val drawableClass = Class.forName("com.codehong.library.widget.R\$drawable")
            drawableClass.fields.mapNotNull { it.name }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getDrawableResIdByNameFromLibrary(name: String?): Int? {
        if (name.isNullOrEmpty()) return null
        return try {
            val drawableClass = Class.forName("com.codehong.library.widget.R\$drawable")
            val field = drawableClass.getDeclaredField(name)
            field.getInt(null)
        } catch (e: Exception) {
            null // 해당 이름의 drawable이 없거나 오류 발생
        }
    }
}
