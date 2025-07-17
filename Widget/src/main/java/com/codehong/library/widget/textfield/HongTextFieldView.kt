package com.codehong.library.widget.textfield

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.codehong.library.widget.R
import com.codehong.library.widget.databinding.HonglibViewTextFieldBinding
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

    private val binding =
        HonglibViewTextFieldBinding.inflate(LayoutInflater.from(context), this, true)

    private var debounceJob: Job? = null
    private var lastInput: String = ""

    var option = HongTextFieldOption()
        private set

    private var hasClearButton = false


    fun set(
        option: HongTextFieldOption
    ): HongTextFieldView {
        this.option = option

        with(binding.llContainer) {
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
                backgroundColor = option.backgroundColorHex,
                border = option.border,
                useShapeCircle = option.useShapeCircle,
                radius = option.radius,
            )
        }

        with(binding.etInput) {
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

            this.setText(option.inputTextOption.text ?: "")
            this.setTextColor((option.inputTextOption.colorHex ?: HongTextFieldOption.DEFAULT_INPUT_COLOR).toParseColor())
            this.textSize = (option.inputTextOption.size ?: HongTextFieldOption.DEFAULT_INPUT_SIZE).toFloat()

            setHintStyle(option.placeholderTextOption)

            checkFont(
                input = this.text.toString(),
                hintFontId = option.placeholderTextOption.fontType?.font
                    ?: HongTextFieldOption.DEFAULT_PLACEHOLDER_FONT_TYPE.font,
                inputFontId = option.inputTextOption.fontType?.font
                    ?: HongTextFieldOption.DEFAULT_INPUT_FONT_TYPE.font,
            )

            toKeyboardOptions(option.keyboardOption)
            setUseHideKeyboard(option.useHideKeyboard)

            doAfterTextChanged { text ->
                val input = text.toString()
                lastInput = input

                checkFont(
                    input = input,
                    hintFontId = option.placeholderTextOption.fontType?.font
                        ?: HongTextFieldOption.DEFAULT_PLACEHOLDER_FONT_TYPE.font,
                    inputFontId = option.inputTextOption.fontType?.font
                        ?: HongTextFieldOption.DEFAULT_INPUT_FONT_TYPE.font,
                )

                if (input.isEmpty()) {
                    binding.ivClear.visibility = View.GONE
                } else {
                    if (hasClearButton) {
                        binding.ivClear.visibility = View.VISIBLE
                    } else {
                        binding.ivClear.visibility = View.GONE
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

        option.clearImageOption?.let {
            hasClearButton = true
            binding.ivClear.set(
                HongImageBuilder()
                    .copy(it)
                    .onClick{
                        binding.etInput.text?.clear()
                    }
                    .applyOption()
            )
        }

        if (option.inputTextOption.text.isNullOrEmpty()) {
            binding.etInput.setText("")
            binding.ivClear.visibility = View.GONE
        } else {
            binding.etInput.setText(option.inputTextOption.text)
            if (hasClearButton) {
                binding.ivClear.visibility = View.VISIBLE
            } else {
                binding.ivClear.visibility = View.GONE
            }
        }
        return this
    }
}
