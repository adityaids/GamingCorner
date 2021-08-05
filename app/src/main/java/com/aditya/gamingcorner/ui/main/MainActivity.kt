package com.aditya.gamingcorner.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.Resource
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.ActivityMainBinding
import com.aditya.gamingcorner.ui.LatestAdapter
import com.aditya.gamingcorner.ui.GameAdapter
import com.aditya.gamingcorner.ui.detail.DetailActivity
import com.aditya.gamingcorner.ui.search.SearchActivity
import com.aditya.gamingcorner.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val popularAdapter = GameAdapter()
        val latestAdapter = LatestAdapter()

        with(activityMainBinding.rvPopular){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularAdapter
        }
        with(activityMainBinding.rvLatest){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = latestAdapter
        }

        popularAdapter.onItemClick = { selectedData ->
            activityMainBinding.progressBar.visibility = View.VISIBLE
            viewModel.getDetailGame(selectedData.id).observe(this, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> activityMainBinding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            toDetail(it.data)
                        }
                        is Resource.Error -> {
                            activityMainBinding.viewError.tvError.text = it.message ?: getString(R.string.error)
                        }
                    }
                }
            })
        }

        latestAdapter.onItemClick = { selectedData->
            activityMainBinding.progressBar.visibility = View.VISIBLE
            viewModel.getDetailGame(selectedData.id).observe(this, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> activityMainBinding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            toDetail(it.data)
                        }
                        is Resource.Error -> {
                            activityMainBinding.viewError.tvError.text = it.message ?: getString(R.string.error)
                        }
                    }
                }
            })
        }

        popularAdapter.onButtonFavoritClick = { data->
            if (data.isFavorite) {
                viewModel.updateFavorit(data)
            } else {
                viewModel.setFavorit(data)
            }
        }

        latestAdapter.onButtonFavoritClick = { data ->
            if (data.isFavorite) {
                viewModel.updateFavorit(data)
            } else {
                viewModel.setFavorit(data)
            }
        }

        viewModel.popularGameList.observe(this,{
            if (it != null) {
                when (it) {
                    is Resource.Loading -> activityMainBinding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        activityMainBinding.tvTitle.visibility = View.VISIBLE
                        activityMainBinding.progressBar.visibility = View.GONE
                        popularAdapter.setData(it.data)
                    }
                    is Resource.Error -> {
                        activityMainBinding.progressBar.visibility = View.GONE
                        activityMainBinding.viewError.root.visibility = View.VISIBLE
                        activityMainBinding.viewError.tvError.text = it.message ?: getString(R.string.error)
                    }
                }
            }
        })

        viewModel.latestGameList.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> activityMainBinding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        activityMainBinding.tvLatest.visibility = View.VISIBLE
                        latestAdapter.setData(it.data)
                    }
                    is Resource.Error -> {
                        activityMainBinding.viewError.tvError.text = it.message ?: getString(R.string.error)
                    }
                }
            }
        })

        activityMainBinding.btnSearch.setOnClickListener(this)
        activityMainBinding.btnFavorite.setOnClickListener(this)
    }

    private fun toDetail(game: GameModel?){
        activityMainBinding.progressBar.visibility = View.GONE
        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DATA, game)
        }
        startActivity(intent)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_search -> {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_favorite -> {
                startActivity(Intent(this, Class.forName("com.aditya.feature.ui.favorit.FavoritActivity")))
            }
        }
    }
}