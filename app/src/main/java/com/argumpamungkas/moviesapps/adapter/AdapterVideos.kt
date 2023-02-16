package com.argumpamungkas.moviesapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumpamungkas.moviesapps.databinding.LayoutItemVideosBinding
import com.argumpamungkas.moviesapps.model.VideosMovieModel

class AdapterVideos(private val listTrailer: ArrayList<VideosMovieModel>, private val listener: OnAdapterListener):
RecyclerView.Adapter<AdapterVideos.ViewHolder>(){

    inner class ViewHolder(val binding: LayoutItemVideosBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutItemVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listTrailer[position]
        holder.binding.tvTitle.text = item.name
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount() = listTrailer.size

    interface OnAdapterListener {
        fun onClick(item: VideosMovieModel)
    }

    fun setItemTrailer(item: List<VideosMovieModel>){
        listTrailer.clear()
        listTrailer.addAll(item)
        notifyDataSetChanged()
    }
}