package com.argumpamungkas.moviesapps.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumpamungkas.moviesapps.databinding.LayoutItemBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieModel
import com.argumpamungkas.moviesapps.util.imageFormat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class AdapterListMovie(
    private val listItem: ArrayList<ItemMovieModel>,
    private val listener: OnAdapterListener
) : RecyclerView.Adapter<AdapterListMovie.ViewHolder>() {

    class ViewHolder(val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]

        holder.binding.tvTitleItem.text = item.title

        imageFormat(item.poster_path, holder.binding.ivPosterPortrait)

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount() = listItem.size

    interface OnAdapterListener {
        fun onClick(item: ItemMovieModel)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(item: List<ItemMovieModel>) {
        listItem.clear()
        listItem.addAll(item)
        notifyDataSetChanged()
    }
}