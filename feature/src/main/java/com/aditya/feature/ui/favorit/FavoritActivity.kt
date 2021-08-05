package com.aditya.feature.ui.favorit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.Resource
import com.aditya.feature.databinding.ActivityFavoritBinding
import com.aditya.feature.di.favoritModule
import com.aditya.feature.viewmodel.FavoritViewModel
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.ui.detail.DetailActivity
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

        val favoritAdapter = FavoritAdapter()

        with(binding.rvFavorit){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoritAdapter
        }

        favoritViewModel.getFavoritList.observe(this, {
            if (it != null) {
                favoritAdapter.setData(it)
            } else {
                binding.tvNothing.visibility = View.VISIBLE
            }
        })

        favoritAdapter.onItemClick = { selectedData ->
            binding.progressBar.visibility = View.VISIBLE
            favoritViewModel.getDetailGame(selectedData.id).observe(this,{
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            toDetail(it.data)
                        }
                        is Resource.Error -> {
                            binding.featureError.tvError.text = it.message ?: getString(R.string.error)
                        }
                    }
                }
            })
        }

        favoritAdapter.onButtonDeleteClick = { selectedData ->
            favoritViewModel.updateFavorit(selectedData)
        }
    }

    private fun toDetail(game: GameModel?){
        binding.progressBar.visibility = View.GONE
        val intent = Intent(this@FavoritActivity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DATA, game)
        }
        startActivity(intent)
    }
}