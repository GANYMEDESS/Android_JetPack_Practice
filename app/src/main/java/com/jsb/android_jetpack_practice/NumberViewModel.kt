package com.jsb.android_jetpack_practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jsb.android_jetpack_practice.utils.SimpleLog

enum class ActionType
{
    PLUS,
    MINUS
}

// 데이터 변경하는 부분을 설정
class NumberViewModel: ViewModel()
{
    // 변경 가능한 데이터 - Mutable LiveData
    // 읽기 전용 데이터 - LiveData

    // 내부 설정 자료형은 Mutable -> 변경가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기 값
    init {
        SimpleLog.d("NumberViewModel - 생성자 호출 ")
        _currentValue.value = 0
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    fun updateValue(actionType: ActionType, input: Int) {
        when(actionType){
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}