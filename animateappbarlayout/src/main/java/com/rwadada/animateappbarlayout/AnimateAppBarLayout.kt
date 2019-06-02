package com.rwadada.animateappbarlayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.ScaleAnimation
import com.google.android.material.appbar.AppBarLayout
import com.rwadada.animateappbarlayout.animations.AppBarScaleAppBarAnimation

class AnimateAppBarLayout(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {
    companion object {
        val TAG = AnimateAppBarLayout::class.java.simpleName
    }

    private var targetLayoutId: Int? = null

    private var targetLayoutHeight: Int = 0

    private var prevTargetSizeX: Float = 0.0f
    private var nextTargetSizeX: Float = 0.0f
    private var prevTargetSizeY: Float = 0.0f
    private var nextTargetSizeY: Float = 0.0f


    // 指定されたResourceIDのレイアウトにAnimationを設定
    fun startAnimation(appBarScaleAnimation: AppBarScaleAppBarAnimation, resourceId: Int) {
        targetLayoutId = resourceId
        lateinit var targetLayout: View
        addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when (verticalOffset) {
                0 -> {
                    Log.d(TAG, "Expand")

                    targetLayout = appBarLayout!!.findViewById(resourceId)

                    if (prevTargetSizeX == 0.0f &&
                        nextTargetSizeX == 0.0f &&
                        prevTargetSizeY == 0.0f &&
                        nextTargetSizeY == 0.0f
                    ) {
                        initScaleSize(appBarScaleAnimation)
                        targetLayoutHeight = targetLayout.height
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
            Math.abs(verticalOffset) * ((appBarScaleAnimation.toX - appBarScaleAnimation.fromX) / targetLayoutHeight) + appBarScaleAnimation.fromX

        prevTargetSizeY = nextTargetSizeY
        nextTargetSizeY =
            Math.abs(verticalOffset) * ((appBarScaleAnimation.toY - appBarScaleAnimation.fromY) / targetLayoutHeight) + appBarScaleAnimation.fromY
    }


    // ScaleAnimation に使用する値を初期化
    private fun initScaleSize(appBarScaleAnimation: AppBarScaleAppBarAnimation) {
        prevTargetSizeX = appBarScaleAnimation.fromX
        nextTargetSizeX = appBarScaleAnimation.fromX
        prevTargetSizeY = appBarScaleAnimation.fromY
        nextTargetSizeY = appBarScaleAnimation.fromY
    }
}