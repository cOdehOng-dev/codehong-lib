package com.codehong.lib.sample.playground.preview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.codehong.library.widget.Consts
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.input.HongLabelInputBuilder
import com.codehong.library.widget.label.input.HongLabelInputOption
import com.codehong.library.widget.language.frameLayout
import com.codehong.library.widget.language.hongLabel
import com.codehong.library.widget.language.hongLabelInput
import com.codehong.library.widget.language.horizontalLinearLayout
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType

class QuarterInputOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    fun set(
        label: String,
        description: String = "",
        useTopPadding: Boolean = true,
        inputInit1: Triple<String?, String?, Boolean>,
        inputInit2: Triple<String?, String?, Boolean>,
        inputInit3: Triple<String?, String?, Boolean>,
        inputInit4: Triple<String?, String?, Boolean>,
        inputCallback1: (String?) -> Unit,
        inputCallback2: (String?) -> Unit,
        inputCallback3: (String?) -> Unit,
        inputCallback4: (String?) -> Unit
    ) {
        setPadding(
            paddingLeft,
            if (useTopPadding) context.dpToPx(Consts.PLAYGROUND_TOP_PADDING) else 0,
            paddingRight,
            paddingBottom
        )

        hongLabel {
            set(
                HongLabelBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .height(HongLayoutParam.WRAP_CONTENT.value)
                    .label(label)
                    .description(description)
                    .applyOption()
            )
        }

        horizontalLinearLayout {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            setPadding(
                paddingLeft,
                context.dpToPx(10f),
                paddingRight,
                paddingBottom
            )


            frameLayout {
                layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                    marginEnd = context.dpToPx(5f)
                }
                hongLabelInput {
                    set(
                        labelInputOption(
                            inputInit = inputInit1,
                            callback = inputCallback1
                        )
                    )
                }
            }

            frameLayout {
                layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                    marginEnd = context.dpToPx(5f)
                }
                hongLabelInput {
                    set(
                        labelInputOption(
                            inputInit = inputInit2,
                            callback = inputCallback2
                        )
                    )
                }
            }

            frameLayout {
                layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                    marginEnd = context.dpToPx(5f)
                }
                hongLabelInput {
                    set(
                        labelInputOption(
                            inputInit = inputInit3,
                            callback = inputCallback3
                        )
                    )
                }
            }

            frameLayout {
                layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                    marginEnd = context.dpToPx(5f)
                }

                hongLabelInput {
                    set(
                        labelInputOption(
                            inputInit = inputInit4,
                            callback = inputCallback4
                        )
                    )
                }
            }
        }
    }

    private fun labelInputOption(
        inputInit: Triple<String?, String?, Boolean>,
        callback: (String?) -> Unit
    ): HongLabelInputOption {
        return HongLabelInputBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .label(inputInit.first)
            .input(inputInit.second)
            .keyboardOption(
                Pair(
                    if (inputInit.third) {
                        HongKeyboardType.NUMBER
                    } else {
                        HongKeyboardType.TEXT
                    }, HongKeyboardActionType.DONE
                )
            )
            .clearIconSize(13)
            .onTextChanged(callback)
            .applyOption()
    }
}
