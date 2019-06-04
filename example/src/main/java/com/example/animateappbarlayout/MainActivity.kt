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
            pivotYVal = 0.5f
        )
        val appBarAlphaAnimation = AppBarAlphaAnimation(
            fromAlpha = 1.0f,
            toAlpha = 0.0f
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
            toXValue = 0.95f,
            fromYType = Animation.RELATIVE_TO_PARENT,
            fromYValue = 0.0f,
            toYType = Animation.RELATIVE_TO_PARENT,
            toYValue = 1.0f
        )

        val appBarAnimationSet1 = AppBarAnimationSet()
        appBarAnimationSet1.addAnimation(appBarScaleAnimation)
        appBarAnimationSet1.addAnimation(appBarAlphaAnimation)

        appBarLayout.setAnimation(appBarAnimationSet = appBarAnimationSet1,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar)

        val appBarAnimationSet2 = AppBarAnimationSet()
        appBarAnimationSet2.addAnimation(appBarScaleAnimation)
        appBarAnimationSet2.addAnimation(appBarRotateAnimation)

        appBarLayout.setAnimation(appBarAnimationSet = appBarAnimationSet2,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar)

        appBarLayout.setAnimation(appBarAnimationSet2, R.id.image_view, R.id.toolbar)
    }
}
