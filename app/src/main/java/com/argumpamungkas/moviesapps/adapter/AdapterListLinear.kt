package com.argumpamungkas.moviesapps.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumpamungkas.moviesapps.R
import com.argumpamungkas.moviesapps.databinding.LayoutItemLinearBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieSearchModel
import com.argumpamungkas.moviesapps.util.imageFormat
import com.argumpamungkas.moviesapps.util.shimmer
import com.argumpamungkas.moviesapps.util.voteFormat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class AdapterListLinear(
    private val listItem: ArrayList<ItemMovieSearchModel>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<AdapterListLinear.ViewHolder>() {

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

        if (item.poster_path == "originalnull") {
            holder.binding.ivPoster.setImageResource(R.drawable.bg_bottom_nav)
        } else {
            val poster = Constant.POSTER_PATH + item.poster_path
            Glide.with(holder.binding.ivPoster)
                .load(poster)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .placeholder(shimmer())
                .into(holder.binding.ivPoster)
        }

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount() = listItem.size

    interface OnAdapterListener {
        fun onClick(item: ItemMovieSearchModel)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItemMovie(item: List<ItemMovieSearchModel>) {
        listItem.clear()
        listItem.addAll(item)
        notifyDataSetChanged()
    }

    fun setAddMovie(item: List<ItemMovieSearchModel>) {
        listItem.addAll(item)
        notifyDataSetChanged()
    }

    fun clear() {
        listItem.clear()
    }
}