package com.jsb.android_jetpack_practice

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jsb.android_jetpack_practice.databinding.ActivityMainBinding
import com.jsb.android_jetpack_practice.utils.SimpleLog

class MainActivity : AppCompatActivity(), View.OnClickListener
{
    lateinit var numberViewModel: NumberViewModel
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        numberViewModel = ViewModelProvider(this).get(NumberViewModel::class.java)

        numberViewModel.currentValue.observe(this, Observer {
            SimpleLog.d("MainActivity Vale Changer ->  $it")
            mainBinding.numberText.text = it.toString()
        })

        mainBinding.run {
            plusBtn.setOnClickListener(this@MainActivity)
            minusBtn.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(view: View?) {
        val userInput = mainBinding.inputEdit.text.toString().toInt()

        when(view){
            mainBinding.plusBtn ->
                numberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            mainBinding.minusBtn ->
                numberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }
}