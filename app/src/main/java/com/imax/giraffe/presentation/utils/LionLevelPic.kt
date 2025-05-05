package com.imax.giraffe.presentation.utils

import com.imax.giraffe.R

sealed interface LevelPic {
    data object Rabbit: LevelPic
    data object Fox: LevelPic
    data object Lion : LevelPic
    data object Tiger : LevelPic
}
enum class RabbitLevelPic(val resId: Int) {
    LEVEL1(R.drawable.pic_rabbit_level_1),
    LEVEL2(R.drawable.pic_rabbit_level_1),
    LEVEL3(R.drawable.pic_rabbit_level_2),
    LEVEL4(R.drawable.pic_rabbit_level_2),
    LEVEL5(R.drawable.pic_rabbit_level_3),
    LEVEL6(R.drawable.pic_rabbit_level_3),
    LEVEL7(R.drawable.pic_rabbit_level_4),
    LEVEL8(R.drawable.pic_rabbit_level_4),
    LEVEL9(R.drawable.pic_rabbit_level_5),
    LEVEL10(R.drawable.pic_rabbit_level_5),
}
enum class FoxLevelPic(val resId: Int) {
    LEVEL1(R.drawable.pic_fox_level_1),
    LEVEL2(R.drawable.pic_fox_level_1),
    LEVEL3(R.drawable.pic_fox_level_2),
    LEVEL4(R.drawable.pic_fox_level_2),
    LEVEL5(R.drawable.pic_fox_level_3),
    LEVEL6(R.drawable.pic_fox_level_3),
    LEVEL7(R.drawable.pic_fox_level_4),
    LEVEL8(R.drawable.pic_fox_level_4),
    LEVEL9(R.drawable.pic_fox_level_5),
    LEVEL10(R.drawable.pic_fox_level_5),
}
enum class LionLevelPic(val resId: Int) {
    LEVEL1(R.drawable.pic_lion_level_1),
    LEVEL2(R.drawable.pic_lion_level_1),
    LEVEL3(R.drawable.pic_lion_level_2),
    LEVEL4(R.drawable.pic_lion_level_2),
    LEVEL5(R.drawable.pic_lion_level_3),
    LEVEL6(R.drawable.pic_lion_level_3),
    LEVEL7(R.drawable.pic_lion_level_4),
    LEVEL8(R.drawable.pic_lion_level_4),
    LEVEL9(R.drawable.pic_lion_level_5),
    LEVEL10(R.drawable.pic_lion_level_5),
}

enum class TigerLevelPic(val resId: Int) {
    LEVEL1(R.drawable.pic_tiger_level_1),
    LEVEL2(R.drawable.pic_tiger_level_1),
    LEVEL3(R.drawable.pic_tiger_level_2),
    LEVEL4(R.drawable.pic_tiger_level_2),
    LEVEL5(R.drawable.pic_tiger_level_3),
    LEVEL6(R.drawable.pic_tiger_level_3),
    LEVEL7(R.drawable.pic_tiger_level_4),
    LEVEL8(R.drawable.pic_tiger_level_4),
    LEVEL9(R.drawable.pic_tiger_level_5),
    LEVEL10(R.drawable.pic_tiger_level_5),
}