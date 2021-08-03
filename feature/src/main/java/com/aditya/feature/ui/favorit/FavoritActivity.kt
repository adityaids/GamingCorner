package com.aditya.feature.ui.favorit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.feature.databinding.ActivityFavoritBinding
import com.aditya.feature.di.favoritModule
import com.aditya.feature.viewmodel.FavoritViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFavoritBinding
    private val favoritViewModel: FavoritViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoritModule)

        binding
    }
}