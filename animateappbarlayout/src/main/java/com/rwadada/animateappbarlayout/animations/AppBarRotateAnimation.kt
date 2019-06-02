package com.rwadada.animateappbarlayout.animations

import android.view.animation.Animation

class AppBarRotateAnimation (
    val fromDegree: Float,
    val toDegree: Float,
    val pivotXType: Int = Animation.ABSOLUTE,
    val pivotXVal: Float = 0.0f,
    val pivotYType: Int = Animation.ABSOLUTE,
    val pivotYVal: Float = 0.0f
) : AppBarAnimation {
    internal var prevTargetDegree: Float = 0.0f
    internal var nextTargetDegree: Float = 0.0f
}