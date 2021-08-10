package com.jsb.android_jetpack_practice.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jsb.android_jetpack_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

    }
}