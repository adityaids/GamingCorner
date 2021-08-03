package com.aditya.feature.ui.favorit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.feature.databinding.ActivityFavoritBinding

class FavoritActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFavoritBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}