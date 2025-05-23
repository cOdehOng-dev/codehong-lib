package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.codehong.lib.sample.databinding.ViewInputBinding
import com.codehong.library.widget.util.Utils

class InputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewInputBinding.inflate(LayoutInflater.from(context), this, true)

    fun init(
        initial: String? = null,
        isNumberType: Boolean = false
    ) {
        if (isNumberType) {
            binding.etOption.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        } else {
            binding.etOption.inputType = android.text.InputType.TYPE_CLASS_TEXT
        }
        binding.flClearButton.visibility = View.GONE
        binding.flClearButton.setOnClickListener {
            binding.etOption.text?.clear()
        }
        binding.flClearButton.visibility =
            if (initial.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
        binding.etOption.setText(initial ?: "")
    }

    fun observe(
        inputCallback: (String?) -> Unit
    ) {
        binding.etOption.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.flClearButton.visibility =
                    if (s.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
                inputCallback.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        binding.etOption.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.etOption.clearFocus()
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etOption.windowToken, 0)
                true
            } else {
                false
            }
        }
    }

    fun setProperty(property: String) {
        binding.etOption.setText(property)
        binding.flClearButton.visibility = if (property.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    fun inputTextSize(dp: Float) {
        binding.etOption.textSize = Utils.dpToFloatPx(context, dp)
    }

    fun show() {
        binding.root.visibility = View.VISIBLE
    }

    fun hide() {
        binding.root.visibility = View.GONE
    }
}
