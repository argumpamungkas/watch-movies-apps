package com.argumpamungkas.moviesapps.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumpamungkas.moviesapps.databinding.LayoutItemLinearBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieDetailResponse
import com.argumpamungkas.moviesapps.model.ItemMovieSearchModel
import com.argumpamungkas.moviesapps.util.imageFormat
import com.argumpamungkas.moviesapps.util.shimmer
import com.argumpamungkas.moviesapps.util.voteFormat
import com.bumptech.glide.Glide

class AdapterListFavorite(private val listItem : ArrayList<ItemMovieDetailResponse>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<AdapterListFavorite.ViewHolder>(){

    inner class ViewHolder(val binding: LayoutItemLinearBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutItemLinearBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvOverview.text = item.overview

        holder.binding.tvVote.text = voteFormat(item.vote_average)

        imageFormat(item.poster_path, holder.binding.ivPoster)

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount() = listItem.size

    interface OnAdapterListener {
        fun onClick(item: ItemMovieDetailResponse)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItemMovie(item: List<ItemMovieDetailResponse>) {
        listItem.clear()
        listItem.addAll(item)
        notifyDataSetChanged()
    }

    fun clear() {
        listItem.clear()
    }
}