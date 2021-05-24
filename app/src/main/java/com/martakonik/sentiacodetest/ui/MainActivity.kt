package com.martakonik.sentiacodetest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.martakonik.sentiacodetest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}