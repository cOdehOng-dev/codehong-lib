package com.codehong.library.widget.language

import android.content.Context
import android.view.ViewGroup
import com.codehong.library.widget.button.select.HongButtonSelectView
import com.codehong.library.widget.button.text.HongButtonTextView
import com.codehong.library.widget.checkbox.HongCheckboxView
import com.codehong.library.widget.image.HongImageView
import com.codehong.library.widget.label.HongLabelView
import com.codehong.library.widget.label.input.HongLabelInputView
import com.codehong.library.widget.label.select.HongLabelSelectInputView
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchView
import com.codehong.library.widget.text.label.HongTextView
import com.codehong.library.widget.text.unit.HongTextUnitView
import com.codehong.library.widget.textfield.HongTextFieldView
import com.codehong.library.widget.toggleswitch.HongSwitchView


fun Context.hongText(
    block: HongTextView.() -> Unit
) = HongTextView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongText(
    block: HongTextView.() -> Unit
) = HongTextView(this.context).run {
    block.invoke(this)
    this@hongText.addView(this)
    this
}

fun Context.hongTextUnit(
    block: HongTextUnitView.() -> Unit
) = HongTextUnitView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongTextUnit(
    block: HongTextUnitView.() -> Unit
) = HongTextUnitView(this.context).run {
    block.invoke(this)
    this@hongTextUnit.addView(this)
    this
}




fun Context.hongImage(
    block: HongImageView.() -> Unit
) = HongImageView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongImage(
    block: HongImageView.() -> Unit
) = HongImageView(this.context).run {
    block.invoke(this)
    this@hongImage.addView(this)
    this
}

fun Context.hongLabel(
    block: HongLabelView.() -> Unit
) = HongLabelView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongLabel(
    block: HongLabelView.() -> Unit
) = HongLabelView(this.context).run {
    block.invoke(this)
    this@hongLabel.addView(this)
    this
}

fun Context.hongLabelInput(
    block: HongLabelInputView.() -> Unit
) = HongLabelInputView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongLabelInput(
    block: HongLabelInputView.() -> Unit
) = HongLabelInputView(this.context).run {
    block.invoke(this)
    this@hongLabelInput.addView(this)
    this
}

fun Context.hongTextButton(
    block: HongButtonTextView.() -> Unit
) = HongButtonTextView(this).run {
    block.invoke(this)
    this
}
fun ViewGroup.hongTextButton(
    block: HongButtonTextView.() -> Unit
) = HongButtonTextView(this.context).run {
    block.invoke(this)
    this@hongTextButton.addView(this)
    this
}

fun Context.hongSelectButton(
    block: HongButtonSelectView.() -> Unit
) = HongButtonSelectView(this).run {
    block.invoke(this)
    this
}
fun ViewGroup.hongSelectButton(
    block: HongButtonSelectView.() -> Unit
) = HongButtonSelectView(this.context).run {
    block.invoke(this)
    this@hongSelectButton.addView(this)
    this
}

fun Context.hongTextField(
    block: HongTextFieldView.() -> Unit
) = HongTextFieldView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongTextField(
    block: HongTextFieldView.() -> Unit
) = HongTextFieldView(this.context).run {
    block.invoke(this)
    this@hongTextField.addView(this)
    this
}

fun Context.hongSelectInput(
    block: HongLabelSelectInputView.() -> Unit
) = HongLabelSelectInputView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongSelectInput(
    block: HongLabelSelectInputView.() -> Unit
) = HongLabelSelectInputView(this.context).run {
    block.invoke(this)
    this@hongSelectInput.addView(this)
    this
}

fun Context.hongSwitch(
    block: HongSwitchView.() -> Unit
) = HongSwitchView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongSwitch(
    block: HongSwitchView.() -> Unit
) = HongSwitchView(this.context).run {
    block.invoke(this)
    this@hongSwitch.addView(this)
    this
}

fun Context.hongLabelSwitch(
    block: HongLabelSwitchView.() -> Unit
) = HongLabelSwitchView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongLabelSwitch(
    block: HongLabelSwitchView.() -> Unit
) = HongLabelSwitchView(this.context).run {
    block.invoke(this)
    this@hongLabelSwitch.addView(this)
    this
}

fun Context.hongCheckbox(
    block: HongCheckboxView.() -> Unit
) = HongCheckboxView(this).run {
    block.invoke(this)
    this
}

fun ViewGroup.hongCheckbox(
    block: HongCheckboxView.() -> Unit
) = HongCheckboxView(this.context).run {
    block.invoke(this)
    this@hongCheckbox.addView(this)
    this
}


