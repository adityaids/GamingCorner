package com.aditya.gamingcorner.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aditya.core.data.domain.model.GameModel
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val EXTRA_DATA: String = "extra_data"
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.getParcelableExtra<GameModel>(EXTRA_DATA) as GameModel
        initView(intent)
        binding.btnBack.setOnClickListener(this)
    }

    private fun initView(data: GameModel){
        Glide.with(this)
            .load(data.gameImage)
            .error(R.drawable.ic_broken_image)
            .into(binding.gameImage)
        binding.tvDetailTitle.text = data.name
        binding.tvGamesReleased.text = data.released
        binding.tvDescription.text = data.description
        binding.ratingBar.rating = data.rating
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_back -> this.finish()
        }
    }
}