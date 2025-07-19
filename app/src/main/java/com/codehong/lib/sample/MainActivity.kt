package com.codehong.lib.sample

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.codehong.lib.sample.badge.SampleBadgeTextActivity
import com.codehong.lib.sample.button.select.SampleSelectButtonActivity
import com.codehong.lib.sample.button.text.SampleTextButtonActivity
import com.codehong.lib.sample.calendar.SampleCalendarActivity
import com.codehong.lib.sample.calendar.SampleCalendarComposeActivity
import com.codehong.lib.sample.captureshare.SampleCaptureShareActivity
import com.codehong.lib.sample.checkbox.SampleCheckboxActivity
import com.codehong.lib.sample.closeheader.SampleCloseHeaderActivity
import com.codehong.lib.sample.dynamicisland.SampleDynamicIslandActivity
import com.codehong.lib.sample.image.SampleImageActivity
import com.codehong.lib.sample.label.SampleLabelActivity
import com.codehong.lib.sample.label.checkbox.SampleLabelCheckboxActivity
import com.codehong.lib.sample.label.input.SampleLabelInputActivity
import com.codehong.lib.sample.label.select.SampleLabelSelectInputActivity
import com.codehong.lib.sample.label.toggle.SampleLabelSwitchActivity
import com.codehong.lib.sample.pager.SampleHorizontalPagerActivity
import com.codehong.lib.sample.picker.OptionPickerActivity
import com.codehong.lib.sample.player.SampleVideoPlayerActivity
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.tab.SampleScrollTabActivity
import com.codehong.lib.sample.text.SampleTextActivity
import com.codehong.lib.sample.textfield.SampleTextFieldActivity
import com.codehong.lib.sample.textfield.timer.SampleTimerTextFieldActivity
import com.codehong.lib.sample.textfield.underline.SampleUnderlineTextFieldActivity
import com.codehong.lib.sample.toggleswitch.SampleSwitchActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupBuilderActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupComposeActivity
import com.codehong.library.widget.R
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonCompose
import com.codehong.library.widget.dynamicisland.DynamicIslandInfo
import com.codehong.library.widget.dynamicisland.DynamicIslandManager
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.picker.OptionPickerDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme(this)
        }
        checkDynamicIsland()
    }

    private fun checkDynamicIsland() {
        if (DynamicIslandManager.isGranted(this)) {
            startDynamicIsland()
        }
    }

    private fun startDynamicIsland() {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA)
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        val startFutureTime = Date(System.currentTimeMillis() + 1000 * 60 * 60) // 1시간 후
        val start = dateFormat.format(startFutureTime)
        val endFutureTime = Date(System.currentTimeMillis() + 1000 * 60 * 120) // 2시간 후
        val end = dateFormat.format(endFutureTime)
        val ticketInfo = DynamicIslandInfo(
            thumbnailUrl = "https://images.unsplash.com/photo-1644645233471-d2cbe70e3871?q=80&w=2187&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            fromCity = "FROM   TOKYO",
            toCity = "TO       SEOUL/INCHEON",
            startDate = start,
            endDate = end
        )
        if (DynamicIslandManager.isRunning()) {
            DynamicIslandManager.reset(ticketInfo)
        } else {
            DynamicIslandManager.schedule(this, ticketInfo)
        }
    }
}

@Composable
fun SampleTheme(
    activity: ComponentActivity
) {
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
                        HongTextButtonCompose(
                            option = HongTextButtonBuilder()
                                .height(50)
                                .state(
                                    if (item.widgetType.allowPlayground) HongState.ENABLED else HongState.DISABLED
                                )
                                .margin(
                                    HongSpacingInfo(
                                        right = 5f
                                    )
                                )
                                .textOption(
                                    HongTextBuilder()
                                        .text("Playground")
                                        .typography(HongTypo.BODY_14_B)
                                        .color(HongColor.MAIN_ORANGE_100)
                                        .textAlign(HongTextAlign.CENTER)
                                        .applyOption()
                                )
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
                        HongTextButtonCompose(
                            option = HongTextButtonBuilder()
                                .height(50)
                                .textOption(
                                    HongTextBuilder()
                                        .text("샘플")
                                        .typography(HongTypo.BODY_14_B)
                                        .color(HongColor.MAIN_ORANGE_100)
                                        .textAlign(HongTextAlign.CENTER)
                                        .applyOption()
                                )
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


                                        HongWidgetType.UNDERLINE_TEXT_FIELD -> {
                                            Intent(activity, SampleUnderlineTextFieldActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.UNDERLINE_TEXT_FIELD.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TIMER_TEXT_FIELD -> {
                                            Intent(activity, SampleTimerTextFieldActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TIMER_TEXT_FIELD.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.TEXT_BUTTON -> {
                                            Intent(activity, SampleTextButtonActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.TEXT_BUTTON.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.SELECT_BUTTON -> {
                                            Intent(activity, SampleSelectButtonActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.SELECT_BUTTON.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.CLOSE_HEADER -> {
                                            Intent(activity, SampleCloseHeaderActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.CLOSE_HEADER.value
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

                                        HongWidgetType.BADGE_TEXT -> {
                                            Intent(activity, SampleBadgeTextActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.BADGE_TEXT.value
                                                )
                                                activity.startActivity(this)
                                            }
                                        }

                                        HongWidgetType.SCROLL_TAB -> {
                                            Intent(activity, SampleScrollTabActivity::class.java).apply {
                                                putExtra(
                                                    SampleConst.WIDGET_TYPE,
                                                    HongWidgetType.SCROLL_TAB.value
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

private val sampleTypeList = listOf(
    SampleType.XML.value,
    SampleType.OPTION_BUILDER.value,
    SampleType.COMPOSE.value
)

enum class SampleType(
    val value: String
) {
    XML("xml"),
    OPTION_BUILDER("option builder"),
    COMPOSE("compose");

    companion object {
        fun String.toType(): SampleType? {
            return entries.find { it.value == this }
        }
    }
}




