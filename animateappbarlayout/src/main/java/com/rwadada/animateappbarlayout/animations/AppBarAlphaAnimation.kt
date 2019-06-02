package com.rwadada.animateappbarlayout.animations

data class AppBarAlphaAnimation (
    val fromAlpha: Float,
    val toAlpha: Float
) : AppBarAnimation {
    internal var prevTargetAlpha: Float = 0.0f
    internal var nextTargetAlpha: Float = 0.0f
}