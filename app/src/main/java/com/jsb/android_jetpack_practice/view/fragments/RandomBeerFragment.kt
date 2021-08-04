package com.jsb.android_jetpack_practice.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jsb.android_jetpack_practice.databinding.FragmentRandomBeerBinding

class RandomBeerFragment: Fragment()
{
    private lateinit var mBinding: FragmentRandomBeerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentRandomBeerBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}