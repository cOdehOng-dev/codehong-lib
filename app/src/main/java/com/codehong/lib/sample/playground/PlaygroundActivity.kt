package com.codehong.lib.sample.playground

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.button.select.HongSelectButtonPlayground
import com.codehong.lib.sample.button.text.HongTextButtonPlayground
import com.codehong.lib.sample.calendar.CalendarPlayground
import com.codehong.lib.sample.checkbox.HongCheckboxPlayground
import com.codehong.lib.sample.closeheader.HongCloseHeaderPlayground
import com.codehong.lib.sample.databinding.ActivityPlaygroundBinding
import com.codehong.lib.sample.image.HongImagePlayground
import com.codehong.lib.sample.label.HongLabelPlayground
import com.codehong.lib.sample.label.checkbox.HongLabelCheckboxPlayground
import com.codehong.lib.sample.label.input.HongLabelInputPlayground
import com.codehong.lib.sample.label.toggle.HongLabelSwitchPlayground
import com.codehong.lib.sample.pager.HongHorizontalPagerPlayground
import com.codehong.lib.sample.tab.HongTabScrollPlayground
import com.codehong.lib.sample.tab.segment.HongTabSegmentPlayground
import com.codehong.lib.sample.text.HongTextPlayground
import com.codehong.lib.sample.text.badge.HongTextBadgePlayground
import com.codehong.lib.sample.text.check.HongTextCheckPlayground
import com.codehong.lib.sample.text.unit.HongTextUnitPlayground
import com.codehong.lib.sample.text.updown.HongTextUpDownPlayground
import com.codehong.lib.sample.textfield.HongTextFieldPlayground
import com.codehong.lib.sample.textfield.number.HongNumberTextFieldPlayground
import com.codehong.lib.sample.textfield.timer.HongTimerTextFieldPlayground
import com.codehong.lib.sample.textfield.underline.HongUnderlineTextFieldPlayground
import com.codehong.lib.sample.toggleswitch.HongSwitchPlayground
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.button.select.HongSelectButtonCompose
import com.codehong.library.widget.button.select.HongSelectButtonOption
import com.codehong.library.widget.button.text.HongTextButtonCompose
import com.codehong.library.widget.button.text.HongTextButtonOption
import com.codehong.library.widget.calendar.HongCalendarCompose
import com.codehong.library.widget.calendar.HongCalendarOption
import com.codehong.library.widget.checkbox.HongCheckBoxCompose
import com.codehong.library.widget.checkbox.HongCheckboxOption
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.header.HongCloseHeaderCompose
import com.codehong.library.widget.header.HongCloseHeaderOption
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.image.HongImageOption
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.label.HongLabelViewCompose
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxCompose
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxOption
import com.codehong.library.widget.label.input.HongLabelInputCompose
import com.codehong.library.widget.label.input.HongLabelInputOption
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchCompose
import com.codehong.library.widget.label.toggleswitch.HongLabelSwitchOption
import com.codehong.library.widget.pager.HongHorizontalPagerCompose
import com.codehong.library.widget.pager.HongHorizontalPagerOption
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.tab.HongScrollTabCompose
import com.codehong.library.widget.tab.HongScrollTabOption
import com.codehong.library.widget.tab.segment.HongTabSegmentCompose
import com.codehong.library.widget.tab.segment.HongTabSegmentOption
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.text.HongTextOption
import com.codehong.library.widget.text.badge.HongTextBadgeCompose
import com.codehong.library.widget.text.badge.HongTextBadgeOption
import com.codehong.library.widget.text.check.HongCheckTextOption
import com.codehong.library.widget.text.check.HongTextCheckCompose
import com.codehong.library.widget.text.unit.HongTextUnitCompose
import com.codehong.library.widget.text.unit.HongTextUnitOption
import com.codehong.library.widget.text.updown.HongTextUpDownCompose
import com.codehong.library.widget.text.updown.HongTextUpDownOption
import com.codehong.library.widget.textfield.HongTextFieldCompose
import com.codehong.library.widget.textfield.HongTextFieldOption
import com.codehong.library.widget.textfield.number.HongNumberTextFieldCompose
import com.codehong.library.widget.textfield.number.HongNumberTextFieldOption
import com.codehong.library.widget.textfield.timer.HongTimerTextFieldCompose
import com.codehong.library.widget.textfield.timer.HongTimerTextFieldOption
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldCompose
import com.codehong.library.widget.textfield.underline.HongUnderlineTextFieldOption
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder
import com.codehong.library.widget.toggleswitch.HongSwitchCompose
import com.codehong.library.widget.toggleswitch.HongSwitchOption

class PlaygroundActivity : BaseActivity() {

    private lateinit var binding: ActivityPlaygroundBinding

    private val viewModel by viewModels<PlaygroundViewModel>()

    private var componentType: HongWidgetType? = null
    var previewOption: HongWidgetCommonOption? = null
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaygroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        componentType = intent.getStringExtra(SampleConst.WIDGET_TYPE).toHongWidgetType()

        if (componentType == HongWidgetType.NO_VALUE) {
            finish()
            return
        }

        binding.vHeader.init(
            title = componentType?.value ?: "",
            back = {
                finish()
            }
        )

        setupObservers()
        setOptionView()
    }

    private fun setupObservers() {
        viewModel.isBorderOn.observe(this) { isBorderOn ->
            applyPreviewUI(isBorderOn)
        }
    }

    private fun setOptionView() {
        binding.ivCheckWhite.visibility = View.VISIBLE
        binding.ivCheckBlack.visibility = View.GONE
        binding.ivCheckGray.visibility = View.GONE

        binding.vChangeWhite.setOnClickListener {
            binding.ivCheckWhite.visibility = View.VISIBLE
            binding.ivCheckBlack.visibility = View.GONE
            binding.ivCheckGray.visibility = View.GONE
            binding.vComposePreview.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    com.codehong.library.widget.R.color.honglib_color_ffffff
                )
            )
        }
        binding.vChangeBlack.setOnClickListener {
            binding.ivCheckWhite.visibility = View.GONE
            binding.ivCheckBlack.visibility = View.VISIBLE
            binding.ivCheckGray.visibility = View.GONE
            binding.vComposePreview.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    com.codehong.library.widget.R.color.honglib_color_000000
                )
            )
        }
        binding.vChangeGray.setOnClickListener {
            binding.ivCheckWhite.visibility = View.GONE
            binding.ivCheckBlack.visibility = View.GONE
            binding.ivCheckGray.visibility = View.VISIBLE
            binding.vComposePreview.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    com.codehong.library.widget.R.color.honglib_color_999999
                )
            )
        }

        with(binding.vSwitchBorderLine) {
            set(
                HongSwitchBuilder()
                    .width(55)
                    .height(30)
                    .onColor(HongColor.MAIN_ORANGE_100)
                    .offColor(HongColor.GRAY_20)
                    .cursorSize(25)
                    .cursorHorizontalMargin(3)
                    .cursorColor(HongColor.WHITE_100)
                    .initialState(false)
                    .switchClick { _, isOn ->
                        viewModel.isBorderOn.value = isOn
                    }
                    .applyOption()
            )
        }

        when (componentType) {
            HongWidgetType.TEXT -> HongTextPlayground(this).preview()
            HongWidgetType.TEXT_CHECK -> HongTextCheckPlayground(this).preview()
            HongWidgetType.TEXT_UNIT -> HongTextUnitPlayground(this).preview()
            HongWidgetType.TEXT_UP_DOWN -> HongTextUpDownPlayground(this).preview()
            HongWidgetType.IMAGE -> HongImagePlayground(this).preview()
            HongWidgetType.CLOSE_HEADER -> HongCloseHeaderPlayground(this).preview()
            HongWidgetType.TEXT_FILED -> HongTextFieldPlayground(this).preview()
            HongWidgetType.UNDERLINE_TEXT_FIELD -> HongUnderlineTextFieldPlayground(this).preview()
            HongWidgetType.TIMER_TEXT_FIELD -> HongTimerTextFieldPlayground(this).preview()
            HongWidgetType.NUMBER_TEXT_FIELD -> HongNumberTextFieldPlayground(this).preview()
            HongWidgetType.CALENDAR -> CalendarPlayground(this).preview()
            HongWidgetType.TEXT_BUTTON -> HongTextButtonPlayground(this).preview()
            HongWidgetType.SELECT_BUTTON -> HongSelectButtonPlayground(this).preview()
            HongWidgetType.HORIZONTAL_PAGER -> HongHorizontalPagerPlayground(this).preview()
            HongWidgetType.TEXT_BADGE -> HongTextBadgePlayground(this).preview()
            HongWidgetType.CHECKBOX -> HongCheckboxPlayground(this).preview()
            HongWidgetType.SWITCH -> HongSwitchPlayground(this).preview()
            HongWidgetType.LABEL -> HongLabelPlayground(this).preview()
            HongWidgetType.LABEL_INPUT -> HongLabelInputPlayground(this).preview()
            HongWidgetType.LABEL_SWITCH -> HongLabelSwitchPlayground(this).preview()
            HongWidgetType.LABEL_CHECKBOX -> HongLabelCheckboxPlayground(this).preview()
            HongWidgetType.SCROLL_TAB -> HongTabScrollPlayground(this).preview()
            HongWidgetType.TAB_SEGMENT -> HongTabSegmentPlayground(this).preview()
//            HongWidgetType.LABEL_SELECT_INPUT -> HongLabelSelectInputPlayground(this).preview()
            else -> {}
        }
    }

    fun applyPreview(
        previewOption: HongWidgetCommonOption
    ) {
        this.previewOption = previewOption
        val isBorderOn = viewModel.isBorderOn.value ?: false
        applyPreviewUI(isBorderOn)
    }

    fun addOptionView(view: View) {
        binding.llProperty.addView(view)
    }

    @Composable
    private fun PreviewUI(
        isBorderOn: Boolean,
        widgetCompose: @Composable () -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .border(
                        width = if (isBorderOn) 1.dp else 0.dp,
                        color = (if (isBorderOn) HongColor.MAIN_ORANGE_100.hex else HongColor.TRANSPARENT.hex).toColor()
                    )
            ) {
                widgetCompose()
            }
        }
    }

    private fun applyPreviewUI(
        isBorderOn: Boolean
    ) {
        when (componentType) {
            HongWidgetType.TEXT -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTextCompose(previewOption as HongTextOption)
                    }
                }
            }
            HongWidgetType.TEXT_CHECK -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTextCheckCompose(previewOption as HongCheckTextOption)
                    }
                }
            }
            HongWidgetType.TEXT_UNIT -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTextUnitCompose(previewOption as HongTextUnitOption)
                    }
                }
            }
            HongWidgetType.TEXT_UP_DOWN -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTextUpDownCompose(previewOption as HongTextUpDownOption)
                    }
                }
            }

            HongWidgetType.IMAGE -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongImageCompose(previewOption as HongImageOption)
                    }
                }
            }

            HongWidgetType.CLOSE_HEADER -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongCloseHeaderCompose(previewOption as HongCloseHeaderOption)
                    }
                }
            }

            HongWidgetType.TEXT_FILED -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTextFieldCompose(previewOption as HongTextFieldOption)
                    }
                }
            }
            HongWidgetType.UNDERLINE_TEXT_FIELD -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongUnderlineTextFieldCompose(previewOption as HongUnderlineTextFieldOption)
                    }
                }
            }
            HongWidgetType.TIMER_TEXT_FIELD -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTimerTextFieldCompose(previewOption as HongTimerTextFieldOption)
                    }
                }
            }
            HongWidgetType.NUMBER_TEXT_FIELD -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongNumberTextFieldCompose(previewOption as HongNumberTextFieldOption)
                    }
                }
            }


            HongWidgetType.TEXT_BUTTON -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTextButtonCompose(previewOption as HongTextButtonOption)
                    }
                }
            }


            HongWidgetType.SELECT_BUTTON -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongSelectButtonCompose(previewOption as HongSelectButtonOption)
                    }
                }
            }

            HongWidgetType.CALENDAR -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongCalendarCompose(previewOption as HongCalendarOption)
                    }
                }
            }

            HongWidgetType.HORIZONTAL_PAGER -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongHorizontalPagerCompose(
                            previewOption as HongHorizontalPagerOption
                        ) { item ->
                            (item as? String)
                                ?.toString()
                                ?.takeIf { it.isNotEmpty() }
                                ?.let { imageUrl ->
                                    HongImageCompose(
                                        option = HongImageBuilder()
                                            .width(HongLayoutParam.MATCH_PARENT.value)
                                            .height(150)
                                            .imageUrl(imageUrl)
                                            .radius(
                                                HongRadiusInfo(
                                                    all = 12
                                                )
                                            )
                                            .scaleType(HongScaleType.CENTER_CROP)
                                            .applyOption()
                                    )
                                }
                        }
                    }
                }
            }

            HongWidgetType.TEXT_BADGE -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTextBadgeCompose(previewOption as HongTextBadgeOption)
                    }
                }
            }

            HongWidgetType.CHECKBOX -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongCheckBoxCompose(previewOption as HongCheckboxOption)
                    }
                }
            }

            HongWidgetType.SWITCH -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongSwitchCompose(previewOption as HongSwitchOption)
                    }
                }
            }
            HongWidgetType.LABEL -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongLabelViewCompose(previewOption as HongLabelOption)
                    }
                }
            }

            HongWidgetType.LABEL_INPUT -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongLabelInputCompose(previewOption as HongLabelInputOption)
                    }
                }
            }
            HongWidgetType.LABEL_SWITCH -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongLabelSwitchCompose(previewOption as HongLabelSwitchOption)
                    }
                }
            }
            HongWidgetType.LABEL_CHECKBOX -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongLabelCheckboxCompose(previewOption as HongLabelCheckboxOption)
                    }
                }
            }

            HongWidgetType.SCROLL_TAB -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongScrollTabCompose(previewOption as HongScrollTabOption)
                    }
                }
            }

            HongWidgetType.TAB_SEGMENT -> {
                binding.vComposePreview.setContent {
                    PreviewUI(isBorderOn) {
                        HongTabSegmentCompose(previewOption as HongTabSegmentOption)
                    }
                }
            }


            else -> return
        }
    }
}