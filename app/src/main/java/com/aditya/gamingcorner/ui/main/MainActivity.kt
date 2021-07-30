package com.aditya.gamingcorner.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.source.Resource
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.ActivityMainBinding
import com.aditya.gamingcorner.ui.LatestAdapter
import com.aditya.gamingcorner.ui.PopularAdapter
import com.aditya.gamingcorner.ui.detail.DetailActivity
import com.aditya.gamingcorner.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val popularAdapter = PopularAdapter()
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
            viewModel.getDetailGame(selectedData.id).observe(this, {
                Log.d("id", it.data?.id.toString())
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
    }

    private fun toDetail(game: GameDetailModel?){
        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DATA, game)
        }
        startActivity(intent)
    }
}