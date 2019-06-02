package com.rwadada.animateappbarlayout.animations

class AppBarAnimationSet {
    private var appBarAnimations = mutableListOf<AppBarAnimation>()

    fun addAnimation(appBarAnimation: AppBarAnimation) {
        appBarAnimations.add(appBarAnimation)
    }

    fun getAppBarAnimations(): List<AppBarAnimation?> {
        return appBarAnimations
    }
}