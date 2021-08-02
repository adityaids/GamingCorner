package com.aditya.gamingcorner.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.core.data.source.Resource
import com.aditya.gamingcorner.databinding.ActivitySearchBinding
import com.aditya.gamingcorner.ui.GameAdapter
import com.aditya.gamingcorner.viewmodel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameAdapter = GameAdapter()
        with(binding.rvSearch){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        binding.edSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.progressBar.visibility = View.VISIBLE
                searchViewModel.getSearchGameResult(query.toString()).observe(this@SearchActivity,{
                    if (it != null) {
                        when (it) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                               gameAdapter.setData(it.data)
                            }
                            is Resource.Error -> {
                                binding.viewError.tvError.text = it.message ?: getString(
                                    com.aditya.gamingcorner.R.string.error)
                            }
                        }
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


    }
}