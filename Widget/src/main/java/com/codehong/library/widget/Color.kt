package com.codehong.library.widget

import androidx.annotation.ColorRes

enum class ColorType(val colorName: String, @ColorRes val colorResId: Int, val code: Int) {
    BLACK_100("black_100", R.color.honglib_color_29292d, 10),
    BLACK_80("black_80", R.color.honglib_color_cc29292d, 11),
    BLACK_60("black_60", R.color.honglib_color_9929292d, 12),
    BLACK_50("black_50", R.color.honglib_color_8029292d, 13),
    BLACK_30("black_30", R.color.honglib_color_4d29292d, 14),
    BLACK_15("black_15", R.color.honglib_color_2629292d, 15),
    BLACK_10("black_10", R.color.honglib_color_1a29292d, 16),
    BLACK_5("black_5", R.color.honglib_color_0d29292d, 17),

    GRAY_80("gray_80", R.color.honglib_color_545457, 20),
    GRAY_60("gray_60", R.color.honglib_color_7e7e81, 21),
    GRAY_50("gray_50", R.color.honglib_color_949496, 22),
    GRAY_30("gray_30", R.color.honglib_color_bfbfc0, 23),
    GRAY_15("gray_15", R.color.honglib_color_dfdfe0, 24),
    GRAY_10("gray_10", R.color.honglib_color_eaeaea, 25),
    GRAY_5("gray_5", R.color.honglib_color_f4f4f5, 26),

    WHITE_100("white_100", R.color.honglib_color_ffffff, 30),
    WHITE_80("white_80", R.color.honglib_color_ccffffff, 31),
    WHITE_60("white_80", R.color.honglib_color_99ffffff, 32),
    WHITE_20("white_80", R.color.honglib_color_33ffffff, 33),
    WHITE_8("white_80", R.color.honglib_color_14ffffff, 34),

    HONG_BG1("hong_bg1", R.color.honglib_color_fbfbfb, 40),
    HONG_BG2("hong_bg2", R.color.honglib_color_f7f7f8, 41),
    HONG_ALERT("alert", R.color.honglib_color_be0017, 42),

    PRIMARY_MINT("primary_mint", R.color.honglib_color_3eb489, 50),
}
