package com.codehong.lib.sample

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import com.codehong.library.widget.rule.HongWidgetType

fun ComponentActivity.startSampleActivity(
    activityClass: Class<*>,
    widgetType: HongWidgetType
) {
    Intent(this, activityClass).apply {
        putExtra(SampleConst.WIDGET_TYPE, widgetType.value)
        startActivity(this)
    }
}

fun ComponentActivity.startSampleActivity(activityClass: Class<*>) {
    Intent(this, activityClass).apply {
        startActivity(this)
    }
}

fun Context.startSampleActivity(
    activityClass: Class<*>,
    widgetType: HongWidgetType
) {
    Intent(this, activityClass).apply {
        putExtra(SampleConst.WIDGET_TYPE, widgetType.value)
        startActivity(this)
    }
}

fun Context.startSampleActivity(activityClass: Class<*>) {
    Intent(this, activityClass).apply {
        startActivity(this)
    }
}
