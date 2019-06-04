package com.example.animateappbarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rwadada.animateappbarlayout.AnimateAppBarLayout
import com.rwadada.animateappbarlayout.animations.*

class MainActivity : AppCompatActivity() {

    private val releaseNote = listOf<String>(
        "ver 0.0.1 : released on 2019/06/02",
        "ver 0.0.2 : released on 2019/06/02",
        "ver 1.0.0-beta1 : released on 2019/06/02",
        "ver 1.0.0 : released on 2019/06/04",
        "ver 1.0.1 : released on 2019/06/04",
        "...",
        "...",
        "...",
        "...",
        "...",
        "...",
        "...",
        "...",
        "...",
        "...",
        "..."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAnimations()

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val linerLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linerLayoutManager

        val recyclerViewAdapter = RecyclerViewAdapter(releaseNote)
        recyclerView.adapter = recyclerViewAdapter
    }


    // Animationを設定
    private fun initAnimations() {
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
        val appBarScaleAnimation3 = AppBarScaleAnimation(
            fromX = 1.0f,
            toX = 1.0f,
            fromY = 0.0f,
            toY = 1.0f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.5f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 0.0f
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
        appBarAnimationSet3.addAnimation(appBarScaleAnimation3)

        appBarLayout.setAnimation(appBarAnimationSet3, R.id.text_view, R.id.toolbar)

        appBarLayout.setAnimation(appBarTranslateAnimation, R.id.rocket, R.id.toolbar)
    }
}
