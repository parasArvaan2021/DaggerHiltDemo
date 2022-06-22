package com.example.daggerhiltdemo.design

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.daggerhiltdemo.R
import com.example.daggerhiltdemo.base.DataBindingActivity
import com.example.daggerhiltdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private val mainViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this


            mainViewModel.mainResponse.observe(this) {
//                mainViewModel.setData(it)
                binding.viewModelXml=it[0]
                Log.e("TAG", "onCreateResponse: $it")
        }
    }
}