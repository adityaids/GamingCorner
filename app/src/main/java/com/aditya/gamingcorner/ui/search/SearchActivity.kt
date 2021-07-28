package com.aditya.gamingcorner.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.gamingcorner.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}