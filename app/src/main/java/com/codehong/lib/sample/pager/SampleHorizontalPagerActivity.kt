package com.codehong.lib.sample.pager

import android.util.Log
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codehong.lib.sample.SampleComposeDespContainer
import com.codehong.lib.sample.SampleConst.testImageUrlList
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageView
import com.codehong.library.widget.pager.HongHorizontalPagerCompose
import com.codehong.library.widget.pager.HongHorizontalPagerBuilder
import com.codehong.library.widget.pager.HongHorizontalPagerView
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.extensions.toColor

class SampleHorizontalPagerActivity : BaseSampleMixActivity() {

    private var pagerViewList = mutableListOf<HongHorizontalPagerView>()

    private val defOption = HongHorizontalPagerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .verticalPadding(
            topPadding = 20f,
            bottomPadding = 20f
        )
        .backgroundColor(HongColor.WHITE_100.hex)
        .pageInfoList(testImageUrlList)
        .pageSpacing(10)
        .currentIndex {
            Log.d("TAG", "기본 현재 인데스: $it")
        }
        .applyOption()

    private val autoNandInifiniteNoption = HongHorizontalPagerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .verticalPadding(
            topPadding = 20f,
            bottomPadding = 20f
        )
        .backgroundColor(HongColor.WHITE_100.hex)
        .pageInfoList(testImageUrlList)
        .pageSpacing(10)
        .infiniteScroll(
            on = false,
            isRollbackFirst = false
        )
        .pageVisibleWidth(
            prev = 20f,
            next = 20f
        )
        .currentIndex {
            Log.d("TAG", "오토스크롤 X, 무한스크롤 X 현재 인덱스: $it")
        }
        .applyOption()

    private val autoInfiniteOption = HongHorizontalPagerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .backgroundColor(HongColor.WHITE_100.hex)
        .pageInfoList(testImageUrlList)
        .pageSpacing(10)
        .pageVisibleWidth(
            prev = 20f,
            next = 20f
        )
        .autoScrollMillSecond(3000)
        .infiniteScroll(
            on = true,
            isRollbackFirst = false
        )
        .currentIndex {
            Log.d("TAG", "오토스크롤 O, 무한스크롤 O 현재 인덱스: $it")
        }
        .applyOption()

    private val autoNinifiniteYoption = HongHorizontalPagerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .backgroundColor(HongColor.WHITE_100.hex)
        .pageInfoList(testImageUrlList)
        .pageSpacing(5)
        .pageVisibleWidth(
            prev = 15f,
            next = 15f
        )
        .infiniteScroll(
            on = true,
            isRollbackFirst = false
        )
        .currentIndex {
            Log.d("TAG", "오토스크롤 X, 무한스크롤 O 현재 인덱스: $it")
        }
        .applyOption()

    private val rollbackOption = HongHorizontalPagerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .backgroundColor(HongColor.WHITE_100.hex)
        .pageInfoList(testImageUrlList)
        .pageSpacing(5)
        .pageVisibleWidth(
            prev = 15f,
            next = 15f
        )
        .infiniteScroll(
            on = false,
            isRollbackFirst = true
        )
        .currentIndex {
            Log.d("TAG", "rollback 현재 인덱스: $it")
        }
        .applyOption()

    private val autoRollbackOption = HongHorizontalPagerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .backgroundColor(HongColor.WHITE_100.hex)
        .pageInfoList(testImageUrlList)
        .pageSpacing(5)
        .autoScrollMillSecond(3000)
        .pageVisibleWidth(
            prev = 15f,
            next = 15f
        )
        .infiniteScroll(
            on = true,
            isRollbackFirst = true
        )
        .currentIndex {
            Log.d("TAG", "auto rollback 현재 인덱스: $it")
        }
        .applyOption()

    override fun onDestroy() {
        pagerViewList.forEach { it.stopAutoScroll() }
        super.onDestroy()
    }

    override fun optionDesscriptionViewList(): List<Pair<String, View>> {
        return listOf(
            Pair(
                "기본",
                HongHorizontalPagerView(this)
                    .set(defOption) { binding, data ->
                        (data as? String)
                            ?.takeIf { it.isNotEmpty() }
                            ?.let { imageUrl ->
                                binding.flContainer.addView(
                                    HongImageView(this).set(
                                        HongImageBuilder()
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
                                )
                            }
                    }.apply {
                        pagerViewList.add(this)
                    }
            ),
            Pair(
                "오토스크롤 X, 무한스크롤 X",
                HongHorizontalPagerView(this)
                    .set(autoNandInifiniteNoption) { binding, data ->
                        (data as? String)
                            ?.takeIf { it.isNotEmpty() }
                            ?.let { imageUrl ->
                                binding.flContainer.addView(
                                    HongImageView(this).set(
                                        HongImageBuilder()
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
                                )
                            }
                    }.apply {
                        pagerViewList.add(this)
                    }
            ),
            Pair(
                "오토스크롤 O, 무한스크롤 O",
                HongHorizontalPagerView(this).set(autoInfiniteOption) { binding, data ->
                    (data as? String)
                        ?.takeIf { it.isNotEmpty() }
                        ?.let { imageUrl ->
                            binding.flContainer.addView(
                                HongImageView(this).set(
                                    HongImageBuilder()
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
                            )
                        }
                }.apply {
                    pagerViewList.add(this)
                }
            ),
            Pair(
                "오토스크롤 X, 무한스크롤 O",
                HongHorizontalPagerView(this)
                    .set(autoNinifiniteYoption) { binding, data ->
                        (data as? String)
                            ?.takeIf { it.isNotEmpty() }
                            ?.let { imageUrl ->
                                binding.flContainer.addView(
                                    HongImageView(this).set(
                                        HongImageBuilder()
                                            .width(HongLayoutParam.MATCH_PARENT.value)
                                            .height(250)
                                            .imageUrl(imageUrl)
                                            .radius(
                                                HongRadiusInfo(
                                                    all = 12
                                                )
                                            )
                                            .scaleType(HongScaleType.CENTER_CROP)
                                            .applyOption()
                                    )
                                )
                            }
                    }.apply {
                        pagerViewList.add(this)
                    }
            ),
            Pair(
                "스크롤 완료 후 처음으로 백",
                HongHorizontalPagerView(this)
                    .set(rollbackOption) { binding, data ->
                        (data as? String)
                            ?.takeIf { it.isNotEmpty() }
                            ?.let { imageUrl ->
                                binding.flContainer.addView(
                                    HongImageView(this).set(
                                        HongImageBuilder()
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
                                )
                            }
                    }.apply {
                        pagerViewList.add(this)
                    }
            ),
            Pair(
                "자동 스크롤 완료 후 처음으로 백",
                HongHorizontalPagerView(this)
                    .set(autoRollbackOption) { binding, data ->
                        (data as? String)
                            ?.takeIf { it.isNotEmpty() }
                            ?.let { imageUrl ->
                                binding.flContainer.addView(
                                    HongImageView(this).set(
                                        HongImageBuilder()
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
                                )
                            }
                    }.apply {
                        pagerViewList.add(this)
                    }
            )

        )
    }

    @Composable
    override fun InitCompose() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(HongColor.WHITE_100.hex.toColor())
        ) {
            SampleComposeDespContainer(desp = "기본") {
                HongHorizontalPagerCompose(
                    option = defOption,
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
            SampleComposeDespContainer(desp = "오토스크롤 X, 무한스크롤 X") {
                HongHorizontalPagerCompose(
                    option = autoNandInifiniteNoption,
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
            SampleComposeDespContainer(desp = "오토스크롤 O, 무한스크롤 O") {
                HongHorizontalPagerCompose(
                    option = autoInfiniteOption,
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
            SampleComposeDespContainer(desp = "오토스크롤 X, 무한스크롤 O") {
                HongHorizontalPagerCompose(
                    option = autoNinifiniteYoption,
                ) { item ->
                    (item as? String)
                        ?.toString()
                        ?.takeIf { it.isNotEmpty() }
                        ?.let { imageUrl ->
                            HongImageCompose(
                                option = HongImageBuilder()
                                    .width(HongLayoutParam.MATCH_PARENT.value)
                                    .height(250)
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
            SampleComposeDespContainer(desp = "스크롤 완료 후 처음으로 백") {
                HongHorizontalPagerCompose(
                    option = rollbackOption,
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
            SampleComposeDespContainer(desp = "자동 스크롤 완료 후 처음으로 백") {
                HongHorizontalPagerCompose(
                    option = autoRollbackOption,
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
}
