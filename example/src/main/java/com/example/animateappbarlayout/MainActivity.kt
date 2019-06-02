package com.example.animateappbarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.rwadada.animateappbarlayout.AnimateAppBarLayout
import com.rwadada.animateappbarlayout.animations.AppBarAlphaAnimation
import com.rwadada.animateappbarlayout.animations.AppBarScaleAppBarAnimation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBarLayout: AnimateAppBarLayout = findViewById(R.id.appbar_layout)
        appBarLayout.startAnimation(
            AppBarScaleAppBarAnimation(
                1.0f,
                0.7f,
                1.0f,
                0.7f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                1.0f
            ),
            R.id.toolbar,
            R.id.toolbar
        )

        appBarLayout.startAnimation(
            AppBarAlphaAnimation(
                1.0f,
                0.0f
            ),
            R.id.toolbar,
            R.id.toolbar
        )
    }
}
