package com.codehong.library.widget.util.picker

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.codehong.library.widget.R
import com.codehong.library.widget.button.select.HongSelectButtonBuilder
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.databinding.HonglibOptionPickerDialogBinding
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.language.hongSelectButton
import com.codehong.library.widget.language.hongText
import com.codehong.library.widget.language.hongTextButton
import com.codehong.library.widget.language.verticalLinearLayout
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.label.HongTextBuilder

class OptionPickerDialog constructor(
    private val context: Context,
    private val title: String,
    private val optionList: List<String>,
    private var selectedPosition: Int,
    private val useDirectCallback: Boolean = false,
    private val selectOptionCallback: (String, Int) -> Unit
) : Dialog(context, R.style.Dialog_FullScreen) {

    private val binding: HonglibOptionPickerDialogBinding by lazy {
        HonglibOptionPickerDialogBinding.inflate(layoutInflater, null, false)
    }

    private var selectOption: String = ""

    private var llOptionPicker: LinearLayout? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setFullDialog(isAttachWindow = true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullDialog()
        setContentView(binding.root)
        this.selectOption = optionList[selectedPosition]

        binding.llDialog.verticalLinearLayout {
            llOptionPicker = this
            setLayout(
                width = HongLayoutParam.MATCH_PARENT.value,
                height = HongLayoutParam.WRAP_CONTENT.value
            )?.apply {
                minimumHeight = context.dpToPx(50)
            }
            setPadding(
                0,
                context.dpToPx(20),
                0,
                context.dpToPx(20)
            )
            hongBackground(
                backgroundColor = HongColor.WHITE_100.hex,
                radius = HongRadiusInfo(
                    topLeft = 16,
                    topRight = 16
                )
            )

            hongText {
                set(
                    HongTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .padding(
                            HongSpacingInfo(
                                left = 20f,
                                right = 20f
                            )
                        )
                        .text(title)
                        .typography(HongTypo.BODY_20_B)
                        .color(HongColor.BLACK_100)
                        .applyOption()
                )
            }

            val picker = PickerListView(context).apply {
                initPicker(
                    pickerList = optionList,
                    selectPosition = selectedPosition
                ) { selectPicker, index ->
                    this@OptionPickerDialog.selectOption = selectPicker
                    this@OptionPickerDialog.selectedPosition = index
                    if (useDirectCallback) {
                        selectOptionCallback.invoke(selectPicker, index)
                    }
                }
            }

            verticalLinearLayout {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                addView(picker)
            }

            if (!useDirectCallback) {
                hongSelectButton {
                    set(
                        HongSelectButtonBuilder()
                            .margin(
                                HongSpacingInfo(
                                    left = 20f,
                                    right = 20f,
                                )
                            )
                            .onNegativeClick {
                                dismiss()
                            }
                            .onPositiveClick {
                                dismiss()
                                selectOptionCallback.invoke(selectOption, selectedPosition)
                            }
                            .applyOption()
                    )
                }
            } else {
                hongTextButton {
                    set(
                        HongButtonTextBuilder()
                            .width(HongLayoutParam.MATCH_PARENT.value)
                            .height(48)
                            .onClick {
                                dismiss()
                            }
                            .margin(
                                HongSpacingInfo(
                                    left = 20f,
                                    right = 20f,
                                    bottom = 10f
                                )
                            )
                            .text("확인")
                            .textTypo(HongTypo.BODY_15_B)
                            .textColor(HongColor.WHITE_100)
                            .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
                            .radius(
                                HongRadiusInfo(
                                    all = 12
                                )
                            )
                            .applyOption()
                    )
                }
            }
        }

        binding.dim.setOnClickListener {
            dismiss()
        }
    }

    override fun show() {
        super.show()
        llOptionPicker?.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.honglib_popup_in
            )
        )
        binding.dim.startAnimation(AnimationUtils.loadAnimation(context, R.anim.honglib_fade_in))
    }

    override fun dismiss() {
        binding.dim.startAnimation(AnimationUtils.loadAnimation(context, R.anim.honglib_fade_out))
        llOptionPicker?.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.honglib_popup_out
            ).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {}
                    override fun onAnimationStart(animation: Animation?) {}

                    override fun onAnimationEnd(animation: Animation?) {
                        super@OptionPickerDialog.dismiss()
                    }
                })
            }
        )
    }

    @Suppress("DEPRECATION")
    private fun setFullDialog(isAttachWindow: Boolean = false) {
        if (isAttachWindow && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.setDecorFitsSystemWindows(false)
            val insetController: WindowInsetsController? = window?.insetsController
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                insetController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_DEFAULT
            }
        } else if (!isAttachWindow && Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            window?.requestFeature(Window.FEATURE_NO_TITLE)
            window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}
