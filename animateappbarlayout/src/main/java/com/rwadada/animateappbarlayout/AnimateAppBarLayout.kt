package com.rwadada.animateappbarlayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.google.android.material.appbar.AppBarLayout

class AnimateAppBarLayout(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {
    companion object {
        val TAG = AnimateAppBarLayout::class.java.simpleName
    }

    var targetLayoutId: Int? = null

    private var targetLayoutHeight: Int = 0
    private var targetLayoutWidth: Int = 0

    fun addAppBarAnimation() {
        addOnOffsetChangedListener(offsetChangedListener)
    }

    private val offsetChangedListener =
        OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val targetLayout: View? = targetLayoutId?.let {
                appBarLayout.findViewById(it)
            }

            if (targetLayout is View) {
                when (verticalOffset) {
                    0 -> {
                        Log.d(TAG, "Expand")
                        targetLayoutHeight = targetLayout.height
                        targetLayoutWidth = targetLayout.width

                        Log.d(TAG, "targetLayoutHeight : $targetLayoutHeight")
                        Log.d(TAG, "targetLayoutWidth : $targetLayoutWidth")
                    }
                    else -> {

                    }
                }
            }
        }
}