package com.aditya.gamingcorner.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.core.data.source.Resource
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.ActivityMainBinding
import com.aditya.gamingcorner.ui.LatestAdapter
import com.aditya.gamingcorner.ui.PopularAdapter
import com.aditya.gamingcorner.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewmodel: MainViewModel by viewModel()

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

        viewmodel.popularGameList.observe(this,{
            if (it != null) {
                when (it) {
                    is Resource.Loading -> activityMainBinding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
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

        viewmodel.latestGameList.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> activityMainBinding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        activityMainBinding.progressBar.visibility = View.GONE
                        latestAdapter.setData(it.data)
                    }
                    is Resource.Error -> {
                        activityMainBinding.progressBar.visibility = View.GONE
                        activityMainBinding.viewError.root.visibility = View.VISIBLE
                        activityMainBinding.viewError.tvError.text = it.message ?: getString(R.string.error)
                    }
                }
            }
        })
    }
}