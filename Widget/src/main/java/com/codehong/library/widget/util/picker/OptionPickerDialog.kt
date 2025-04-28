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
import com.codehong.library.widget.R
import com.codehong.library.widget.databinding.HonglibOptionPickerDialogBinding

class OptionPickerDialog constructor(
    private val context: Context,
    private val title: String,
    private val optionList: List<String>,
    private var selectedPosition: Int,
    private val isDirectCallback: Boolean = false,
    private val selectOptionCallback: (String, Int) -> Unit
) : Dialog(context, R.style.Dialog_FullScreen) {

    private val binding: HonglibOptionPickerDialogBinding by lazy {
        HonglibOptionPickerDialogBinding.inflate(layoutInflater, null, false)
    }

    private var selectOption: String = ""

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setFullDialog(isAttachWindow = true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullDialog()
        setContentView(binding.root)
        this.selectOption = optionList[selectedPosition]

        if (isDirectCallback) {
            binding.tvCancel.visibility = View.GONE
        }

        binding.dim.setOnClickListener {
            dismiss()
        }

        binding.tvConfirm.setOnClickListener {
            dismiss()
            if (!isDirectCallback) {
                selectOptionCallback.invoke(selectOption, selectedPosition)
            }
        }

        binding.tvCancel.setOnClickListener {
            dismiss()
        }

        binding.tvTitle.text = title
        val picker = PickerListView(context).apply {
            initPicker(
                pickerList = optionList,
                selectPosition = selectedPosition
            ) { selectPicker, index ->
                this@OptionPickerDialog.selectOption = selectPicker
                this@OptionPickerDialog.selectedPosition = index
                if (isDirectCallback) {
                    selectOptionCallback.invoke(selectPicker, index)
                }
            }
        }

        if (binding.containerOptionPicker.childCount > 0) {
            binding.containerOptionPicker.removeAllViews()
        }
        binding.containerOptionPicker.addView(picker)
    }

    override fun show() {
        super.show()
        binding.clOptionPicker.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.honglib_popup_in
            )
        )
        binding.dim.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_motion_m))
    }

    override fun dismiss() {
        binding.dim.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out_motion_m))
        binding.clOptionPicker.startAnimation(
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
