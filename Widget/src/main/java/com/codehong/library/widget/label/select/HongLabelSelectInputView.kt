package com.codehong.library.widget.label.select

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonOption
import com.codehong.library.widget.button.text.HongTextButtonView
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.label.HongLabelBuilder2
import com.codehong.library.widget.language.hongLabel2
import com.codehong.library.widget.language.hongTextButton
import com.codehong.library.widget.language.hongTextField
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldBuilder
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
    private var textButton: HongTextButtonView? = null

    private var textButtonOption: HongTextButtonOption? = null

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
        hongLabel2 {
            set(
                HongLabelBuilder2()
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

        hongLabel2 {
            set(
                HongLabelBuilder2()
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
            .textFieldOption(
                HongTextFieldBuilder()
                    .copy(option.textFieldOption)
                    .margin(
                        HongSpacingInfo(
                            top = 10f
                        )
                    )
                    .input(option.textFieldOption.inputTextOption.text ?: option.textButtonOption.textOption.text)
                    .placeholder(option.textFieldOption.placeholderTextOption.text)
                    .keyboardOption(
                        Pair(
                            if (option.useOnlyNumber) HongKeyboardType.NUMBER else HongKeyboardType.TEXT,
                            HongKeyboardActionType.DONE
                        )
                    )
                    .onTextChanged {
                        option.inputCallback?.invoke(it)
                    }
                    .applyOption()
            )
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
            .textFieldOption(
                HongTextFieldBuilder()
                    .copy(option.textFieldOption)
                    .input(input)
                    .applyOption()
            )
            .applyOption()
        textField?.set(option.textFieldOption)

    }

    private fun initSelectPickerView() {
        val initial = option.textButtonOption.textOption.text

        this.textButtonOption = HongTextButtonBuilder()
            .copy(option.textButtonOption)
            .textOption(
                HongTextBuilder()
                    .copy(option.buttonTextOption)
                    .text(if (!initial.isNullOrEmpty()) initial else "")
                    .applyOption()
            )
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
                        .textButtonOption(
                            HongTextButtonBuilder()
                                .copy(
                                    HongTextButtonBuilder()
                                        .copy(textButtonOption)
                                        .textOption(
                                            HongTextBuilder()
                                                .copy(option.buttonTextOption)
                                                .text(selectOption)
                                                .applyOption()
                                        )
                                        .applyOption()
                                )
                                .applyOption()
                        )
                        .applyOption()
                    textButton?.set(option.textButtonOption)
                    option.pickerCallback?.invoke(selectOption, option.selectList.indexOf(selectOption))
                }.show()
            }
            .applyOption()

        this.option = HongLabelSelectInputBuilder()
            .copy(option)
            .textButtonOption(
                HongTextButtonBuilder()
                    .copy(textButtonOption)
                    .applyOption()
            )
            .applyOption()

        this.textButton = hongTextButton {
            set(this@HongLabelSelectInputView.option.textButtonOption)
        }
    }
}
