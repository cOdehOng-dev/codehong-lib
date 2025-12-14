package com.codehong.lib.sample.text.count

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongCountType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.count.HongTextCountBuilder
import com.codehong.library.widget.text.count.HongTextCountCompose
import com.codehong.library.widget.text.count.HongTextCountView

class SampleTextCountActivity : BaseSampleMixActivity() {

    private val option1 = HongTextCountBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f
            )
        )
        .startCount(0)
        .maxCount(1.6)
        .amount(1.2)
        .unitText("킬로미터")
        .countType(HongCountType.LONG)
        .onCountChange {

        }
        .applyOption()

    private val option2 = HongTextCountBuilder()
        .margin(
            HongSpacingInfo(
                top = 20f
            )
        )
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f
            )
        )
        .startCount(0.0)
        .maxCount(5.2)
        .amount(1.2)
        .unitText("킬로미터")
        .countType(HongCountType.DOUBLE)
        .onCountChange {

        }
        .applyOption()

    private val option3 = HongTextCountBuilder()
        .margin(
            HongSpacingInfo(
                top = 20f
            )
        )
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f
            )
        )
        .startCount(0.0)
        .maxCount(5.2)
        .unitText("킬로미터")
        .countType(HongCountType.DOUBLE)
        .buttonType(HongButtonIconType.SIZE_28)
        .onCountChange {

        }
        .applyOption()

    private val option4 = HongTextCountBuilder()
        .margin(
            HongSpacingInfo(
                top = 20f
            )
        )
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f
            )
        )
        .startCount(0.0)
        .maxCount(5.2)
        .unitText("킬로미터")
        .countType(HongCountType.DOUBLE)
        .countColor(HongColor.MAIN_ORANGE_100)
        .buttonType(HongButtonIconType.SIZE_28)
        .onCountChange {

        }
        .applyOption()

    private val option5 = HongTextCountBuilder()
        .margin(
            HongSpacingInfo(
                top = 20f
            )
        )
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f
            )
        )
        .startCount(0.0)
        .maxCount(5.2)
        .unitText("킬로미터")
        .countType(HongCountType.DOUBLE)
        .countColor(HongColor.MAIN_ORANGE_100)
        .buttonType(HongButtonIconType.SIZE_28)
        .onCountChange {

        }
        .applyOption()

    private val option6 = HongTextCountBuilder()
        .margin(
            HongSpacingInfo(
                top = 20f
            )
        )
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f
            )
        )
        .startCount(0.0)
        .maxCount(5.2)
        .unitText("킬로미터")
        .countType(HongCountType.DOUBLE)
        .countColor(HongColor.MAIN_ORANGE_100)
        .buttonType(HongButtonIconType.SIZE_28)
        .onCountChange {

        }
        .applyOption()

    private val option7 = HongTextCountBuilder()
        .margin(
            HongSpacingInfo(
                top = 20f
            )
        )
        .padding(
            HongSpacingInfo(
                top = 20f,
                left = 20f
            )
        )
        .startCount(0.0)
        .maxCount(5.2)
        .unitText("킬로미터")
        .countTypo(HongTypo.TITLE_28_B)
        .countType(HongCountType.DOUBLE)
        .countColor(HongColor.MAIN_ORANGE_100)
        .buttonType(HongButtonIconType.SIZE_28)
        .onCountChange {

        }
        .applyOption()

    private val optionList = listOf(
        option1,
        option2,
        option3,
        option4,
        option5,
        option6,
        option7
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { option ->
                add(HongTextCountView(this@SampleTextCountActivity).set(option))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach { option ->
            HongTextCountCompose(option)
        }
    }
}