package com.jeroenmols.navigationexclusionbug

import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<SeekBar>(R.id.seekbar).doOnLayout {
            if (Build.VERSION.SDK_INT >= 29) {
                val right = Rect(it.left,it.top, 100, it.bottom)
                val left = Rect(it.right - 100,it.top, it.right, it.bottom)


                // Setting on view DOESNT WORK
                it.systemGestureExclusionRects = listOf(right, left)

                // Setting on parent WORKS
                // findViewById<ConstraintLayout>(R.id.root).systemGestureExclusionRects = listOf(right, left)
            }
        }
    }
}
