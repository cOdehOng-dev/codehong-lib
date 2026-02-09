package com.codehong.lib.sample

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
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
import com.codehong.lib.sample.draganddrop.SampleDragAndDropActivity
import com.codehong.lib.sample.dynamicisland.SampleDynamicIslandActivity
import com.codehong.lib.sample.graph.SampleGraphBarActivity
import com.codehong.lib.sample.graph.SampleGraphLineActivity
import com.codehong.lib.sample.header.close.SampleHeaderCloseActivity
import com.codehong.lib.sample.header.icon.SampleHeaderIconActivity
import com.codehong.lib.sample.icon.SampleIconActivity
import com.codehong.lib.sample.image.SampleImageActivity
import com.codehong.lib.sample.label.SampleLabelActivity
import com.codehong.lib.sample.label.checkbox.SampleLabelCheckboxActivity
import com.codehong.lib.sample.label.input.SampleLabelInputActivity
import com.codehong.lib.sample.label.select.SampleLabelSelectInputActivity
import com.codehong.lib.sample.label.toggle.SampleLabelSwitchActivity
import com.codehong.lib.sample.layout.fade.SampleScrollFadeLayoutActivity
import com.codehong.lib.sample.liquidglass.SampleLiquidGlassActivity
import com.codehong.lib.sample.liquidglass.tabbar.SampleLiquidGlassTabBarActivity
import com.codehong.lib.sample.pager.SampleHorizontalPagerActivity
import com.codehong.lib.sample.picker.OptionPickerActivity
import com.codehong.lib.sample.picker.SamplePickerComposeActivity
import com.codehong.lib.sample.player.SampleVideoPlayerActivity
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.progress.SampleProgressActivity
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
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import com.codehong.library.widget.util.picker.OptionPickerDialog


private data class WidgetCategory(
    val title: String,
    val widgets: List<HongWidgetType>
)

private val widgetCategories = listOf(
    WidgetCategory(
        title = "Text",
        widgets = listOf(
            HongWidgetType.TEXT,
            HongWidgetType.TEXT_CHECK,
            HongWidgetType.TEXT_UP_DOWN,
            HongWidgetType.TEXT_UNIT,
            HongWidgetType.TEXT_BADGE,
            HongWidgetType.TEXT_COUNT
        )
    ),
    WidgetCategory(
        title = "TextField",
        widgets = listOf(
            HongWidgetType.TEXT_FILED,
            HongWidgetType.TEXT_FIELD_UNDERLINE,
            HongWidgetType.TEXT_FIELD_TIMER,
            HongWidgetType.TEXT_FIELD_NUMBER,
            HongWidgetType.TEXT_FIELD_BORDER,
            HongWidgetType.TEXT_FIELD_BORDER_SELECT
        )
    ),
    WidgetCategory(
        title = "Button",
        widgets = listOf(
            HongWidgetType.BUTTON_TEXT,
            HongWidgetType.BUTTON_SELECT,
            HongWidgetType.BUTTON_ICON
        )
    ),
    WidgetCategory(
        title = "Tab",
        widgets = listOf(
            HongWidgetType.TAB_SCROLL,
            HongWidgetType.TAB_SEGMENT,
            HongWidgetType.TAB_FLOW
        )
    ),
    WidgetCategory(
        title = "Label",
        widgets = listOf(
            HongWidgetType.LABEL,
            HongWidgetType.LABEL_INPUT,
            HongWidgetType.LABEL_SELECT_INPUT,
            HongWidgetType.LABEL_SWITCH,
            HongWidgetType.LABEL_CHECKBOX
        )
    ),
    WidgetCategory(
        title = "Graph",
        widgets = listOf(
            HongWidgetType.GRAPH_LINE,
            HongWidgetType.GRAPH_BAR
        )
    ),
    WidgetCategory(
        title = "Bottom Sheet",
        widgets = listOf(
            HongWidgetType.BOTTOM_SHEET_SELECT,
            HongWidgetType.BOTTOM_SHEET_SWIPE
        )
    ),
    WidgetCategory(
        title = "Media",
        widgets = listOf(
            HongWidgetType.IMAGE,
            HongWidgetType.VIDEO_POPUP,
            HongWidgetType.VIDEO_PLAYER
        )
    ),
    WidgetCategory(
        title = "Input",
        widgets = listOf(
            HongWidgetType.CHECKBOX,
            HongWidgetType.SWITCH
        )
    ),
    WidgetCategory(
        title = "Header",
        widgets = listOf(
            HongWidgetType.HEADER_CLOSE,
            HongWidgetType.HEADER_ICON
        )
    ),
    WidgetCategory(
        title = "Etc",
        widgets = listOf(
            HongWidgetType.ICON,
            HongWidgetType.CALENDAR,
            HongWidgetType.HORIZONTAL_PAGER,
            HongWidgetType.PICKER,
            HongWidgetType.CAPTURE_SHARE,
            HongWidgetType.DYNAMIC_ISLAND,
            HongWidgetType.GRID_DRAG_AND_DROP,
            HongWidgetType.SCROLL_FADE_ANIM_LAYOUT,
            HongWidgetType.LIQUID_GLASS,
            HongWidgetType.PROGRESS
        )
    )
)

@Composable
fun MainScreen() {
    val context = LocalContext.current
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
            widgetCategories.forEach { category ->
                item {
                    CategoryHeader(title = category.title)
                }
                items(
                    category.widgets.map { widgetType ->
                        SampleTestItem(
                            title = widgetType.value,
                            widgetType = widgetType
                        )
                    }
                ) { item ->
                    SampleListItem(
                        context = context,
                        item = item,
                        sampleTypeList = sampleTypeList
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 8.dp)
    ) {
        HongTextCompose(
            option = HongTextBuilder()
                .text(title)
                .typography(HongTypo.BODY_16_B)
                .color(HongColor.MAIN_ORANGE_100)
                .applyOption()
        )
    }
}

@Composable
private fun SampleListItem(
    context: Context,
    item: SampleTestItem,
    sampleTypeList: List<String>
) {
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
            PlaygroundButton(context = context, item = item)
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            SampleButton(
                context = context,
                item = item,
                sampleTypeList = sampleTypeList
            )
        }
    }
}

@Composable
private fun PlaygroundButton(
    context: Context,
    item: SampleTestItem
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
                    Intent(context, PlaygroundActivity::class.java).apply {
                        putExtra(SampleConst.WIDGET_TYPE, item.widgetType.value)
                        context.startActivity(this)
                    }
                }, 200)
            }
            .applyOption(),
    )
}

@Composable
private fun SampleButton(
    context: Context,
    item: SampleTestItem,
    sampleTypeList: List<String>
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
                handleSampleClick(context, item, sampleTypeList)
            }
            .applyOption(),
    )
}

private fun handleSampleClick(
    context: Context,
    item: SampleTestItem,
    sampleTypeList: List<String>
) {
    when (item.widgetType) {
        HongWidgetType.BOTTOM_SHEET_SELECT -> {
            context.startSampleActivity(SampleBottomSheetSelectActivity::class.java, item.widgetType)
        }

        HongWidgetType.BOTTOM_SHEET_SWIPE -> {
            context.startSampleActivity(SampleBottomSheetSwipeActivity::class.java, item.widgetType)
        }

        HongWidgetType.BUTTON_ICON -> {
            context.startSampleActivity(SampleButtonIconActivity::class.java, item.widgetType)
        }

        HongWidgetType.BUTTON_SELECT -> {
            context.startSampleActivity(SampleSelectButtonActivity::class.java, item.widgetType)
        }

        HongWidgetType.BUTTON_TEXT -> {
            context.startSampleActivity(SampleTextButtonActivity::class.java, item.widgetType)
        }

        HongWidgetType.CALENDAR -> {
            (context as? ComponentActivity)?.let { showCalendarPicker(it) }
        }

        HongWidgetType.CAPTURE_SHARE -> {
            context.startSampleActivity(SampleCaptureShareActivity::class.java, item.widgetType)
        }

        HongWidgetType.CHECKBOX -> {
            context.startSampleActivity(SampleCheckboxActivity::class.java, item.widgetType)
        }

        HongWidgetType.DYNAMIC_ISLAND -> {
            context.startSampleActivity(SampleDynamicIslandActivity::class.java, item.widgetType)
        }

        HongWidgetType.GRAPH_BAR -> {
            context.startSampleActivity(SampleGraphBarActivity::class.java, item.widgetType)
        }

        HongWidgetType.GRAPH_LINE -> {
            context.startSampleActivity(SampleGraphLineActivity::class.java, item.widgetType)
        }

        HongWidgetType.GRID_DRAG_AND_DROP -> {
            context.startSampleActivity(SampleDragAndDropActivity::class.java, item.widgetType)
        }

        HongWidgetType.HEADER_CLOSE -> context.startSampleActivity(SampleHeaderCloseActivity::class.java, item.widgetType)
        HongWidgetType.HEADER_ICON -> context.startSampleActivity(SampleHeaderIconActivity::class.java, item.widgetType)


        HongWidgetType.HORIZONTAL_PAGER -> {
            context.startSampleActivity(SampleHorizontalPagerActivity::class.java, item.widgetType)
        }

        HongWidgetType.ICON -> {
            context.startSampleActivity(SampleIconActivity::class.java, item.widgetType)
        }

        HongWidgetType.IMAGE -> {
            context.startSampleActivity(SampleImageActivity::class.java, item.widgetType)
        }

        HongWidgetType.LABEL -> {
            context.startSampleActivity(SampleLabelActivity::class.java, HongWidgetType.LABEL)
        }

        HongWidgetType.LABEL_CHECKBOX -> {
            context.startSampleActivity(SampleLabelCheckboxActivity::class.java, HongWidgetType.LABEL_CHECKBOX)
        }

        HongWidgetType.LABEL_INPUT -> {
            context.startSampleActivity(SampleLabelInputActivity::class.java, HongWidgetType.LABEL_INPUT)
        }

        HongWidgetType.LABEL_SELECT_INPUT -> {
            context.startSampleActivity(SampleLabelSelectInputActivity::class.java, HongWidgetType.LABEL_SELECT_INPUT)
        }

        HongWidgetType.LABEL_SWITCH -> {
            context.startSampleActivity(SampleLabelSwitchActivity::class.java, HongWidgetType.LABEL_SWITCH)
        }

        HongWidgetType.LIQUID_GLASS -> {
            context.startSampleActivity(SampleLiquidGlassActivity::class.java, HongWidgetType.LIQUID_GLASS)
        }
        HongWidgetType.LIQUID_GLASS_TAB_BAR -> context.startSampleActivity(SampleLiquidGlassTabBarActivity::class.java, item.widgetType)

        HongWidgetType.PICKER -> {
            (context as? ComponentActivity)?.let { showPickerDialog(it) }
        }

        HongWidgetType.PROGRESS -> {
            context.startSampleActivity(SampleProgressActivity::class.java, HongWidgetType.PROGRESS)
        }

        HongWidgetType.SCROLL_FADE_ANIM_LAYOUT -> {
            context.startSampleActivity(SampleScrollFadeLayoutActivity::class.java, HongWidgetType.SCROLL_FADE_ANIM_LAYOUT)
        }

        HongWidgetType.SWITCH -> {
            context.startSampleActivity(SampleSwitchActivity::class.java, HongWidgetType.SWITCH)
        }

        HongWidgetType.TAB_FLOW -> {
            context.startSampleActivity(SampleTabFlowActivity::class.java, HongWidgetType.TAB_FLOW)
        }

        HongWidgetType.TAB_SCROLL -> {
            context.startSampleActivity(SampleTabScrollActivity::class.java, HongWidgetType.TAB_SCROLL)
        }

        HongWidgetType.TAB_SEGMENT -> {
            context.startSampleActivity(SampleTabSegmentActivity::class.java, HongWidgetType.TAB_SEGMENT)
        }

        HongWidgetType.TEXT -> {
            context.startSampleActivity(SampleTextActivity::class.java, HongWidgetType.TEXT)
        }

        HongWidgetType.TEXT_BADGE -> {
            context.startSampleActivity(SampleTextBadgeActivity::class.java, HongWidgetType.TEXT_BADGE)
        }

        HongWidgetType.TEXT_CHECK -> {
            context.startSampleActivity(SampleCheckTextActivity::class.java, HongWidgetType.TEXT_CHECK)
        }

        HongWidgetType.TEXT_COUNT -> {
            context.startSampleActivity(SampleTextCountActivity::class.java, HongWidgetType.TEXT_COUNT)
        }

        HongWidgetType.TEXT_FIELD_BORDER -> {
            context.startSampleActivity(SampleTextFieldBorderActivity::class.java, HongWidgetType.TEXT_FIELD_BORDER)
        }

        HongWidgetType.TEXT_FIELD_BORDER_SELECT -> {
            context.startSampleActivity(SampleTextFieldBorderSelectActivity::class.java, HongWidgetType.TEXT_FIELD_BORDER_SELECT)
        }

        HongWidgetType.TEXT_FIELD_NUMBER -> {
            context.startSampleActivity(SampleTextFieldNumberActivity::class.java, HongWidgetType.TEXT_FIELD_NUMBER)
        }

        HongWidgetType.TEXT_FIELD_TIMER -> {
            context.startSampleActivity(SampleTextFieldTimerActivity::class.java, HongWidgetType.TEXT_FIELD_TIMER)
        }

        HongWidgetType.TEXT_FIELD_UNDERLINE -> {
            context.startSampleActivity(SampleTextFieldUnderlineActivity::class.java, HongWidgetType.TEXT_FIELD_UNDERLINE)
        }

        HongWidgetType.TEXT_FILED -> {
            context.startSampleActivity(SampleTextFieldActivity::class.java, HongWidgetType.TEXT_FILED)
        }

        HongWidgetType.TEXT_UNIT -> {
            context.startSampleActivity(SampleTextUnitActivity::class.java, HongWidgetType.TEXT_UNIT)
        }

        HongWidgetType.TEXT_UP_DOWN -> {
            context.startSampleActivity(SampleTextUpDownActivity::class.java, HongWidgetType.TEXT_UP_DOWN)
        }

        HongWidgetType.VIDEO_PLAYER -> {
            context.startSampleActivity(SampleVideoPlayerActivity::class.java, HongWidgetType.VIDEO_PLAYER)
        }

        HongWidgetType.VIDEO_POPUP -> {
            (context as? ComponentActivity)?.let { showVideoPopupPicker(it, sampleTypeList) }
        }

        else -> {
            context.startSampleActivity(OptionPickerActivity::class.java)
        }
    }
}

private fun showCalendarPicker(activity: ComponentActivity) {
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
                    Intent(activity, SampleCalendarActivity::class.java).apply {
                        putExtra(SampleConst.INITIAL_DATE, false)
                        activity.startActivity(this)
                    }
                }
                "초기 날짜 선택(XML)" -> {
                    Intent(activity, SampleCalendarActivity::class.java).apply {
                        putExtra(SampleConst.INITIAL_DATE, true)
                        activity.startActivity(this)
                    }
                }
                "초기 날짜 미선택(Compose)" -> {
                    Intent(activity, SampleCalendarComposeActivity::class.java).apply {
                        putExtra(SampleConst.INITIAL_DATE, false)
                        putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.CALENDAR.value)
                        activity.startActivity(this)
                    }
                }
                "초기 날짜 선택(Compose)" -> {
                    Intent(activity, SampleCalendarComposeActivity::class.java).apply {
                        putExtra(SampleConst.INITIAL_DATE, true)
                        putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.CALENDAR.value)
                        activity.startActivity(this)
                    }
                }
            }
        }, 200)
    }.show()
}

private fun showPickerDialog(activity: ComponentActivity) {
    OptionPickerDialog(
        activity,
        "샘플 선택",
        listOf("view", "compose"),
        0
    ) { selectType, _ ->
        Handler(Looper.getMainLooper()).postDelayed({
            when (selectType) {
                "view" -> {
                    Intent(activity, SampleCalendarComposeActivity::class.java).apply {
                        putExtra(SampleConst.INITIAL_DATE, false)
                        putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.CALENDAR.value)
                        activity.startActivity(this)
                    }
                }
                "compose" -> {
                    Intent(activity, SamplePickerComposeActivity::class.java).apply {
                        activity.startActivity(this)
                    }
                }
            }
        }, 200)
    }.show()
}

private fun showVideoPopupPicker(
    activity: ComponentActivity,
    sampleTypeList: List<String>
) {
    OptionPickerDialog(
        activity,
        "샘플 선택",
        sampleTypeList,
        0
    ) { selectType, _ ->
        Handler(Looper.getMainLooper()).postDelayed({
            when (selectType.toType()) {
                SampleType.XML -> {
                    Intent(activity, SampleVideoPopupActivity::class.java).apply {
                        putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.VIDEO_POPUP.value)
                        activity.startActivity(this)
                    }
                }
                SampleType.OPTION_BUILDER -> {
                    Intent(activity, SampleVideoPopupBuilderActivity::class.java).apply {
                        putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.VIDEO_POPUP.value)
                        activity.startActivity(this)
                    }
                }
                SampleType.COMPOSE -> {
                    Intent(activity, SampleVideoPopupComposeActivity::class.java).apply {
                        putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.VIDEO_POPUP.value)
                        activity.startActivity(this)
                    }
                }
                else -> {}
            }
        }, 200)
    }.show()
}
