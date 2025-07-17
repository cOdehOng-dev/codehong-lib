package com.codehong.lib.sample.player

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleVideoPlayerBinding
import com.codehong.library.widget.player.HongVideoPlayerCompose
import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.player.HongVideoPlayerView
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType
import com.codehong.library.widget.rule.radius.HongRadiusInfo

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
        val option = HongVideoPlayerBuilder()
            .margin(
                HongSpacingInfo(
                    left = 10f,
                    right = 10f
                )
            )
            .setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4")
            .ratio("16:9")
            .applyOption()
        binding.vVideoPlayer.set(
            option = option,
            onReady = {
            },
            onError = {
            },
            onEnd = {
            }
        ).play()
    }

    private fun initBuilder() {
        val option = HongVideoPlayerBuilder()
            .setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")
            .radius(
                HongRadiusInfo(
                    all = 14
                )
            )
            .ratio("16:9")
            .applyOption()
        listOf(
            HongVideoPlayerView(this).set(option = option).apply {
                play()
            }
        ).forEach {
            binding.containerSample.llView.addView(it)
        }
    }


    @Composable
    fun InitCompose() {
        val option = HongVideoPlayerBuilder()
            .setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4")
            .radius(
                HongRadiusInfo(
                    top = 14
                )
            )
            .ratio("16:9")
            .applyOption()
        HongVideoPlayerCompose(
            option = option
        )
    }
}