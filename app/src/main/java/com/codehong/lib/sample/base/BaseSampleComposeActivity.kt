package com.codehong.lib.sample.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codehong.lib.sample.SampleConst
import com.codehong.library.network.debug.TimberUtil
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.header.icon.HongHeaderIcon
import com.codehong.library.widget.header.icon.HongHeaderIconBuilder
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

abstract class BaseSampleComposeActivity : BaseActivity() {

    private var widgetType: HongWidgetType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        widgetType = intent.getStringExtra(SampleConst.WIDGET_TYPE).toHongWidgetType()

        if (widgetType == HongWidgetType.NO_VALUE) {
            TimberUtil.e("Invalid widget type")
            finish()
            return
        }

        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                topBar = {
                    HongHeaderIcon(
                        HongHeaderIconBuilder()
                            .title(widgetType?.value)
                            .titleTypo(HongTypo.BODY_18)
                            .titleColor(HongColor.BLACK_100.hex)
                            .backIcon(R.drawable.honglib_ic_arrow_left)
                            .onBack {
                                finish()
                            }
                            .applyOption()
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .hongBackground(HongColor.WHITE_100)
                        .padding(it)
                ) {
                    InitCompose()
                }
            }
        }
    }

    @Composable
    protected abstract fun InitCompose()

}
