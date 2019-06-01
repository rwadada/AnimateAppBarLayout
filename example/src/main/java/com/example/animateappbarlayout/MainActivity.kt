package com.example.animateappbarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rwadada.animateappbarlayout.AnimateAppBarLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBarLayout: AnimateAppBarLayout = findViewById(R.id.appbar_layout)
        appBarLayout.targetLayoutId = R.id.toolbar
        appBarLayout.addAppBarAnimation()
    }
}
