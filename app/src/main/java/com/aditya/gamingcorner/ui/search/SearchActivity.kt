package com.aditya.gamingcorner.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.Resource
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.ActivitySearchBinding
import com.aditya.gamingcorner.ui.GameAdapter
import com.aditya.gamingcorner.ui.detail.DetailActivity
import com.aditya.gamingcorner.viewmodel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameAdapter = GameAdapter()
        with(binding.rvSearch){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        binding.edSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                setTitle(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        gameAdapter.onItemClick = { selectedData ->
            binding.progressBar.visibility = View.VISIBLE
            searchViewModel.getDetailGame(selectedData.id).observe(this, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            toDetail(it.data)
                        }
                        is Resource.Error -> {
                            binding.viewError.tvError.text = it.message ?: getString(R.string.error)
                        }
                    }
                }
            })
        }

        gameAdapter.onButtonFavoritClick = { selectedData ->
            if (selectedData.isFavorite) {
                searchViewModel.updateFavorit(selectedData)
            } else {
                searchViewModel.setFavorit(selectedData)
            }
        }
    }

    private fun setTitle(title: String){
        binding.progressBar.visibility = View.VISIBLE
        searchViewModel.getSearchGameResult(title).observe(this@SearchActivity, { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    gameAdapter.setData(result.data)
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.viewError.tvError.text = result.message ?: getString(
                        com.aditya.gamingcorner.R.string.error)
                }
            }
        })
    }

    private fun toDetail(game: GameModel?){
        binding.progressBar.visibility = View.GONE
        val intent = Intent(this@SearchActivity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DATA, game)
        }
        startActivity(intent)
    }
}