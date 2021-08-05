package com.aditya.feature.ui.favorit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aditya.core.data.domain.model.GameModel
import com.aditya.feature.databinding.FavoritItemBinding
import com.aditya.gamingcorner.R
import com.bumptech.glide.Glide
import java.util.ArrayList

class FavoritAdapter: RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {

    private var listData = ArrayList<GameModel>()
    var onItemClick: ((GameModel) -> Unit)? = null
    var onButtonDeleteClick: ((GameModel) -> Unit)? = null

    fun setData(newListData: List<GameModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): FavoritViewHolder{
        val binding = FavoritItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class FavoritViewHolder(private val binding: FavoritItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: GameModel){
            Glide.with(itemView.context)
                .load(data.gameImage)
                .error(R.drawable.ic_broken_image)
                .into(binding.gameImage)
            binding.tvGamesTitle.text = data.name
            binding.tvGamesReleased.text = data.released
            binding.ratingBar.rating = data.rating

            binding.btnDelete.setOnClickListener {
                data.isFavorite = false
                onButtonDeleteClick?.invoke(data)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}