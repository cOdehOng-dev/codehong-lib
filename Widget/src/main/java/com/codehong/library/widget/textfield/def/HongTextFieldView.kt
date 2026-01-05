package com.codehong.library.widget.textfield.def

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.checkFont
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setHintStyle
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.extensions.setUseHideKeyboard
import com.codehong.library.widget.extensions.toKeyboardOptions
import com.codehong.library.widget.extensions.toParseColor
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageView
import com.codehong.library.widget.language.hongImage
import com.codehong.library.widget.language.textField
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toParseColor
import com.codehong.library.widget.rule.typo.fontType
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.def.HongTextBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HongTextFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    private var debounceJob: Job? = null
    private var lastInput: String = ""

    var option = HongTextFieldOption()
        private set

    private var hasClearButton = false

    private var textField: AppCompatEditText? = null
    private var clearButton: HongImageView? = null


    fun set(
        option: HongTextFieldOption
    ): HongTextFieldView {
        removeAllViews()

        this.option = option

        setLayout(
            option.width,
            option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }
        hongPadding(
            HongSpacingInfo(
                left = option.padding.left,
                right = option.padding.right,
                top = 0f,
                bottom = 0f
            )
        )
        hongBackground(
            backgroundColor = option.backgroundColorHex,
            border = option.border,
            useShapeCircle = option.useShapeCircle,
            radius = option.radius,
        )

        textField {
            textField = this

            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                weight = 1f
            }
            setBackgroundColor(HongColor.TRANSPARENT.toParseColor())

            setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    true
                } else {
                    false
                }
            }

            setOnFocusChangeListener { _, hasFocus ->
                isCursorVisible = hasFocus
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                this.textCursorDrawable = ContextCompat.getDrawable(
                    context,
                    R.drawable.honglib_view_text_field_cursor
                )
            }
            this.maxLines = if (option.singleLine) 1 else option.maxLines
            this.minLines = option.minLines

            this.setText(option.input ?: "")
            this.setTextColor((option.inputColorHex).toParseColor())
            this.textSize = (option.inputTypo.size()).toFloat()

            setHintStyle(
                HongTextBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .padding(option.placeholderPadding)
                    .text(option.placeholder)
                    .typography(option.placeholderTypo)
                    .color(option.placeholderColorHex)
                    .applyOption()
            )

            checkFont(
                input = this.text.toString(),
                hintFontId = option.placeholderTypo.fontType().font,
                inputFontId = option.inputTypo.fontType().font
            )

            toKeyboardOptions(option.keyboardOption)
            setUseHideKeyboard(option.useHideKeyboard)

            doAfterTextChanged { text ->
                val input = text.toString()
                lastInput = input

                checkFont(
                    input = input,
                    hintFontId = option.placeholderTypo.fontType().font,
                    inputFontId = option.inputTypo.fontType().font
                )

                if (input.isEmpty()) {
                    clearButton?.visibility = View.GONE
                } else {
                    if (hasClearButton) {
                        clearButton?.visibility = View.VISIBLE
                    } else {
                        clearButton?.visibility = View.GONE
                    }
                }

                if (option.delayInputCallback > HongTextFieldOption.DEFAULT_DELAY_INPUT_CALLBACK) {
                    debounceJob?.cancel()
                    debounceJob = CoroutineScope(Dispatchers.Main).launch {
                        delay(option.delayInputCallback)
                        if (lastInput == input) {
                            option.onTextChanged.invoke(input)
                        }
                    }
                } else {
                    option.onTextChanged.invoke(input)
                }
            }
        }

        option.clearIconRes?.let {
            hasClearButton = true
            hongImage {
                clearButton = this
                set(
                    HongImageBuilder()
                        .width(option.clearIconSize)
                        .height(option.clearIconSize)
                        .margin(option.clearIconMargin)
                        .imageInfo(option.clearIconRes)
                        .onClick{
                            textField?.text?.clear()
                        }
                        .applyOption()
                )
            }
        }

        if (option.input.isNullOrEmpty()) {
            textField?.setText("")
            clearButton?.visibility = View.GONE
        } else {
            textField?.setText(option.input)
            if (hasClearButton) {
                clearButton?.visibility = View.VISIBLE
            } else {
                clearButton?.visibility = View.GONE
            }
        }
        return this
    }
}
