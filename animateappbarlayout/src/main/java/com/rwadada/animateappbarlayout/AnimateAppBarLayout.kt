package com.rwadada.animateappbarlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.ScaleAnimation
import com.google.android.material.appbar.AppBarLayout
import com.rwadada.animateappbarlayout.animations.AppBarAlphaAnimation
import com.rwadada.animateappbarlayout.animations.AppBarScaleAppBarAnimation

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

    // 指定されたResourceIDのレイアウトにScaleAnimationを設定
    fun startAnimation(appBarScaleAnimation: AppBarScaleAppBarAnimation, targetResourceId: Int, scrollViewResourceId: Int) {
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
                        scrollViewHeight = scrollView.height
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
    private fun getScaleAnimation(appBarScaleAnimation: AppBarScaleAppBarAnimation): ScaleAnimation {
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

    // ScrollするごとにAnimationに使う値を再計算
    private fun setScaleSize(verticalOffset: Int, appBarScaleAnimation: AppBarScaleAppBarAnimation) {
        prevTargetSizeX = nextTargetSizeX
        nextTargetSizeX =
            Math.abs(verticalOffset) * ((appBarScaleAnimation.toX - appBarScaleAnimation.fromX) / scrollViewHeight) + appBarScaleAnimation.fromX

        prevTargetSizeY = nextTargetSizeY
        nextTargetSizeY =
            Math.abs(verticalOffset) * ((appBarScaleAnimation.toY - appBarScaleAnimation.fromY) / scrollViewHeight) + appBarScaleAnimation.fromY
    }

    // ScaleAnimation に使用する値を初期化
    private fun initScaleSize(appBarScaleAnimation: AppBarScaleAppBarAnimation) {
        prevTargetSizeX = appBarScaleAnimation.fromX
        nextTargetSizeX = appBarScaleAnimation.fromX
        prevTargetSizeY = appBarScaleAnimation.fromY
        nextTargetSizeY = appBarScaleAnimation.fromY
    }

    // 指定されたResourceIDのレイアウトにAlphaAnimationを設定
    fun startAnimation(appBarAlphaAnimation: AppBarAlphaAnimation, targetResourceId: Int, scrollViewResourceId: Int) {
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
                        initAlpha(appBarAlphaAnimation)
                        scrollViewHeight = scrollView.height
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

    private fun initAlpha(appBarAlphaAnimation: AppBarAlphaAnimation){
        prevTargetAlpha = appBarAlphaAnimation.fromAlpha
        nextTargetAlpha = appBarAlphaAnimation.fromAlpha
    }

    private fun getAlphaAnimation(): AlphaAnimation {
        val alphaAnimation = AlphaAnimation(
            prevTargetAlpha,
            nextTargetAlpha
        )
        alphaAnimation.duration = 0
        alphaAnimation.fillAfter = true

        return alphaAnimation
    }

    private fun setAlpha(verticalOffset: Int, appBarAlphaAnimation: AppBarAlphaAnimation) {
        prevTargetAlpha = nextTargetAlpha
        nextTargetAlpha =
                Math.abs(verticalOffset) * ((appBarAlphaAnimation.toAlpha - appBarAlphaAnimation.fromAlpha) / scrollViewHeight) + appBarAlphaAnimation.fromAlpha
    }
}