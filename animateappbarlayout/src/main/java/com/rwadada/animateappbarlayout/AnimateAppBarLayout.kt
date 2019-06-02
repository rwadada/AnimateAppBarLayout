package com.rwadada.animateappbarlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.google.android.material.appbar.AppBarLayout
import com.rwadada.animateappbarlayout.animations.AppBarAlphaAnimation
import com.rwadada.animateappbarlayout.animations.AppBarRotateAnimation
import com.rwadada.animateappbarlayout.animations.AppBarScaleAnimation
import com.rwadada.animateappbarlayout.animations.AppBarTranslateAnimation

class AnimateAppBarLayout(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {
    companion object {
        val TAG = AnimateAppBarLayout::class.java.simpleName
    }

    private var scrollViewHeight: Int = 0

    private var prevTargetSizeX: Float = 0.0f
    private var nextTargetSizeX: Float = 0.0f
    private var prevTargetSizeY: Float = 0.0f
    private var nextTargetSizeY: Float = 0.0f

    private var prevTargetAlpha: Float = 0.0f
    private var nextTargetAlpha: Float = 0.0f

    private var prevTargetDegree: Float = 0.0f
    private var nextTargetDegree: Float = 0.0f

    private var prevTranslateX: Float = 0.0f
    private var nextTranslateX: Float = 0.0f
    private var prevTranslateY: Float = 0.0f
    private var nextTranslateY: Float = 0.0f

    // 指定されたResourceIDのレイアウトにScaleAnimationを設定
    fun startAnimation(
        appBarScaleAnimation:
        AppBarScaleAnimation,
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

                    if (prevTargetSizeX == 0.0f &&
                        nextTargetSizeX == 0.0f &&
                        prevTargetSizeY == 0.0f &&
                        nextTargetSizeY == 0.0f
                    ) {
                        initScaleSize(appBarScaleAnimation)
                        if (scrollViewHeight == 0) {
                            scrollViewHeight = scrollView.height
                        }
                    } else {
                        setScaleSize(verticalOffset, appBarScaleAnimation)
                        targetLayout.startAnimation(getScaleAnimation(appBarScaleAnimation))
                    }
                }
                else -> {
                    setScaleSize(verticalOffset, appBarScaleAnimation)
                    targetLayout.startAnimation(getScaleAnimation(appBarScaleAnimation))
                }
            }
        })
    }

    // duration0 fillAfter:trueでScaleAnimationを作成
    private fun getScaleAnimation(appBarScaleAnimation: AppBarScaleAnimation): ScaleAnimation {
        val scaleAnimation = ScaleAnimation(
            prevTargetSizeX,
            nextTargetSizeX,
            prevTargetSizeY,
            nextTargetSizeY,
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
        scaleAnimation: AppBarScaleAnimation
    ) {
        prevTargetSizeX = nextTargetSizeX
        nextTargetSizeX =
            Math.abs(verticalOffset) * ((scaleAnimation.toX - scaleAnimation.fromX) / scrollViewHeight) + scaleAnimation.fromX

        prevTargetSizeY = nextTargetSizeY
        nextTargetSizeY =
            Math.abs(verticalOffset) * ((scaleAnimation.toY - scaleAnimation.fromY) / scrollViewHeight) + scaleAnimation.fromY
    }

    // ScaleAnimation に使用する値を初期化
    private fun initScaleSize(scaleAnimation: AppBarScaleAnimation) {
        prevTargetSizeX = scaleAnimation.fromX
        nextTargetSizeX = scaleAnimation.fromX
        prevTargetSizeY = scaleAnimation.fromY
        nextTargetSizeY = scaleAnimation.fromY
    }

    // 指定されたResourceIDのレイアウトにAlphaAnimationを設定
    fun startAnimation(
        appBarAlphaAnimation: AppBarAlphaAnimation,
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

                    if (prevTargetAlpha == 0.0f &&
                        nextTargetAlpha == 0.0f
                    ) {
                        initAlpha(appBarAlphaAnimation)
                        if (scrollViewHeight == 0) {
                            scrollViewHeight = scrollView.height
                        }
                    } else {
                        setAlpha(verticalOffset, appBarAlphaAnimation)
                        targetLayout.startAnimation(getAlphaAnimation())
                    }
                }
                else -> {
                    setAlpha(verticalOffset, appBarAlphaAnimation)
                    targetLayout.startAnimation(getAlphaAnimation())
                }
            }
        })
    }

    // AlphaAnimation に使用する値を初期化
    private fun initAlpha(appBarAlphaAnimation: AppBarAlphaAnimation) {
        prevTargetAlpha = appBarAlphaAnimation.fromAlpha
        nextTargetAlpha = appBarAlphaAnimation.fromAlpha
    }

    // duration0 fillAfter:trueでAlphaAnimationを作成
    private fun getAlphaAnimation(): AlphaAnimation {
        val alphaAnimation = AlphaAnimation(
            prevTargetAlpha,
            nextTargetAlpha
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
        prevTargetAlpha = nextTargetAlpha
        nextTargetAlpha =
            Math.abs(verticalOffset) * ((appBarAlphaAnimation.toAlpha - appBarAlphaAnimation.fromAlpha) / scrollViewHeight) + appBarAlphaAnimation.fromAlpha
    }

    // 指定されたResourceIDのレイアウトにRotateAnimationを設定
    fun startAnimation(
        appBarRotateAnimation: AppBarRotateAnimation,
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

                    if (prevTargetDegree == 0.0f &&
                        nextTargetDegree == 0.0f
                    ) {
                        initRotateDegree(appBarRotateAnimation)
                        if (scrollViewHeight == 0) {
                            scrollViewHeight = scrollView.height
                        }
                    } else {
                        setRotateDegree(verticalOffset, appBarRotateAnimation)
                        targetLayout.startAnimation(getRotateAnimation(appBarRotateAnimation))
                    }
                }
                else -> {
                    setRotateDegree(verticalOffset, appBarRotateAnimation)
                    targetLayout.startAnimation(getRotateAnimation(appBarRotateAnimation))
                }
            }
        })
    }

    // RotateAnimationに使用する値を初期化
    private fun initRotateDegree(appBarRotateAnimation: AppBarRotateAnimation) {
        prevTargetDegree = appBarRotateAnimation.fromDegree
        nextTargetDegree = appBarRotateAnimation.fromDegree
    }

    // ScrollするごとにRotateAnimationに使う値を再計算
    private fun setRotateDegree(
        verticalOffset: Int,
        appBarRotateAnimation: AppBarRotateAnimation
    ) {
        prevTargetDegree = nextTargetDegree
        nextTargetDegree =
            Math.abs(verticalOffset) * ((appBarRotateAnimation.toDegree - appBarRotateAnimation.fromDegree) / scrollViewHeight) + appBarRotateAnimation.fromDegree
    }

    // duration0 fillAfter:trueでRotateAnimationを作成
    private fun getRotateAnimation(appBarRotateAnimation: AppBarRotateAnimation): RotateAnimation {
        val rotateAnimation = RotateAnimation(
            prevTargetDegree,
            nextTargetDegree,
            appBarRotateAnimation.pivotXType,
            appBarRotateAnimation.pivotXVal,
            appBarRotateAnimation.pivotYType,
            appBarRotateAnimation.pivotYVal
        )
        rotateAnimation.duration = 0
        rotateAnimation.fillAfter = true

        return rotateAnimation
    }

    // 指定されたResourceIDのレイアウトにTranslateAnimationを設定
    fun startAnimation(
        appBarTranslateAnimation: AppBarTranslateAnimation,
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

                    if (prevTranslateX == 0.0f &&
                        nextTranslateX == 0.0f &&
                        prevTranslateY == 0.0f &&
                        nextTranslateY == 0.0f
                    ) {
                        initTranslatePosition(appBarTranslateAnimation)
                        if (scrollViewHeight == 0) {
                            scrollViewHeight = scrollView.height
                        }
                    } else {
                        setTranslatePosition(verticalOffset, appBarTranslateAnimation)
                        targetLayout.startAnimation(getTranslateAnimation(appBarTranslateAnimation))
                    }
                }
                else -> {
                    setTranslatePosition(verticalOffset, appBarTranslateAnimation)
                    targetLayout.startAnimation(getTranslateAnimation(appBarTranslateAnimation))
                }
            }
        })
    }

    // TranslateAnimationに使用する値を初期化
    private fun initTranslatePosition(appBarTranslateAnimation: AppBarTranslateAnimation) {
        prevTranslateX = appBarTranslateAnimation.fromXValue
        nextTranslateX = appBarTranslateAnimation.fromXValue
        prevTranslateY = appBarTranslateAnimation.fromYValue
        nextTranslateY = appBarTranslateAnimation.fromYValue
    }

    // ScrollするごとにTranslateAnimationに使う値を再計算
    private fun setTranslatePosition(verticalOffset: Int,
                                     appBarTranslateAnimation: AppBarTranslateAnimation) {
        prevTranslateX = nextTranslateX
        nextTranslateX =
            Math.abs(verticalOffset) * ((appBarTranslateAnimation.toXValue - appBarTranslateAnimation.fromXValue) / scrollViewHeight) + appBarTranslateAnimation.fromXValue

        prevTranslateY = nextTranslateY
        nextTranslateY =
            Math.abs(verticalOffset) * ((appBarTranslateAnimation.toYValue - appBarTranslateAnimation.fromYValue) / scrollViewHeight) + appBarTranslateAnimation.fromYValue
    }

    // duration0 fillAfter:trueでTranslateAnimationを作成
    private fun getTranslateAnimation(appBarTranslateAnimation: AppBarTranslateAnimation): TranslateAnimation {
        val translateAnimation = TranslateAnimation(
            appBarTranslateAnimation.fromXType,
            prevTranslateX,
            appBarTranslateAnimation.toXType,
            nextTranslateX,
            appBarTranslateAnimation.fromYType,
            prevTranslateY,
            appBarTranslateAnimation.toYType,
            nextTranslateY
        )
        translateAnimation.duration = 0
        translateAnimation.fillAfter = true

        return translateAnimation
    }
}