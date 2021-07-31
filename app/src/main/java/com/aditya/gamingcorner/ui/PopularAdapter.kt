package com.aditya.gamingcorner.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.remote.response.GameResponse
import com.aditya.gamingcorner.R
import com.aditya.gamingcorner.databinding.PopularGamesItemBinding
import com.bumptech.glide.Glide
import java.util.ArrayList


class PopularAdapter: RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private var listData = ArrayList<GameModel>()
    var onItemClick: ((GameModel) -> Unit)? = null
    var onButtonFavoritClick: ((GameModel) -> Unit)? = null

    fun setData(newListData: List<GameModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.PopularViewHolder {
        val binding = PopularGamesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.PopularViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class PopularViewHolder(private val
        binding: PopularGamesItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GameModel){
            Glide.with(itemView.context)
                .load(data.gameImage)
                .into(binding.gameImage)
            binding.tvGamesTitle.text = data.name
            binding.tvGamesReleased.text = data.released
            binding.ratingBar.rating = data.rating

            binding.btnFavorite.setOnClickListener {
                if (data.isFavorite) {
                    data.isFavorite = false
                    binding.btnFavorite.icon = ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_border_24)
                } else {
                    data.isFavorite = true
                    binding.btnFavorite.icon = ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_24)
                }
                onButtonFavoritClick?.invoke(listData[adapterPosition])
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}