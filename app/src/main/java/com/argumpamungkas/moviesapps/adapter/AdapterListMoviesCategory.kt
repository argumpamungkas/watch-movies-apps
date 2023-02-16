package com.argumpamungkas.moviesapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumpamungkas.moviesapps.databinding.ItemMoviesCategoryBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieModel
import com.argumpamungkas.moviesapps.util.imageFormat
import com.argumpamungkas.moviesapps.util.shimmer
import com.bumptech.glide.Glide

class AdapterListMoviesCategory(
    private val listItem: ArrayList<ItemMovieModel>,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<AdapterListMoviesCategory.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMoviesCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMoviesCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.binding.tvTitle.text = item.title

        imageFormat(item.poster_path, holder.binding.ivPoster)

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount() = listItem.size

    interface OnAdapterListener {
        fun onClick(item: ItemMovieModel)
    }

    fun setItemMovie(item: List<ItemMovieModel>){
        listItem.clear()
        listItem.addAll(item)
        notifyDataSetChanged()
    }
}