[![](https://jitpack.io/v/rwadada/AnimateAppBarLayout.svg)](https://jitpack.io/#rwadada/AnimateAppBarLayout)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
# AnimateAppBarLayout
Layout to add animation to the scaling of AppbarLayout  

How do I use it?
---

### Setup  
##### Dependencies  
build.gradle(root)  
```groovy  
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
  
build.gradle(module)
```groovy
dependencies {
    implementation 'com.google.android.material:material:1.0.0'
    implementation 'com.github.rwadada:AnimateAppBarLayout:1.0.0-beta1'
}
```

### Functions
##### Layout
ex.
```xml  
<androidx.coordinatorlayout.widget.CoordinatorLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

    <com.rwadada.animateappbarlayout.AnimateAppBarLayout
            android:id="@+id/appbar_layout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

        <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:title="@string/app_name"
                app:layout_scrollFlags="scroll|enterAlways"/>
      </com.rwadada.animateappbarlayout.AnimateAppBarLayout>

    <androidx.recyclerview.widget.RecyclerView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior"/>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

##### ScaleAnimation  
```kotlin
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
```

##### AlphaAnimation
```kotlin
val appBarAlphaAnimation = AppBarAlphaAnimation(
            fromAlpha = 1.0f,
            toAlpha = 0.0f
        )
```

##### RotateAnimation
```kotlin
val appBarRotateAnimation = AppBarRotateAnimation(
            fromDegree = 0.0f,
            toDegree = 360.0f,
            pivotXType = Animation.RELATIVE_TO_SELF,
            pivotXVal = 0.0f,
            pivotYType = Animation.RELATIVE_TO_SELF,
            pivotYVal = 0.0f
        )
```

##### TranslateAnimation
```kotlin
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
```

##### AnimationSets
```kotlin
val appBarAnimationSet = AppBarAnimationSet()
        appBarAnimationSet.addAnimation(appBarScaleAnimation)
        appBarAnimationSet.addAnimation(appBarAlphaAnimation)
        appBarAnimationSet.addAnimation(appBarRotateAnimation)
        appBarAnimationSet.addAnimation(appBarTranslateAnimation)
```

##### setAppBarAnimation
```kotlin
appBarLayout.setAnimation(appBarScaleAnimation = appBarScaleAnimation,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar)
            
appBarLayout.setAnimation(appBarAlphaAnimation = appBarAlphaAnimation,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar)
            
appBarLayout.setAnimation(appBarRotateAnimation = appBarRotateAnimation,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar)
            
appBarLayout.setAnimation(appBarTranslateAnimation = appBarTranslateAnimation,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar)
            
appBarLayout.setAnimation(appBarAnimationSet = appBarAnimationSet,
            targetResourceId = R.id.toolbar,
            scrollViewResourceId = R.id.toolbar)
```

Developed By
-------
Ryosuke Wada (rwadada)

License
-------

    Copyright 2019 rwadada

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
