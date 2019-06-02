package com.example.animateappbarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.rwadada.animateappbarlayout.AnimateAppBarLayout
import com.rwadada.animateappbarlayout.animations.AppBarAlphaAnimation
import com.rwadada.animateappbarlayout.animations.AppBarScaleAnimation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBarLayout: AnimateAppBarLayout = findViewById(R.id.appbar_layout)

        val appBarScaleAnimation = AppBarScaleAnimation(
            1.0f,
            0.7f,
            1.0f,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            1.0f
        )
        appBarLayout.startAnimation(
            scaleAnimation = appBarScaleAnimation,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar
        )

        val appBarAlphaAnimation = AppBarAlphaAnimation(
            1.0f,
            0.0f
        )
        appBarLayout.startAnimation(
            appBarAlphaAnimation = appBarAlphaAnimation,
            targetResourceId = R.id.sub_content,
            scrollViewResourceId = R.id.toolbar
        )
    }
}
