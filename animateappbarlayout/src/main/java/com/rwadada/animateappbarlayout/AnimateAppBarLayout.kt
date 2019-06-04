package com.rwadada.animateappbarlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.*
import com.google.android.material.appbar.AppBarLayout
import com.rwadada.animateappbarlayout.animations.*

class AnimateAppBarLayout(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {
    companion object {
        val TAG = AnimateAppBarLayout::class.java.simpleName
    }

    private var scrollViewHeight: Int = 0

    // duration0 fillAfter:trueでScaleAnimationを作成
    private fun getScaleAnimation(appBarScaleAnimation: AppBarScaleAnimation): ScaleAnimation {
        val scaleAnimation = ScaleAnimation(
            appBarScaleAnimation.prevTargetSizeX,
            appBarScaleAnimation.nextTargetSizeX,
            appBarScaleAnimation.prevTargetSizeY,
            appBarScaleAnimation.nextTargetSizeY,
            appBarScaleAnimation.pivotXType,
            appBarScaleAnimation.pivotXVal,
            appBarScaleAnimation.pivotYType,
            appBarScaleAnimation.pivotYVal
        )
        scaleAnimation.duration = 0
        scaleAnimation.fillAfter = true

        return scaleAnimation
    }

    // ScrollするごとにScaleAnimationに使う値を再計算
    private fun setScaleSize(
        verticalOffset: Int,
        appBarScaleAnimation: AppBarScaleAnimation
    ) {
        appBarScaleAnimation.prevTargetSizeX = appBarScaleAnimation.nextTargetSizeX
        appBarScaleAnimation.nextTargetSizeX =
            Math.abs(verticalOffset) * ((appBarScaleAnimation.toX - appBarScaleAnimation.fromX) / scrollViewHeight) + appBarScaleAnimation.fromX

        appBarScaleAnimation.prevTargetSizeY = appBarScaleAnimation.nextTargetSizeY
        appBarScaleAnimation.nextTargetSizeY =
            Math.abs(verticalOffset) * ((appBarScaleAnimation.toY - appBarScaleAnimation.fromY) / scrollViewHeight) + appBarScaleAnimation.fromY
    }

    // ScaleAnimation に使用する値を初期化
    private fun initScaleSize(appBarScaleAnimation: AppBarScaleAnimation) {
        appBarScaleAnimation.prevTargetSizeX = appBarScaleAnimation.fromX
        appBarScaleAnimation.nextTargetSizeX = appBarScaleAnimation.fromX
        appBarScaleAnimation.prevTargetSizeY = appBarScaleAnimation.fromY
        appBarScaleAnimation.nextTargetSizeY = appBarScaleAnimation.fromY
    }

    // AlphaAnimation に使用する値を初期化
    private fun initAlpha(appBarAlphaAnimation: AppBarAlphaAnimation) {
        appBarAlphaAnimation.prevTargetAlpha = appBarAlphaAnimation.fromAlpha
        appBarAlphaAnimation.nextTargetAlpha = appBarAlphaAnimation.fromAlpha
    }

    // duration0 fillAfter:trueでAlphaAnimationを作成
    private fun getAlphaAnimation(appBarAlphaAnimation: AppBarAlphaAnimation): AlphaAnimation {
        val alphaAnimation = AlphaAnimation(
            appBarAlphaAnimation.prevTargetAlpha,
            appBarAlphaAnimation.nextTargetAlpha
        )
        alphaAnimation.duration = 0
        alphaAnimation.fillAfter = true

        return alphaAnimation
    }

    // ScrollするごとにAlphaAnimationに使う値を再計算
    private fun setAlpha(
        verticalOffset: Int,
        appBarAlphaAnimation: AppBarAlphaAnimation
    ) {
        appBarAlphaAnimation.prevTargetAlpha = appBarAlphaAnimation.nextTargetAlpha
        appBarAlphaAnimation.nextTargetAlpha =
            Math.abs(verticalOffset) * ((appBarAlphaAnimation.toAlpha - appBarAlphaAnimation.fromAlpha) / scrollViewHeight) + appBarAlphaAnimation.fromAlpha
    }

    // RotateAnimationに使用する値を初期化
    private fun initRotateDegree(appBarRotateAnimation: AppBarRotateAnimation) {
        appBarRotateAnimation.prevTargetDegree = appBarRotateAnimation.fromDegree
        appBarRotateAnimation.nextTargetDegree = appBarRotateAnimation.fromDegree
    }

    // ScrollするごとにRotateAnimationに使う値を再計算
    private fun setRotateDegree(
        verticalOffset: Int,
        appBarRotateAnimation: AppBarRotateAnimation
    ) {
        appBarRotateAnimation.prevTargetDegree = appBarRotateAnimation.nextTargetDegree
        appBarRotateAnimation.nextTargetDegree =
            Math.abs(verticalOffset) * ((appBarRotateAnimation.toDegree - appBarRotateAnimation.fromDegree) / scrollViewHeight) + appBarRotateAnimation.fromDegree
    }

    // duration0 fillAfter:trueでRotateAnimationを作成
    private fun getRotateAnimation(appBarRotateAnimation: AppBarRotateAnimation): RotateAnimation {
        val rotateAnimation = RotateAnimation(
            appBarRotateAnimation.prevTargetDegree,
            appBarRotateAnimation.nextTargetDegree,
            appBarRotateAnimation.pivotXType,
            appBarRotateAnimation.pivotXVal,
            appBarRotateAnimation.pivotYType,
            appBarRotateAnimation.pivotYVal
        )
        rotateAnimation.duration = 0
        rotateAnimation.fillAfter = true

        return rotateAnimation
    }

    // TranslateAnimationに使用する値を初期化
    private fun initTranslatePosition(appBarTranslateAnimation: AppBarTranslateAnimation) {
        appBarTranslateAnimation.prevTranslateX = appBarTranslateAnimation.fromXValue
        appBarTranslateAnimation.nextTranslateX = appBarTranslateAnimation.fromXValue
        appBarTranslateAnimation.prevTranslateY = appBarTranslateAnimation.fromYValue
        appBarTranslateAnimation.nextTranslateY = appBarTranslateAnimation.fromYValue
    }

    // ScrollするごとにTranslateAnimationに使う値を再計算
    private fun setTranslatePosition(
        verticalOffset: Int,
        appBarTranslateAnimation: AppBarTranslateAnimation
    ) {
        appBarTranslateAnimation.prevTranslateX = appBarTranslateAnimation.nextTranslateX
        appBarTranslateAnimation.nextTranslateX =
            Math.abs(verticalOffset) * ((appBarTranslateAnimation.toXValue - appBarTranslateAnimation.fromXValue) / scrollViewHeight) + appBarTranslateAnimation.fromXValue

        appBarTranslateAnimation.prevTranslateY = appBarTranslateAnimation.nextTranslateY
        appBarTranslateAnimation.nextTranslateY =
            Math.abs(verticalOffset) * ((appBarTranslateAnimation.toYValue - appBarTranslateAnimation.fromYValue) / scrollViewHeight) + appBarTranslateAnimation.fromYValue
    }

    // duration0 fillAfter:trueでTranslateAnimationを作成
    private fun getTranslateAnimation(appBarTranslateAnimation: AppBarTranslateAnimation): TranslateAnimation {
        val translateAnimation = TranslateAnimation(
            appBarTranslateAnimation.fromXType,
            appBarTranslateAnimation.prevTranslateX,
            appBarTranslateAnimation.toXType,
            appBarTranslateAnimation.nextTranslateX,
            appBarTranslateAnimation.fromYType,
            appBarTranslateAnimation.prevTranslateY,
            appBarTranslateAnimation.toYType,
            appBarTranslateAnimation.nextTranslateY
        )
        translateAnimation.duration = 0
        translateAnimation.fillAfter = true

        return translateAnimation
    }

    fun setAnimation(
        appBarAnimationSet: AppBarAnimationSet,
        targetResourceId: Int,
        scrollViewResourceId: Int
    ) {
        lateinit var targetLayout: View
        lateinit var scrollView: View

        addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val animationSet = AnimationSet(true)
            for (appBarAnimation: AppBarAnimation? in appBarAnimationSet.getAppBarAnimations()) {
                when (verticalOffset) {
                    0 -> {
                        targetLayout = appBarLayout.findViewById(targetResourceId)
                        scrollView = appBarLayout.findViewById(scrollViewResourceId)
                        when (appBarAnimation) {
                            is AppBarScaleAnimation -> {
                                if (appBarAnimation.prevTargetSizeX == 0.0f &&
                                    appBarAnimation.nextTargetSizeX == 0.0f &&
                                    appBarAnimation.prevTargetSizeY == 0.0f &&
                                    appBarAnimation.nextTargetSizeY == 0.0f
                                ) {
                                    initScaleSize(appBarAnimation)
                                    if (scrollViewHeight == 0) {
                                        scrollViewHeight = scrollView.height
                                    }
                                }
                                setScaleSize(verticalOffset, appBarAnimation)
                                animationSet.addAnimation(getScaleAnimation(appBarAnimation))
                            }
                            is AppBarAlphaAnimation -> {
                                if (appBarAnimation.prevTargetAlpha == 0.0f &&
                                    appBarAnimation.nextTargetAlpha == 0.0f
                                ) {
                                    initAlpha(appBarAnimation)
                                    if (scrollViewHeight == 0) {
                                        scrollViewHeight = scrollView.height
                                    }
                                }
                                setAlpha(verticalOffset, appBarAnimation)
                                animationSet.addAnimation(getAlphaAnimation(appBarAnimation))
                            }
                            is AppBarRotateAnimation -> {
                                if (appBarAnimation.prevTargetDegree == 0.0f &&
                                    appBarAnimation.nextTargetDegree == 0.0f
                                ) {
                                    initRotateDegree(appBarAnimation)
                                    if (scrollViewHeight == 0) {
                                        scrollViewHeight = scrollView.height
                                    }
                                }
                                setRotateDegree(verticalOffset, appBarAnimation)
                                animationSet.addAnimation(getRotateAnimation(appBarAnimation))
                            }
                            is AppBarTranslateAnimation -> {
                                if (appBarAnimation.prevTranslateX == 0.0f &&
                                    appBarAnimation.nextTranslateX == 0.0f &&
                                    appBarAnimation.prevTranslateY == 0.0f &&
                                    appBarAnimation.nextTranslateY == 0.0f
                                ) {
                                    initTranslatePosition(appBarAnimation)
                                    if (scrollViewHeight == 0) {
                                        scrollViewHeight = scrollView.height
                                    }
                                }
                                setTranslatePosition(verticalOffset, appBarAnimation)
                                animationSet.addAnimation(getTranslateAnimation(appBarAnimation))
                            }
                        }
                    }
                    else -> {
                        when (appBarAnimation) {
                            is AppBarScaleAnimation -> {
                                setScaleSize(verticalOffset, appBarAnimation)
                                animationSet.addAnimation(getScaleAnimation(appBarAnimation))
                            }
                            is AppBarAlphaAnimation -> {
                                setAlpha(verticalOffset, appBarAnimation)
                                animationSet.addAnimation(getAlphaAnimation(appBarAnimation))
                            }
                            is AppBarRotateAnimation -> {
                                setRotateDegree(verticalOffset, appBarAnimation)
                                animationSet.addAnimation(getRotateAnimation(appBarAnimation))
                            }
                            is AppBarTranslateAnimation -> {
                                setTranslatePosition(verticalOffset, appBarAnimation)
                                animationSet.addAnimation(getTranslateAnimation(appBarAnimation))
                            }
                        }
                    }
                }
            }
            animationSet.duration = 0
            animationSet.fillAfter = true
            targetLayout.startAnimation(animationSet)
        })
    }

    fun setAnimation(
        appBarAnimation: AppBarAnimation,
        targetResourceId: Int,
        scrollViewResourceId: Int
    ) {
        lateinit var targetLayout: View
        lateinit var scrollView: View
        addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when (verticalOffset) {
                0 -> {
                    targetLayout = appBarLayout.findViewById(targetResourceId)
                    scrollView = appBarLayout.findViewById(scrollViewResourceId)
                    when (appBarAnimation) {
                        is AppBarScaleAnimation -> {
                            if (appBarAnimation.prevTargetSizeX == 0.0f &&
                                appBarAnimation.nextTargetSizeX == 0.0f &&
                                appBarAnimation.prevTargetSizeY == 0.0f &&
                                appBarAnimation.nextTargetSizeY == 0.0f
                            ) {
                                initScaleSize(appBarAnimation)
                                if (scrollViewHeight == 0) {
                                    scrollViewHeight = scrollView.height
                                }
                            }
                            setScaleSize(verticalOffset, appBarAnimation)
                            targetLayout.startAnimation(getScaleAnimation(appBarAnimation))
                        }
                        is AppBarAlphaAnimation -> {
                            if (appBarAnimation.prevTargetAlpha == 0.0f &&
                                appBarAnimation.nextTargetAlpha == 0.0f
                            ) {
                                initAlpha(appBarAnimation)
                                if (scrollViewHeight == 0) {
                                    scrollViewHeight = scrollView.height
                                }
                            }
                            setAlpha(verticalOffset, appBarAnimation)
                            targetLayout.startAnimation(getAlphaAnimation(appBarAnimation))
                        }
                        is AppBarRotateAnimation -> {
                            if (appBarAnimation.prevTargetDegree == 0.0f &&
                                appBarAnimation.nextTargetDegree == 0.0f
                            ) {
                                initRotateDegree(appBarAnimation)
                                if (scrollViewHeight == 0) {
                                    scrollViewHeight = scrollView.height
                                }
                            }
                            setRotateDegree(verticalOffset, appBarAnimation)
                            targetLayout.startAnimation(getRotateAnimation(appBarAnimation))
                        }
                        is AppBarTranslateAnimation -> {
                            if (appBarAnimation.prevTranslateX == 0.0f &&
                                appBarAnimation.nextTranslateX == 0.0f &&
                                appBarAnimation.prevTranslateY == 0.0f &&
                                appBarAnimation.nextTranslateY == 0.0f
                            ) {
                                initTranslatePosition(appBarAnimation)
                                if (scrollViewHeight == 0) {
                                    scrollViewHeight = scrollView.height
                                }
                            }
                            setTranslatePosition(verticalOffset, appBarAnimation)
                            targetLayout.startAnimation(getTranslateAnimation(appBarAnimation))
                        }
                    }
                }
                else -> {
                    when (appBarAnimation) {
                        is AppBarScaleAnimation -> {
                            setScaleSize(verticalOffset, appBarAnimation)
                            targetLayout.startAnimation(getScaleAnimation(appBarAnimation))
                        }
                        is AppBarAlphaAnimation -> {
                            setAlpha(verticalOffset, appBarAnimation)
                            targetLayout.startAnimation(getAlphaAnimation(appBarAnimation))
                        }
                        is AppBarRotateAnimation -> {
                            setRotateDegree(verticalOffset, appBarAnimation)
                            targetLayout.startAnimation(getRotateAnimation(appBarAnimation))
                        }
                        is AppBarTranslateAnimation -> {
                            setTranslatePosition(verticalOffset, appBarAnimation)
                            targetLayout.startAnimation(getTranslateAnimation(appBarAnimation))
                        }
                    }
                }
            }
        })
    }
}