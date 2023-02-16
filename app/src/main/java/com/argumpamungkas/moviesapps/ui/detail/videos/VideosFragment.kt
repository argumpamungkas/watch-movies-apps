package com.argumpamungkas.moviesapps.ui.detail.videos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.argumpamungkas.moviesapps.adapter.AdapterVideos
import com.argumpamungkas.moviesapps.databinding.FragmentVideosBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.VideosMovieModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleVideosFragment = module {
    factory { VideosFragment() }
}

class VideosFragment : Fragment() {

    private lateinit var binding: FragmentVideosBinding
    private val viewModel: VideosViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterVideos = AdapterVideos(arrayListOf(), object: AdapterVideos.OnAdapterListener{
            override fun onClick(item: VideosMovieModel) {
                startActivity(Intent(requireContext(), PlayVideoMovie::class.java)
                    .putExtra(Constant.KEY_VIDEOS, item.key)
                )
            }
        })

        binding.rvTrailer.adapter = adapterVideos
        viewModel.videosMovie.observe(viewLifecycleOwner){
            if (it.results?.isNotEmpty()!!){
                adapterVideos.setItemTrailer(it.results)
            } else {
                binding.rvTrailer.visibility = View.GONE
                binding.tvIsEmpty.visibility = View.VISIBLE
            }
        }
    }
}