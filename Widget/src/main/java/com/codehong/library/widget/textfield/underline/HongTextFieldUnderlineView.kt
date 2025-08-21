package com.codehong.library.widget.textfield.underline

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.checkFont
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.parseColor
import com.codehong.library.widget.extensions.setHintStyle
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.extensions.setUseHideKeyboard
import com.codehong.library.widget.extensions.toKeyboardOptions
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageView
import com.codehong.library.widget.language.hongImage
import com.codehong.library.widget.language.horizontalLinearLayout
import com.codehong.library.widget.language.textField
import com.codehong.library.widget.language.view
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.parseColor
import com.codehong.library.widget.rule.typo.fontType
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.HongTextBuilder

class HongTextFieldUnderlineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var option = HongTextFieldUnderlineOption()
        private set

    private var hasClearButton = false

    private var textField: AppCompatEditText? = null
    private var clearButton: HongImageView? = null
    private var underlineView: View? = null


    fun set(
        option: HongTextFieldUnderlineOption
    ): HongTextFieldUnderlineView {
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
        hongBackground(backgroundColor = option.backgroundColorHex)

        horizontalLinearLayout {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            gravity = Gravity.CENTER_VERTICAL
            textField {
                textField = this

                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                }
                setBackgroundColor(HongColor.TRANSPARENT.parseColor())

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

                    if (hasFocus) {
                        underlineView?.setBackgroundColor(option.underlineFocusColor.parseColor())
                    } else {
                        underlineView?.setBackgroundColor(option.underlineOutFocusColor.parseColor())
                    }
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    this.textCursorDrawable = ContextCompat.getDrawable(
                        context,
                        R.drawable.honglib_view_text_field_cursor
                    )
                }
                this.maxLines = 1

                this.setText(option.input ?: "")
                this.setTextColor(option.inputColorHex.parseColor())
                this.textSize = option.inputTypo.size().toFloat()

                setHintStyle(
                    HongTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .text(option.placeholder)
                        .typography(option.placeholderTypo)
                        .color(option.placeholderColorHex)
                        .applyOption()
                )


                checkFont(
                    input = this.text.toString(),
                    hintFontId = option.placeholderTypo.fontType().font,
                    inputFontId = option.inputTypo.fontType().font,
                )

                toKeyboardOptions(option.keyboardOption)
                setUseHideKeyboard(option.useHideKeyboard)

                doAfterTextChanged { text ->
                    val input = text.toString()

                    checkFont(
                        input = input,
                        hintFontId = option.placeholderTypo.fontType().font,
                        inputFontId = option.inputTypo.fontType().font,
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
                            .drawableResId(option.clearIconRes)
                            .onClick{
                                textField?.text?.clear()
                            }
                            .applyOption()
                    )
                }
            }
        }

        view {
            underlineView = this
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, context.dpToPx(option.underlineHeight)).apply {
                gravity = Gravity.BOTTOM
            }
            setBackgroundColor(option.underlineOutFocusColor.parseColor())
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