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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.SampleType.Companion.toType
import com.codehong.lib.sample.button.SampleTextButtonActivity
import com.codehong.lib.sample.calendar.SampleCalendarActivity1
import com.codehong.lib.sample.captureshare.SampleCaptureShareActivity
import com.codehong.lib.sample.dynamicisland.SampleDynamicIslandActivity
import com.codehong.lib.sample.header.SampleHeaderActivity
import com.codehong.lib.sample.image.SampleImageActivity
import com.codehong.lib.sample.layout.slide.SampleSlideLayoutActivity
import com.codehong.lib.sample.pager.SampleHorizontalPagerActivity
import com.codehong.lib.sample.picker.OptionPickerActivity
import com.codehong.lib.sample.player.SampleVideoPlayerActivity
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.lib.sample.tab.SampleScrollTabActivity
import com.codehong.lib.sample.text.SampleBadgeTextActivity
import com.codehong.lib.sample.text.SampleTextActivity
import com.codehong.lib.sample.textfield.SampleTextFieldActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupBuilderActivity
import com.codehong.lib.sample.videopopup.SampleVideoPopupComposeActivity
import com.codehong.library.widget.R
import com.codehong.library.widget.button.HongTextButton
import com.codehong.library.widget.dynamicisland.DynamicIslandInfo
import com.codehong.library.widget.dynamicisland.DynamicIslandManager
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongText
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
                        colorResource(id = HongColor.MAIN_PURPLE.colorResId)
                    ),
                contentAlignment = Alignment.Center
            ) {
                HongText(
                    text = "라이브러리",
                    fontWeight = FontWeight.W700,
                    size = 30,
                    color = HongComposeColor(
                        resId = R.color.honglib_color_ffffff
                    )
                )
            }
        },
        bottomBar = {
//            val widgetList = HongWidgetType.entries.map { it.value }.toMutableList()
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//                    .hongBorder(
//                        borderWidth = 1,
//                        borderColor = HongComposeColor(
//                            type = HongColor.MAIN_PURPLE
//                        ),
//                        backgroundColor = HongComposeColor(
//                            type = HongColor.WHITE_100
//                        )
//                    )
//                    .clickable {
//                        OptionPickerDialog(
//                            activity,
//                            "위젯 선택",
//                            widgetList,
//                            0
//                        ) { selectComponent, _ ->
//                            if (selectComponent == HongWidgetType.NO_VALUE.value) {
//                                return@OptionPickerDialog
//                            }
//
//                            Handler(Looper.getMainLooper()).postDelayed({
//                                Intent(activity, PlaygroundActivity::class.java).apply {
//                                    putExtra("componentType", selectComponent)
//                                    activity.startActivity(this)
//                                }
//                            }, 200)
//                        }.show()
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//            }
        }
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
                                    compose = item
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
                        .background(colorResource(R.color.honglib_color_ffffff))
                ) {
                    HongText(
                        modifier = Modifier
                            .weight(2f)
                            .align(Alignment.CenterVertically),
                        text = item.title,
                        typo = HongTypo.BODY_14_B,
                        colorType = HongColor.BLACK_100
                    )
                    Spacer(
                        modifier = Modifier
                            .width(5.dp)
                            .height(1.dp)
                    )
                    if (item.compose.allowPlayground) {
                        HongTextButton(
                            modifier = Modifier
                                .weight(1.5f),
                            buttonText = "Playground",
                            buttonTextTypoType = HongTypo.BODY_14_B,
                            allRadius = 14,
                            buttonTextColor = HongComposeColor(
                                type = HongColor.MAIN_PURPLE
                            ),
                            buttonBackgroundColor = HongComposeColor(
                                type = HongColor.WHITE_100
                            ),
                            borderColor = HongComposeColor(
                                type = HongColor.MAIN_PURPLE
                            ),
                            borderWidth = 1,
                            verticalPadding = 15
                        ) {
                            Handler(Looper.getMainLooper()).postDelayed({
                                Intent(activity, PlaygroundActivity::class.java).apply {
                                    putExtra("componentType", item.compose.value)
                                    activity.startActivity(this)
                                }
                            }, 200)
                        }
                        Spacer(
                            modifier = Modifier
                                .width(5.dp)
                                .height(1.dp)
                        )
                    }
                    HongTextButton(
                        modifier = Modifier
                            .weight(1f),
                        buttonText = "샘플",
                        buttonTextTypoType = HongTypo.BODY_14_B,
                        allRadius = 14,
                        buttonTextColor = HongComposeColor(
                            type = HongColor.MAIN_PURPLE
                        ),
                        buttonBackgroundColor = HongComposeColor(
                            type = HongColor.WHITE_100
                        ),
                        borderColor = HongComposeColor(
                            type = HongColor.MAIN_PURPLE
                        ),
                        borderWidth = 1,
                        verticalPadding = 15,
                    )  {
                        when (item.compose) {
                            HongWidgetType.TEXT -> {
                                Intent(activity, SampleTextActivity::class.java).apply {
                                    putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.TEXT.value)
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.IMAGE -> {
                                Intent(activity, SampleImageActivity::class.java).apply {
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.TEXT_FILED -> {
                                Intent(activity, SampleTextFieldActivity::class.java).apply {
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.TEXT_BUTTON -> {
                                Intent(activity, SampleTextButtonActivity::class.java).apply {
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.SLIDE_LAYOUT -> {
                                Intent(activity, SampleSlideLayoutActivity::class.java).apply {
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.HEADER -> {
                                Intent(activity, SampleHeaderActivity::class.java).apply {
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.CALENDAR_1 -> {
                                Intent(activity, SampleCalendarActivity1::class.java).apply {
                                    putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.CALENDAR_1.value)
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.HORIZONTAL_PAGER -> {
                                Intent(activity, SampleHorizontalPagerActivity::class.java).apply {
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.BADGE_TEXT -> {
                                Intent(activity, SampleBadgeTextActivity::class.java).apply {
                                    activity.startActivity(this)
                                }
                            }

                            HongWidgetType.SCROLL_TAB -> {
                                Intent(activity, SampleScrollTabActivity::class.java).apply {
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
                            HongWidgetType.VIDEO_PLAYER -> {
                                Intent(activity, SampleVideoPlayerActivity::class.java).apply {
                                    putExtra(SampleConst.WIDGET_TYPE, HongWidgetType.VIDEO_PLAYER.value)
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
                }
            }
        }
    }
}

private val sampleTypeList = listOf(
    "xml",
    "option builder",
    "compose"
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




