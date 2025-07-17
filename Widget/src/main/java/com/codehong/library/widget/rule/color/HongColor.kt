package com.codehong.library.widget.rule.color

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.toColor

enum class HongColor(val colorName: String, @ColorRes val colorResId: Int, val hex: String) {
    TRANSPARENT("transparent", R.color.honglib_color_transparent, "#00000000"),

    MAIN_ORANGE_100("main_orange_100", R.color.honglib_color_ff8224, "#FFFF8224"),
    MAIN_ORANGE_90("main_orange_90", R.color.honglib_color_ff8224_90, "#E6FF8224"),
    MAIN_ORANGE_80("main_orange_80", R.color.honglib_color_ff8224_80, "#CCFF8224"),
    MAIN_ORANGE_70("main_orange_70", R.color.honglib_color_ff8224_70, "#B3FF8224"),
    MAIN_ORANGE_60("main_orange_60", R.color.honglib_color_ff8224_60, "#99FF8224"),
    MAIN_ORANGE_50("main_orange_50", R.color.honglib_color_ff8224_50, "#80FF8224"),
    MAIN_ORANGE_40("main_orange_40", R.color.honglib_color_ff8224_40, "#66FF8224"),
    MAIN_ORANGE_30("main_orange_30", R.color.honglib_color_ff8224_30, "#4DFF8224"),
    MAIN_ORANGE_25("main_orange_25", R.color.honglib_color_ff8224_25, "#40FF8224"),
    MAIN_ORANGE_20("main_orange_20", R.color.honglib_color_ff8224_20, "#33FF8224"),
    MAIN_ORANGE_15("main_orange_15", R.color.honglib_color_ff8224_15, "#26FF8224"),
    MAIN_ORANGE_10("main_orange_10", R.color.honglib_color_ff8224_10, "#1AFF8224"),
    MAIN_ORANGE_05("main_orange_05", R.color.honglib_color_ff8224_05, "#0DFF8224"),

    BLACK_100("black_100", R.color.honglib_color_29292d, "#ff29292d"),
    BLACK_80("black_80", R.color.honglib_color_cc29292d, "#cc29292d"),
    BLACK_60("black_60", R.color.honglib_color_9929292d, "#9929292d"),
    BLACK_50("black_50", R.color.honglib_color_8029292d, "#8029292d"),
    BLACK_30("black_30", R.color.honglib_color_4d29292d, "#4d29292d"),
    BLACK_25("black_25", R.color.honglib_color_4029292d, "#4029292d"),
    BLACK_15("black_15", R.color.honglib_color_2629292d, "#2629292d"),
    BLACK_10("black_10", R.color.honglib_color_1a29292d, "#1a29292d"),
    BLACK_5("black_5", R.color.honglib_color_0d29292d, "#0d29292d"),

    WHITE_100("white_100", R.color.honglib_color_ffffff, "#FFFFFFFF"),
    WHITE_90("white_90", R.color.honglib_color_ffffff_90, "#E6FFFFFF"),
    WHITE_80("white_80", R.color.honglib_color_ffffff_80, "#CCFFFFFF"),
    WHITE_70("white_70", R.color.honglib_color_ffffff_70, "#B3FFFFFF"),
    WHITE_60("white_60", R.color.honglib_color_ffffff_60, "#99FFFFFF"),
    WHITE_50("white_50", R.color.honglib_color_ffffff_50, "#80FFFFFF"),
    WHITE_40("white_40", R.color.honglib_color_ffffff_40, "#66FFFFFF"),
    WHITE_30("white_30", R.color.honglib_color_ffffff_30, "#4DFFFFFF"),
    WHITE_25("white_25", R.color.honglib_color_ffffff_25, "#40FFFFFF"),
    WHITE_20("white_20", R.color.honglib_color_ffffff_20, "#33FFFFFF"),
    WHITE_15("white_15", R.color.honglib_color_ffffff_15, "#26FFFFFF"),
    WHITE_10("white_10", R.color.honglib_color_ffffff_10, "#1AFFFFFF"),
    WHITE_05("white_05", R.color.honglib_color_ffffff_05, "#0DFFFFFF"),

    RED_100("red_100", R.color.honglib_color_ff322e, "#FFFF322E"),
    RED_90("red_90", R.color.honglib_color_ff322e_90, "#E6FF322E"),
    RED_80("red_80", R.color.honglib_color_ff322e_80, "#CCFF322E"),
    RED_70("red_70", R.color.honglib_color_ff322e_70, "#B3FF322E"),
    RED_60("red_60", R.color.honglib_color_ff322e_60, "#99FF322E"),
    RED_50("red_50", R.color.honglib_color_ff322e_50, "#80FF322E"),
    RED_40("red_40", R.color.honglib_color_ff322e_40, "#66FF322E"),
    RED_30("red_30", R.color.honglib_color_ff322e_30, "#4DFF322E"),
    RED_25("red_25", R.color.honglib_color_ff322e_25, "#40FF322E"),
    RED_20("red_20", R.color.honglib_color_ff322e_20, "#33FF322E"),
    RED_15("red_15", R.color.honglib_color_ff322e_15, "#26FF322E"),
    RED_10("red_10", R.color.honglib_color_ff322e_10, "#1AFF322E"),
    RED_05("red_05", R.color.honglib_color_ff322e_05, "#0DFF322E"),

    BLUE_100("blue_100", R.color.honglib_color_0043be, "#FF0043BE"),
    BLUE_90("blue_90", R.color.honglib_color_0043be_90, "#E60043BE"),
    BLUE_80("blue_80", R.color.honglib_color_0043be_80, "#CC0043BE"),
    BLUE_70("blue_70", R.color.honglib_color_0043be_70, "#B30043BE"),
    BLUE_60("blue_60", R.color.honglib_color_0043be_60, "#990043BE"),
    BLUE_50("blue_50", R.color.honglib_color_0043be_50, "#800043BE"),
    BLUE_40("blue_40", R.color.honglib_color_0043be_40, "#660043BE"),
    BLUE_30("blue_30", R.color.honglib_color_0043be_30, "#4D0043BE"),
    BLUE_25("blue_25", R.color.honglib_color_0043be_25, "#400043BE"),
    BLUE_20("blue_20", R.color.honglib_color_0043be_20, "#330043BE"),
    BLUE_15("blue_15", R.color.honglib_color_0043be_15, "#260043BE"),
    BLUE_10("blue_10", R.color.honglib_color_0043be_10, "#1A0043BE"),
    BLUE_05("blue_05", R.color.honglib_color_0043be_05, "#0D0043BE"),

    GRAY_100("gray_100", R.color.honglib_color_545457, "#FF545457"),
    GRAY_90("gray_90", R.color.honglib_color_545457_90, "#E6545457"),
    GRAY_80("gray_80", R.color.honglib_color_545457_80, "#CC545457"),
    GRAY_70("gray_70", R.color.honglib_color_545457_70, "#B3545457"),
    GRAY_60("gray_60", R.color.honglib_color_545457_60, "#99545457"),
    GRAY_50("gray_50", R.color.honglib_color_545457_50, "#80545457"),
    GRAY_40("gray_40", R.color.honglib_color_545457_40, "#66545457"),
    GRAY_30("gray_30", R.color.honglib_color_545457_30, "#4D545457"),
    GRAY_25("gray_25", R.color.honglib_color_545457_25, "#40545457"),
    GRAY_20("gray_20", R.color.honglib_color_545457_20, "#33545457"),
    GRAY_15("gray_15", R.color.honglib_color_545457_15, "#26545457"),
    GRAY_10("gray_10", R.color.honglib_color_545457_10, "#1A545457"),
    GRAY_05("gray_05", R.color.honglib_color_545457_05, "#0D545457"),

    YELLOW_100("yellow_100", R.color.honglib_color_fdc400, "#FFFDC400"),
    YELLOW_90("yellow_90", R.color.honglib_color_fdc400_90, "#E6FDC400"),
    YELLOW_80("yellow_80", R.color.honglib_color_fdc400_80, "#CCFDC400"),
    YELLOW_70("yellow_70", R.color.honglib_color_fdc400_70, "#B3FDC400"),
    YELLOW_60("yellow_60", R.color.honglib_color_fdc400_60, "#99FDC400"),
    YELLOW_50("yellow_50", R.color.honglib_color_fdc400_50, "#80FDC400"),
    YELLOW_40("yellow_40", R.color.honglib_color_fdc400_40, "#66FDC400"),
    YELLOW_30("yellow_30", R.color.honglib_color_fdc400_30, "#4DFDC400"),
    YELLOW_25("yellow_25", R.color.honglib_color_fdc400_25, "#40FDC400"),
    YELLOW_20("yellow_20", R.color.honglib_color_fdc400_20, "#33FDC400"),
    YELLOW_15("yellow_15", R.color.honglib_color_fdc400_15, "#26FDC400"),
    YELLOW_10("yellow_10", R.color.honglib_color_fdc400_10, "#1AFDC400"),
    YELLOW_05("yellow_05", R.color.honglib_color_fdc400_05, "#0DFDC400"),


    LINE("line", R.color.honglib_color_eaeaea, "#ffeaeaea"),


    ;



    companion object {
        fun String?.nameToHongColor(): HongColor? {
            return entries.find { it.colorName == this }
        }

        fun String?.hexToHongColor(): HongColor? {
            return entries.find { it.hex == this }
        }

        fun HongColor?.toColor(): Color {
            return this?.hex.toColor()
        }

        fun HongColor?.parseColor(): Int {
            val hex = this?.hex
            return try {
                android.graphics.Color.parseColor(
                    if (hex.isNullOrEmpty()
                        || hex.equals("null", true)
                        || hex.equals("none", true)
                        || hex.equals("blank", true)
                        || hex.equals("empty", true)
                    ) {
                        "#00000000"
                    } else if (hex.startsWith("#")) {
                        when (hex.length) {
                            7 -> hex.replace("#", "#ff")
                            9 -> hex
                            else -> "#00000000"
                        }
                    } else {
                        when (hex.length) {
                            6 -> "#ff$this"
                            8 -> "#$this"
                            else -> "#00000000"
                        }
                    }
                )
            } catch (e: Exception) {
                0
            }
        }
    }
}
