package com.rwadada.animateappbarlayout.animations

import android.view.animation.Animation

class AppBarScaleAnimation(
    val fromX: Float,
    val toX: Float,
    val fromY: Float,
    val toY: Float,
    val pivotXType: Int = Animation.ABSOLUTE,
    val pivotXVal: Float = 0.0f,
    val pivotYType: Int = Animation.ABSOLUTE,
    val pivotYVal: Float = 0.0f
) : AppBarAnimation {
    internal var prevTargetSizeX: Float = 0.0f
    internal var nextTargetSizeX: Float = 0.0f
    internal var prevTargetSizeY: Float = 0.0f
    internal var nextTargetSizeY: Float = 0.0f
}