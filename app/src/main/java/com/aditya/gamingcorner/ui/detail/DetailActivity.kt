package com.aditya.gamingcorner.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.ActivityDetailBinding
import com.aditya.gamingcorner.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA: String = "extra_data"
    }
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.getParcelableExtra<GameDetailModel>(EXTRA_DATA) as GameDetailModel
        initView(intent)
    }

    private fun initView(data: GameDetailModel){
        Glide.with(this)
            .load(data.gameImage)
            .into(binding.gameImage)
        binding.tvDetailTitle.text = data.name
        binding.tvGamesReleased.text = data.released
        binding.tvDescription.text = data.description
    }
}