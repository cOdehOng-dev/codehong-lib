package com.codehong.library.widget.rule.color

import androidx.annotation.ColorRes
import com.codehong.library.widget.R

enum class HongColor(val colorName: String, @ColorRes val colorResId: Int, val hex: String) {
    BLACK_100("black_100", R.color.honglib_color_29292d, "#29292d"),
    BLACK_80("black_80", R.color.honglib_color_cc29292d, "#cc29292d"),
    BLACK_60("black_60", R.color.honglib_color_9929292d, "#9929292d"),
    BLACK_50("black_50", R.color.honglib_color_8029292d, "#8029292d"),
    BLACK_30("black_30", R.color.honglib_color_4d29292d, "#4d29292d"),
    BLACK_15("black_15", R.color.honglib_color_2629292d, "#2629292d"),
    BLACK_10("black_10", R.color.honglib_color_1a29292d, "#1a29292d"),
    BLACK_5("black_5", R.color.honglib_color_0d29292d, "#0d29292d),"),

    GRAY_80("gray_80", R.color.honglib_color_545457, "#ff545457"),
    GRAY_60("gray_60", R.color.honglib_color_7e7e81, "#ff7e7e81"),
    GRAY_50("gray_50", R.color.honglib_color_949496, "#ff949496"),
    GRAY_30("gray_30", R.color.honglib_color_bfbfc0, "#bfbfc0"),
    GRAY_15("gray_15", R.color.honglib_color_dfdfe0, "#dfdfe0"),
    GRAY_10("gray_10", R.color.honglib_color_eaeaea, "#ffeaeaea"),
    GRAY_5("gray_5", R.color.honglib_color_f4f4f5, "#ffeaeaea"),

    WHITE_100("white_100", R.color.honglib_color_ffffff, "#ffffffff"),
    WHITE_80("white_80", R.color.honglib_color_ccffffff, "#ccffffff"),
    WHITE_60("white_80", R.color.honglib_color_99ffffff, "#99ffffff"),
    WHITE_20("white_80", R.color.honglib_color_33ffffff, "#33ffffff"),
    WHITE_8("white_80", R.color.honglib_color_14ffffff, "#14ffffff),"),

    HONG_BG1("hong_bg1", R.color.honglib_color_fbfbfb, "#fbfbfb"),
    HONG_BG2("hong_bg2", R.color.honglib_color_f7f7f8, "#f7f7f8"),
    HONG_ALERT("alert", R.color.honglib_color_be0017, "#be0017"),

    MAIN_PURPLE("primary_mint", R.color.honglib_color_8b00ff, "#8b00ff"),
//    MAIN_COLOR("main_color", R.color.honglib_color_8b00ff, 51),
}
