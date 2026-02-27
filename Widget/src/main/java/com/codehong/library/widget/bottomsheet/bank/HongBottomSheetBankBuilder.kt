package com.codehong.library.widget.bottomsheet.bank

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.util.KoreanBank

class HongBottomSheetBankBuilder : HongWidgetCommonBuilder<HongBottomSheetBankOption, HongBottomSheetBankBuilder> {

    override val builder: HongBottomSheetBankBuilder = this
    override val option: HongBottomSheetBankOption = HongBottomSheetBankOption()

    fun topRadius(radius: Int) = apply {
        option.topRadius = radius
    }

     fun dimColor(color: String) = apply {
         option.dimColorHex = color
     }

     fun dimColor(color: HongColor) = apply {
         option.dimColorHex = color.hex
     }

     fun dragHandleColor(color: HongColor) = apply {
         option.dragHandleColorHex = color.hex
     }
     fun dragHandleColor(color: String) = apply {
         option.dragHandleColorHex = color
     }


     fun titleTypo(typo: HongTypo) = apply {
         option.titleTypo = typo
     }

     fun titleColor(color: HongColor) = apply {
         option.titleColorHex = color.hex
     }
     fun titleColor(color: String) = apply {
         option.titleColorHex = color
     }

    fun onBankSelected(onBankSelected: (KoreanBank) -> Unit) = apply {
        option.onBankSelected = onBankSelected
    }

    fun onDismissed(onDismissed: () -> Unit) = apply {
        option.onDismissed = onDismissed
    }


    fun copy(inject: HongBottomSheetBankOption?): HongBottomSheetBankBuilder {
        if (inject == null) return HongBottomSheetBankBuilder()

        return HongBottomSheetBankBuilder()
            .topRadius(inject.width)
            .dimColor(inject.dimColorHex)
            .dragHandleColor(inject.dragHandleColorHex)
            .titleTypo(inject.titleTypo)
            .titleColor(inject.titleColorHex)
            .onBankSelected(inject.onBankSelected)
            .onDismissed(inject.onDismissed)
    }
}