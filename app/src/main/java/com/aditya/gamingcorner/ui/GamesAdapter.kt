package com.aditya.gamingcorner.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.remote.response.GameResponse
import com.aditya.gamingcorner.databinding.GamesItemBinding
import java.util.ArrayList

class GamesAdapter: RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    private var listData = ArrayList<GameModel>()
    var onItemClick: ((GameModel) -> Unit)? = null

    fun setData(newListData: List<GameModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        val binding = GamesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class GameViewHolder(
        private val binding: GamesItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GameModel){

        }

        init {

        }
    }
}