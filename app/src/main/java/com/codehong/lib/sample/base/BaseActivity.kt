package com.codehong.lib.sample.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.codehong.lib.sample.R
import com.codehong.library.widget.extensions.applyActivityCloseAnim
import com.codehong.library.widget.extensions.applyActivityOpenAnim

abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyActivityOpenAnim(R.anim.slide_right_in)

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this@BaseActivity.handleOnBackPressed()
            }
        })
    }

    override fun finish() {
        super.finish()
        applyActivityCloseAnim(R.anim.slide_right_out)
    }

    open fun handleOnBackPressed() {
        finish()
    }
}
