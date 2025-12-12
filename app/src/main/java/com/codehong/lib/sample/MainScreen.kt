package com.codehong.lib.sample

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.SampleType.Companion.toType
import com.codehong.lib.sample.bottomsheet.SampleBottomSheetSelectActivity
import com.codehong.lib.sample.bottomsheet.SampleBottomSheetSwipeActivity
import com.codehong.lib.sample.button.icon.SampleButtonIconActivity
import com.codehong.lib.sample.button.select.SampleSelectButtonActivity
import com.codehong.lib.sample.button.text.SampleTextButtonActivity
import com.codehong.lib.sample.calendar.SampleCalendarActivity
import com.codehong.lib.sample.calendar.SampleCalendarComposeActivity
import com.codehong.lib.sample.captureshare.SampleCaptureShareActivity
import com.codehong.lib.sample.checkbox.SampleCheckboxActivity
import com.codehong.lib.sample.closeheader.SampleCloseHeaderActivity
import com.codehong.lib.sample.dynamicisland.SampleDynamicIslandActivity
import com.codehong.lib.sample.graph.SampleGraphBarActivity
import com.codehong.lib.sample.graph.SampleGraphLineActivity
import com.codehong.lib.sample.icon.SampleIconActivity
import com.codehong.lib.sample.image.SampleImageActivity
import com.codehong.lib.sample.label.SampleLabelActivity
import com.codehong.lib.sample.label.checkbox.SampleLabelCheckboxActivity
import com.codehong.lib.sample.label.input.SampleLabelInputActivity
import com.codehong.lib.sample.label.select.SampleLabelSelectInputActivity
import com.codehong.lib.sample.label.toggle.SampleLabelSwitchActivity
import com.codehong.lib.sample.pager.SampleHorizontalPagerActivity
import com.codehong.lib.sample.picker.OptionPickerActivity
import com.codehong.lib.sample.picker.SamplePickerComposeActivity
import com.codehong.lib.sample.player.SampleVideoPlayerActivity
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.tab.flow.SampleTabFlowActivity
import com.codehong.lib.sample.tab.scroll.SampleTabScrollActivity
import com.codehong.lib.sample.tab.segment.SampleTabSegmentActivity
import com.codehong.lib.sample.text.SampleTextActivity
import com.codehong.lib.sample.text.badge.SampleTextBadgeActivity
import com.codehong.lib.sample.text.check.SampleCheckTextActivity
import com.codehong.lib.sample.text.count.SampleTextCountActivity
import com.codehong.lib.sample.text.unit.SampleTextUnitActivity
import com.codehong.lib.sample.text.updown.SampleTextUpDownActivity
import com.codehong.lib.sample.textfield.SampleTextFieldActivity
import com.codehong.lib.sample.textfield.border.SampleTextFieldBorderActivity
import com.codehong.lib.sample.textfield.borderselect.SampleTextFieldBorderSelectActivity
import com.codehong.lib.sample.textfield.number.SampleTextFieldNumberActivity
import com.codehong.lib.sample.textfield.timer.SampleTextFieldTimerActivity
import com.codehong.lib.sample.textfield.underline.SampleTextFieldUnderlineActivity
import com.codehong.lib.sample.toggleswitch.SampleSwitchActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupBuilderActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupComposeActivity
import com.codehong.library.widget.R
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.button.text.HongButtonTextCompose
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.picker.OptionPickerDialog

@Composable
fun MainScreen(
    activity: ComponentActivity
) {
    val sampleTypeList = listOf(
        SampleType.XML.value,
        SampleType.OPTION_BUILDER.value,
        SampleType.COMPOSE.value
    )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        colorResource(id = HongColor.MAIN_ORANGE_100.colorResId)
                    ),
                contentAlignment = Alignment.Center
            ) {
                HongTextCompose(
                    option = HongTextBuilder()
                        .text("라이브러리")
                        .fontType(HongFont.PRETENDARD_700)
                        .size(30)
                        .color("ffffff")
                        .applyOption(),
                )
            }
        },
        bottomBar = {}
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .background(colorResource(R.color.honglib_color_ffffff)),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(
                mutableListOf<SampleTestItem>().apply {
                    HongWidgetType.entries.forEach { item ->
                        if (item != HongWidgetType.NO_VALUE) {
                            add(
                                SampleTestItem(
                                    title = item.value,
                                    widgetType = item
                                )
                            )
                        }
                    }
                }
            ) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                        .background(HongColor.WHITE_100.toColor())
                ) {
                    Box(
                        modifier = Modifier
                            .weight(2f)
                            .align(Alignment.CenterVertically),
                    ) {
                        HongTextCompose(
                            option = HongTextBuilder()
                                .width(HongLayoutParam.MATCH_PARENT.value)
                                .text(item.title)
                                .typography(HongTypo.BODY_14_B)
                                .color(HongColor.BLACK_100)
                                .applyOption(),
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(5.dp)
                            .height(1.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1.5f)
                    ) {
                        HongButtonTextCompose(
                            option = HongButtonTextBuilder()
                                .height(50)
                                .state(
                                    if (item.widgetType.allowPlayground) HongState.ENABLED else HongState.DISABLED
                                )
                                .margin(
                                    HongSpacingInfo(
                                        right = 5f
                                    )
                                )
                                .text("Playground")
                                .textTypo(HongTypo.BODY_14_B)
                                .textColor(HongColor.MAIN_ORANGE_100)
                                .backgroundColor(HongColor.WHITE_100.hex)
                                .radius(
                                    HongRadiusInfo(all = 14)
                                )
                                .border(
                                    HongBorderInfo(
                                        color = HongColor.MAIN_ORANGE_100.hex,
                                        width = 1
                                    )
                                )
                                .onClick {
                                    Handler(Looper.getMainLooper()).postDelayed({
                                        Intent(activity, PlaygroundActivity::class.java).apply {
                                            putExtra(SampleConst.WIDGET_TYPE, item.widgetType.value)
                                            activity.startActivity(this)
                                        }
                                    }, 200)
                                }
                                .applyOption(),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        HongButtonTextCompose(
                            option = HongButtonTextBuilder()
                                .height(50)
                                .text("샘플")
                                .textTypo(HongTypo.BODY_14_B)
                                .textColor(HongColor.MAIN_ORANGE_100)
                                .backgroundColor(HongColor.WHITE_100.hex)
                                .radius(
                                    HongRadiusInfo(all = 14)
                                )
                                .border(
                                    HongBorderInfo(
                                        color = HongColor.MAIN_ORANGE_100.hex,
                                        width = 1
                                    )
                                )
                                .onClick {
                                    when (item.widgetType) {
                                        HongWidgetType.TEXT -> {
                                            Intent(activity, SampleTextActivity::class.java).apply {
                                                putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.TEXT.value)
                                                activity.startActivity(this)
                                            }
                                        }
                                        HongWidgetType.TEXT_CHECK -> {
                                            Intent(activity, SampleCheckTextActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_CHECK.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }
                                        HongWidgetType.TEXT_UNIT -> {
                                            Intent(activity, SampleTextUnitActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_UNIT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }
                                        HongWidgetType.TEXT_UP_DOWN -> {
                                            Intent(activity, SampleTextUpDownActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_UP_DOWN.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }
                                        HongWidgetType.TEXT_COUNT -> {
                                            Intent(activity, SampleTextCountActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_COUNT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.IMAGE -> {
                                            Intent(activity, SampleImageActivity::class.java).apply {
                                                putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.IMAGE.value)
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TEXT_FILED -> {
                                            Intent(activity, SampleTextFieldActivity::class.java).apply {
                                                putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.TEXT_FILED.value)
                                                activity.startActivity(this)
                                            }
                                        }


                                        HongWidgetType.TEXT_FIELD_UNDERLINE -> {
                                            Intent(activity, SampleTextFieldUnderlineActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_FIELD_UNDERLINE.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TEXT_FIELD_TIMER -> {
                                            Intent(activity, SampleTextFieldTimerActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_FIELD_TIMER.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TEXT_FIELD_NUMBER -> {
                                            Intent(activity, SampleTextFieldNumberActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_FIELD_NUMBER.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TEXT_FIELD_BORDER -> {
                                            Intent(activity, SampleTextFieldBorderActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_FIELD_BORDER.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TEXT_FIELD_BORDER_SELECT -> {
                                            Intent(activity, SampleTextFieldBorderSelectActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_FIELD_BORDER_SELECT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }



                                        HongWidgetType.BUTTON_TEXT -> {
                                            Intent(activity, SampleTextButtonActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.BUTTON_TEXT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.BUTTON_SELECT -> {
                                            Intent(activity, SampleSelectButtonActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.BUTTON_SELECT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.BUTTON_ICON -> {
                                            Intent(activity, SampleButtonIconActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.BUTTON_ICON.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.HEADER_CLOSE -> {
                                            Intent(activity, SampleCloseHeaderActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.HEADER_CLOSE.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.CALENDAR -> {
                                            OptionPickerDialog(
                                                activity,
                                                "샘플 선택",
                                                listOf(
                                                    "초기 날짜 미선택(XML)",
                                                    "초기 날짜 선택(XML)",
                                                    "초기 날짜 미선택(Compose)",
                                                    "초기 날짜 선택(Compose)"
                                                ),
                                                0
                                            ) { selectType, _ ->
                                                Handler(Looper.getMainLooper()).postDelayed({
                                                    when (selectType) {
                                                        "초기 날짜 미선택(XML)" -> {
                                                            Intent(
                                                                activity,
                                                                SampleCalendarActivity::class.java
                                                            ).apply {
                                                                putExtra(
                                                                    SampleConst.INITIAL_DATE,
                                                                    false
                                                                )
                                                                activity.startActivity(this)
                                                            }
                                                        }
                                                        "초기 날짜 선택(XML)" -> {
                                                            Intent(
                                                                activity,
                                                                SampleCalendarActivity::class.java
                                                            ).apply {
                                                                putExtra(
                                                                    SampleConst.INITIAL_DATE,
                                                                    true
                                                                )
                                                                activity.startActivity(this)
                                                            }
                                                        }

                                                        "초기 날짜 미선택(Compose)" -> {
                                                            Intent(activity, SampleCalendarComposeActivity::class.java).apply {
                                                                putExtra(
                                                                    SampleConst.INITIAL_DATE,
                                                                    false
                                                                )
                                                                putExtra(
                                                                    SampleConst.WIDGET_TYPE,
                                                                    HongWidgetType.CALENDAR.value
                                                                )
                                                                activity.startActivity(this)
                                                            }
                                                        }

                                                        "초기 날짜 선택(Compose)" -> {
                                                            Intent(activity, SampleCalendarComposeActivity::class.java).apply {
                                                                putExtra(
                                                                    SampleConst.INITIAL_DATE,
                                                                    true
                                                                )
                                                                putExtra(
                                                                    SampleConst.WIDGET_TYPE,
                                                                    HongWidgetType.CALENDAR.value
                                                                )
                                                                activity.startActivity(this)
                                                            }
                                                        }
                                                        else -> {}
                                                    }
                                                }, 200)
                                            }.show()
                                        }

                                        HongWidgetType.HORIZONTAL_PAGER -> {
                                            Intent(activity, SampleHorizontalPagerActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.HORIZONTAL_PAGER.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TEXT_BADGE -> {
                                            Intent(activity, SampleTextBadgeActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_BADGE.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TAB_SCROLL -> {
                                            Intent(activity, SampleTabScrollActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TAB_SCROLL.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TAB_SEGMENT -> {
                                            Intent(activity, SampleTabSegmentActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TAB_SEGMENT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TAB_FLOW -> {
                                            Intent(activity, SampleTabFlowActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TAB_FLOW.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.CAPTURE_SHARE -> {
                                            Intent(activity, SampleCaptureShareActivity::class.java).apply {
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.DYNAMIC_ISLAND -> {
                                            Intent(activity, SampleDynamicIslandActivity::class.java).apply {
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.VIDEO_POPUP -> {
                                            OptionPickerDialog(
                                                activity,
                                                "샘플 선택",
                                                sampleTypeList,
                                                0
                                            ) { selectType, _ ->
                                                Handler(Looper.getMainLooper()).postDelayed({
                                                    when (selectType.toType()) {
                                                        SampleType.XML -> {
                                                            Intent(
                                                                activity,
                                                                SampleVideoPopupActivity::class.java
                                                            ).apply {
                                                                putExtra(
                                                                    SampleConst.WIDGET_TYPE,
                                                                    HongWidgetType.VIDEO_POPUP.value
                                                                )
                                                                activity.startActivity(this)
                                                            }
                                                        }

                                                        SampleType.OPTION_BUILDER -> {
                                                            Intent(
                                                                activity,
                                                                SampleVideoPopupBuilderActivity::class.java
                                                            ).apply {
                                                                putExtra(
                                                                    SampleConst.WIDGET_TYPE,
                                                                    HongWidgetType.VIDEO_POPUP.value
                                                                )
                                                                activity.startActivity(this)
                                                            }
                                                        }

                                                        SampleType.COMPOSE -> {
                                                            Intent(
                                                                activity,
                                                                SampleVideoPopupComposeActivity::class.java
                                                            ).apply {
                                                                putExtra(
                                                                    SampleConst.WIDGET_TYPE,
                                                                    HongWidgetType.VIDEO_POPUP.value
                                                                )
                                                                activity.startActivity(this)
                                                            }

                                                        }

                                                        else -> {}
                                                    }
                                                }, 200)
                                            }.show()
                                        }

                                        HongWidgetType.VIDEO_PLAYER -> {
                                            Intent(activity, SampleVideoPlayerActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.VIDEO_PLAYER.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }


                                        HongWidgetType.CHECKBOX -> {
                                            Intent(activity, SampleCheckboxActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.CHECKBOX.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.SWITCH -> {
                                            Intent(activity, SampleSwitchActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.SWITCH.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.LABEL-> {
                                            Intent(activity, SampleLabelActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.LABEL.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.LABEL_INPUT -> {
                                            Intent(activity, SampleLabelInputActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.LABEL_INPUT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.LABEL_SELECT_INPUT -> {
                                            Intent(activity, SampleLabelSelectInputActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.LABEL_SELECT_INPUT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.LABEL_SWITCH -> {
                                            Intent(activity, SampleLabelSwitchActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.LABEL_SWITCH.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.LABEL_CHECKBOX -> {
                                            Intent(activity, SampleLabelCheckboxActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.LABEL_CHECKBOX.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.PICKER -> {
                                            OptionPickerDialog(
                                                activity,
                                                "샘플 선택",
                                                listOf(
                                                    "view",
                                                    "compose",
                                                ),
                                                0
                                            ) { selectType, _ ->
                                                Handler(Looper.getMainLooper()).postDelayed({
                                                    when (selectType) {
                                                        "view" -> {
                                                            Intent(activity, SampleCalendarComposeActivity::class.java).apply {
                                                                putExtra(
                                                                    SampleConst.INITIAL_DATE,
                                                                    false
                                                                )
                                                                putExtra(
                                                                    SampleConst.WIDGET_TYPE,
                                                                    HongWidgetType.CALENDAR.value
                                                                )
                                                                activity.startActivity(this)
                                                            }
                                                        }

                                                        "compose" -> {
                                                            Intent(activity, SamplePickerComposeActivity::class.java).apply {
                                                                activity.startActivity(this)
                                                            }
                                                        }
                                                        else -> {}
                                                    }
                                                }, 200)
                                            }.show()
                                        }

                                        HongWidgetType.BOTTOM_SHEET_SELECT -> {
                                            Intent(activity, SampleBottomSheetSelectActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.BOTTOM_SHEET_SELECT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.BOTTOM_SHEET_SWIPE -> {
                                            Intent(activity, SampleBottomSheetSwipeActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.BOTTOM_SHEET_SWIPE.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.GRAPH_LINE -> {
                                            Intent(activity, SampleGraphLineActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.GRAPH_LINE.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.GRAPH_BAR -> {
                                            Intent(activity, SampleGraphBarActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.GRAPH_BAR.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.ICON -> {
                                            Intent(activity, SampleIconActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.ICON.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.DRAG_AND_DROP -> {

                                        }

                                        else -> {
                                            Intent(activity, OptionPickerActivity::class.java).apply {
                                                activity.startActivity(this)
                                            }
                                        }
                                    }
                                }
                                .applyOption(),
                        )
                    }
                }
            }
        }
    }
}