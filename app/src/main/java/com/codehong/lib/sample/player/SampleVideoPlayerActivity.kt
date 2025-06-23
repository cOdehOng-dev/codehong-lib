package com.codehong.lib.sample.player

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleVideoPlayerBinding
import com.codehong.library.widget.player.builder.HongVideoPlayerBuilderView
import com.codehong.library.widget.player.builder.HongVideoPlayerOption
import com.codehong.library.widget.player.compose.HongComposeVideoPlayerView
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType

class SampleVideoPlayerActivity : BaseActivity() {

    private lateinit var binding: ActivitySampleVideoPlayerBinding
    private var widgetType: HongWidgetType? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        widgetType = intent.getStringExtra(SampleConst.WIDGET_TYPE).toHongWidgetType()

        if (widgetType == HongWidgetType.NO_VALUE) {
            finish()
            return
        }

        binding.vHeader.init(
            title = "${widgetType?.value} 샘플",
            back = {
                finish()
            }
        )

        initXml()
        initBuilder()
        binding.containerSample.vCompose.setContent {
            InitCompose()
        }
    }

    private fun initXml() {
        with(binding.vVideoPlayer) {
            set(
                radiusDp = 14,
                topLeft = true,
                topRight = true
            )
            play(
                playerUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
                ratio = "16:9",
            )
        }
    }

    private fun initBuilder() {
        val option = HongVideoPlayerOption.Builder()
            .setPlayerUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")
            .setRadiusDp(14)
            .setRatio("16:9")
            .build()
        listOf(
            HongVideoPlayerBuilderView(this).set(option = option).apply {
                play()
            }
        ).forEach {
            binding.containerSample.llView.addView(it)
        }
    }


    @Composable
    fun InitCompose() {
        val option = HongVideoPlayerOption.Builder()
            .setPlayerUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4")
            .setRadiusDp(14)
            .setRatio("16:9")
            .build()
        HongComposeVideoPlayerView(
            option = option
        )
    }
}