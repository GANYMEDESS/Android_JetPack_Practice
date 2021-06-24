package com.jsb.android_jetpack_practice.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jsb.android_jetpack_practice.R
import com.jsb.android_jetpack_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.run {
            lifecycleOwner = this@MainActivity
        }
    }
}