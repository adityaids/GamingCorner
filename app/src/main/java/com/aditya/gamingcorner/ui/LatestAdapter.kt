package com.aditya.gamingcorner.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.core.data.source.remote.response.GameResponse
import com.aditya.gamingcorner.databinding.LatestItemBinding
import com.bumptech.glide.Glide
import java.util.ArrayList

class LatestAdapter: RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    private var listData = ArrayList<GameResponse>()
    var onItemClick: ((GameResponse) -> Unit)? = null

    fun setData(newListData: List<GameResponse>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LatestViewHolder {
        val binding = LatestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size


    inner class LatestViewHolder(
        private val binding: LatestItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GameResponse){
            Glide.with(itemView.context)
                .load(data.image)
                .into(binding.gamesImage)
            binding.tvGamesTitle.text = data.name
            binding.tvGamesReleased.text = data.released
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}