package com.jsb.android_jetpack_practice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jsb.android_jetpack_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var numberViewModel: NumberViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        numberViewModel = ViewModelProvider(this).get(NumberViewModel::class.java)

        mainBinding.run {
            lifecycleOwner = this@MainActivity
            viewModel = numberViewModel
        }
    }
}