package com.codehong.lib.sample.text.count

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongCountType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.count.HongTextCountBuilder
import com.codehong.library.widget.text.count.HongTextCountCompose

class SampleTextCountActivity : BaseSampleMixActivity() {

    @Composable
    override fun InitCompose() {
        HongTextCountCompose(
            option = HongTextCountBuilder()
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
                .applyOption(),
            modifier = Modifier
                .padding(top = 20.dp),
        )

        HongTextCountCompose(
            option = HongTextCountBuilder()
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
                .applyOption(),
            modifier = Modifier
                .padding(top = 20.dp),
        )

        HongTextCountCompose(
            option = HongTextCountBuilder()
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
                .applyOption(),
            modifier = Modifier
                .padding(top = 20.dp),
        )

        HongTextCountCompose(
            option = HongTextCountBuilder()
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
                .applyOption(),
            modifier = Modifier
                .padding(top = 20.dp),
        )

        HongTextCountCompose(
            option = HongTextCountBuilder()
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
                .applyOption(),

            modifier = Modifier
                .padding(top = 20.dp),
        )
    }
}