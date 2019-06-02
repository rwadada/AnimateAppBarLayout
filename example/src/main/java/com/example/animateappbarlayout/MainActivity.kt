package com.example.animateappbarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.rwadada.animateappbarlayout.AnimateAppBarLayout
import com.rwadada.animateappbarlayout.animations.AppBarAlphaAnimation
import com.rwadada.animateappbarlayout.animations.AppBarRotateAnimation
import com.rwadada.animateappbarlayout.animations.AppBarScaleAnimation
import com.rwadada.animateappbarlayout.animations.AppBarTranslateAnimation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBarLayout: AnimateAppBarLayout = findViewById(R.id.appbar_layout)

        val appBarScaleAnimation = AppBarScaleAnimation(
            fromX = 1.0f,
            toX = 0.7f,
            fromY = 1.0f,
            toY = 0.7f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.5f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 1.0f
        )
        appBarLayout.startAnimation(
            appBarScaleAnimation = appBarScaleAnimation,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar
        )

        val appBarAlphaAnimation = AppBarAlphaAnimation(
            fromAlpha = 1.0f,
            toAlpha = 0.0f
        )
        appBarLayout.startAnimation(
            appBarAlphaAnimation = appBarAlphaAnimation,
            targetResourceId = R.id.text_view,
            scrollViewResourceId = R.id.toolbar
        )

        val appBarRotateAnimation = AppBarRotateAnimation(
            fromDegree = 0.0f,
            toDegree = 360.0f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.5f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 0.5f
        )

        appBarLayout.startAnimation(
            appBarRotateAnimation = appBarRotateAnimation,
            targetResourceId = R.id.image_view,
            scrollViewResourceId = R.id.toolbar
        )

        val appBarTranslateAnimation = AppBarTranslateAnimation(
            fromXType = Animation.RELATIVE_TO_PARENT,
            fromXValue = 0.0f,
            toXType = Animation.RELATIVE_TO_PARENT,
            toXValue = 0.95f,
            fromYType = Animation.RELATIVE_TO_SELF,
            fromYValue = 0.0f,
            toYType = Animation.RELATIVE_TO_SELF,
            toYValue = 0.0f
        )

        appBarLayout.startAnimation(
            appBarTranslateAnimation = appBarTranslateAnimation,
            targetResourceId = R.id.dot,
            scrollViewResourceId = R.id.toolbar
        )
    }
}
