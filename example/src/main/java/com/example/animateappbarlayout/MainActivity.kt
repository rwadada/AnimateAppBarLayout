package com.example.animateappbarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.rwadada.animateappbarlayout.AnimateAppBarLayout
import com.rwadada.animateappbarlayout.animations.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBarLayout: AnimateAppBarLayout = findViewById(R.id.appbar_layout)

        val appBarScaleAnimation1 = AppBarScaleAnimation(
            fromX = 1.0f,
            toX = 0.0f,
            fromY = 1.0f,
            toY = 0.0f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.5f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 0.5f
        )
        val appBarScaleAnimation2 = AppBarScaleAnimation(
            fromX = 0.0f,
            toX = 1.0f,
            fromY = 0.0f,
            toY = 1.0f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.5f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 0.5f
        )
        val appBarAlphaAnimation1 = AppBarAlphaAnimation(
            fromAlpha = 1.0f,
            toAlpha = 0.0f
        )
        val appBarAlphaAnimation2 = AppBarAlphaAnimation(
            fromAlpha = 0.0f,
            toAlpha = 1.0f
        )
        val appBarRotateAnimation = AppBarRotateAnimation(
            fromDegree = 0.0f,
            toDegree = 360.0f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.5f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 0.5f
        )
        val appBarTranslateAnimation = AppBarTranslateAnimation(
            fromXType = Animation.RELATIVE_TO_PARENT,
            fromXValue = 0.0f,
            toXType = Animation.RELATIVE_TO_PARENT,
            toXValue = -1.0f,
            fromYType = Animation.RELATIVE_TO_PARENT,
            fromYValue = 0.0f,
            toYType = Animation.RELATIVE_TO_PARENT,
            toYValue = 0.0f
        )

        val appBarAnimationSet1 = AppBarAnimationSet()
        appBarAnimationSet1.addAnimation(appBarScaleAnimation1)
        appBarAnimationSet1.addAnimation(appBarAlphaAnimation1)

        appBarLayout.setAnimation(appBarAnimationSet1, R.id.toolbar, R.id.toolbar)

        val appBarAnimationSet2 = AppBarAnimationSet()
        appBarAnimationSet2.addAnimation(appBarScaleAnimation2)
        appBarAnimationSet2.addAnimation(appBarRotateAnimation)

        appBarLayout.setAnimation(appBarAnimationSet2, R.id.image_view, R.id.toolbar)

        val appBarAnimationSet3 = AppBarAnimationSet()
        appBarAnimationSet3.addAnimation(appBarAlphaAnimation2)
        appBarAnimationSet3.addAnimation(appBarScaleAnimation2)

        appBarLayout.setAnimation(appBarAnimationSet3, R.id.text_view, R.id.toolbar)

        appBarLayout.setAnimation(appBarTranslateAnimation, R.id.rocket, R.id.toolbar)
    }
}
