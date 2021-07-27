package com.aditya.gamingcorner.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}