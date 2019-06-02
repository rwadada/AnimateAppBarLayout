package com.rwadada.animateappbarlayout.animations

import android.view.animation.Animation

data class AppBarScaleAppBarAnimation(
    val fromX: Float,
    val toX: Float,
    val fromY: Float,
    val toY: Float,
    val pivotXType: Int = Animation.ABSOLUTE,
    val pivotXVal: Float = 0.0f,
    val pivotYType: Int = Animation.ABSOLUTE,
    val pivotYVal: Float = 0.0f
)