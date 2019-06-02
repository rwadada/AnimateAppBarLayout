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

        val appBarScaleAnimation = AppBarScaleAnimation(
            fromX = 1.0f,
            toX = 0.0f,
            fromY = 1.0f,
            toY = 0.0f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.5f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 1.0f
        )
        val appBarAlphaAnimation = AppBarAlphaAnimation(
            fromAlpha = 1.0f,
            toAlpha = 0.0f
        )
        val appBarRotateAnimation = AppBarRotateAnimation(
            fromDegree = 0.0f,
            toDegree = 360.0f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.0f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 0.0f
        )
        val appBarTranslateAnimation = AppBarTranslateAnimation(
            fromXType = Animation.RELATIVE_TO_PARENT,
            fromXValue = 0.0f,
            toXType = Animation.RELATIVE_TO_PARENT,
            toXValue = 0.95f,
            fromYType = Animation.RELATIVE_TO_PARENT,
            fromYValue = 0.0f,
            toYType = Animation.RELATIVE_TO_PARENT,
            toYValue = 1.0f
        )

        val appBarAnimationSet = AppBarAnimationSet()
        appBarAnimationSet.addAnimation(appBarScaleAnimation)
        appBarAnimationSet.addAnimation(appBarAlphaAnimation)
        appBarAnimationSet.addAnimation(appBarRotateAnimation)
        appBarAnimationSet.addAnimation(appBarTranslateAnimation)

        appBarLayout.setAnimation(appBarAnimationSet = appBarAnimationSet,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar)
    }
}
