package com.codehong.library.widget.label.select

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.button.text.HongButtonTextView
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.label.def.HongLabelBuilder
import com.codehong.library.widget.language.hongLabel
import com.codehong.library.widget.language.hongTextButton
import com.codehong.library.widget.language.hongTextField
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.textfield.HongTextFieldView
import com.codehong.library.widget.util.picker.OptionPickerDialog

class HongLabelSelectInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
    }

    var option: HongLabelSelectInputOption = HongLabelSelectInputOption()
        private set

    private var currentPosition = 0

    private var textField: HongTextFieldView? = null
    private var textButton: HongButtonTextView? = null

    private var textButtonOption = HongButtonTextBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .radius(
            HongRadiusInfo(
                topLeft = 10,
                topRight = 10,
                bottomLeft = 10,
                bottomRight = 10
            )
        )
        .padding(
            HongSpacingInfo(
                top = 14f,
                bottom = 14f
            )
        )
        .margin(
            HongSpacingInfo(
                top = 10f,
            )
        )
        .textTypo(HongTypo.BODY_15)
        .textColor(HongColor.MAIN_ORANGE_100)
        .border(
            HongBorderInfo(
                width = 1,
                color = HongColor.MAIN_ORANGE_100.hex
            )
        )
        .backgroundColor(HongColor.WHITE_100)
        .applyOption()

    fun setSelectView(
        option: HongLabelSelectInputOption,
    ): HongLabelSelectInputView {
        this.option = option
        this.currentPosition = option.selectPosition

        setLayout(
            option.width,
            option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }
        hongPadding(option.padding)
        hongBackground(
            backgroundColor = option.backgroundColorHex
        )
        hongLabel {
            set(
                HongLabelBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .backgroundColor(HongColor.TRANSPARENT)
                    .label(option.label)
                    .labelTypo(option.labelTypo)
                    .labelColor(option.labelColorHex)
                    .description(option.description)
                    .descriptionTypo(option.descriptionTypo)
                    .descriptionColor(option.descriptionColorHex)
                    .applyOption()
            )
        }

        initSelectPickerView()

        return this
    }

    fun setSelectInputView(
        option: HongLabelSelectInputOption,
    ): HongLabelSelectInputView {
        this.option = option
        this.currentPosition = option.selectPosition

        setLayout(
            option.width,
            option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }
        hongPadding(option.padding)
        hongBackground(
            backgroundColor = option.backgroundColorHex
        )

        hongLabel {
            set(
                HongLabelBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .backgroundColor(HongColor.TRANSPARENT)
                    .label(option.label)
                    .labelTypo(option.labelTypo)
                    .labelColor(option.labelColorHex)
                    .description(option.description)
                    .descriptionTypo(option.descriptionTypo)
                    .descriptionColor(option.descriptionColorHex)
                    .applyOption()
            )
        }

        initSelectPickerView()

        this.option = HongLabelSelectInputBuilder()
            .copy(option)
            .inputText(option.input ?: option.buttonText)
            .placeholder(option.placeholder)
            .applyOption()

        hongTextField {
            textField = this
            set(this@HongLabelSelectInputView.option.textFieldOption)
        }

        if (option.showInput) {
            showInput()
        } else {
            hideInput()
        }

        return this
    }

    fun showInput() {
        textField?.visibility = View.VISIBLE
    }

    fun hideInput() {
        textField?.visibility = View.GONE
    }

    fun setInputText(input: String) {
        this.option = HongLabelSelectInputBuilder()
            .copy(option)
            .inputText(input)
            .applyOption()
        textField?.set(option.textFieldOption)

    }

    private fun initSelectPickerView() {
        val initial = option.buttonText

        val beforeButtonTextOption = textButtonOption

        this.textButtonOption = HongButtonTextBuilder()
            .copy(beforeButtonTextOption)
            .text(if (!initial.isNullOrEmpty()) initial else "")
            .textColor(option.buttonTextColorHex)
            .textTypo(option.buttonTextTypo)
            .onClick {
                OptionPickerDialog(
                    context,
                    title = "${option.label} 옵션 선택",
                    optionList = option.selectList,
                    selectedPosition = currentPosition,
                    useDirectCallback = option.useDirectCallback
                ) { selectOption, index ->
                    this.currentPosition = index
                    this.option = HongLabelSelectInputBuilder()
                        .copy(option)
                        .buttonTextColor(option.buttonTextColorHex)
                        .buttonTextTypo(option.buttonTextTypo)
                        .buttonText(selectOption)
                        .applyOption()
                    textButton?.set(HongButtonTextBuilder()
                        .copy(textButtonOption)
                        .textColor(option.buttonTextColorHex)
                        .textTypo(option.buttonTextTypo)
                        .text(selectOption)
                        .applyOption()
                    )
                    option.pickerCallback?.invoke(selectOption, option.selectList.indexOf(selectOption))
                }.show()
            }
            .applyOption()

        this.option = HongLabelSelectInputBuilder()
            .copy(option)
            .buttonText(if (!initial.isNullOrEmpty()) initial else "")
            .buttonTextColor(option.buttonTextColorHex)
            .buttonTextTypo(option.buttonTextTypo)
            .applyOption()

        hongTextButton {
            textButton = this
            set(textButtonOption)
        }
    }
}
