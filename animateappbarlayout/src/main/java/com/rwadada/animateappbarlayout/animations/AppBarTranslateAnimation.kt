package com.rwadada.animateappbarlayout.animations

import android.view.animation.Animation

data class AppBarTranslateAnimation(
    val fromXType: Int = Animation.ABSOLUTE,
    val fromXValue: Float = 0.0f,
    val toXType: Int = Animation.ABSOLUTE,
    val toXValue: Float = 0.0f,
    val fromYType: Int = Animation.ABSOLUTE,
    val fromYValue: Float = 0.0f,
    val toYType: Int = Animation.ABSOLUTE,
    val toYValue: Float = 0.0f
) : AppBarAnimation