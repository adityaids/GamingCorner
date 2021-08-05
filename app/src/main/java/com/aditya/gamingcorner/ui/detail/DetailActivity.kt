package com.aditya.gamingcorner.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aditya.core.data.domain.model.GameModel
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.ActivityDetailBinding
import com.aditya.gamingcorner.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val EXTRA_DATA: String = "extra_data"
    }
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.getParcelableExtra<GameModel>(EXTRA_DATA) as GameModel
        initView(intent)
        binding.btnBack.setOnClickListener(this)
        binding.btnFavorite.setOnClickListener(this)
    }

    private fun initView(data: GameModel){
        Glide.with(this)
            .load(data.gameImage)
            .into(binding.gameImage)
        binding.tvDetailTitle.text = data.name
        binding.tvGamesReleased.text = data.released
        binding.tvDescription.text = data.description
        binding.ratingBar.rating = data.rating
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_back -> this.finish()
            R.id.btn_favorite -> {

            }
        }
    }
}