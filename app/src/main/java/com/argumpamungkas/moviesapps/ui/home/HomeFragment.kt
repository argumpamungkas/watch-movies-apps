package com.argumpamungkas.moviesapps.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.argumpamungkas.moviesapps.R
import com.argumpamungkas.moviesapps.adapter.AdapterListMovie
import com.argumpamungkas.moviesapps.databinding.CustomToolbarBinding
import com.argumpamungkas.moviesapps.databinding.FragmentHomeBinding
import com.argumpamungkas.moviesapps.model.ItemMovieModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleHomeFragment = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar = binding.customToolbar
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingToolbar.tvTitleToolbar.text = viewModel.titleToolbar

        //        Upcoming
        val adapterUpcoming =
            AdapterListMovie(arrayListOf(), object : AdapterListMovie.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    showMessage(item.title)
                }
            })

        binding.rvListMovieUpcoming.adapter = adapterUpcoming
        viewModel.listMovieUpcoming.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterUpcoming.setItem(it.results)
        }

//        POPULAR
        val adapterPopular =
            AdapterListMovie(arrayListOf(), object : AdapterListMovie.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    showMessage(item.title)
                }
            })

        binding.rvListMoviePopular.adapter = adapterPopular
        viewModel.listMoviePopular.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterPopular.setItem(it.results)
        }

//        TOP RATED
        val adapterTopRated =
            AdapterListMovie(arrayListOf(), object : AdapterListMovie.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    showMessage(item.title)
                }
            })

        binding.rvListMovieTopRated.adapter = adapterTopRated
        viewModel.listMovieTopRated.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterTopRated.setItem(it.results)
        }

//        NOW PLAYING
        val adapterNowPlaying =
            AdapterListMovie(arrayListOf(), object : AdapterListMovie.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    showMessage(item.title)
                }
            })

        binding.rvListMovieNowPlaying.adapter = adapterNowPlaying
        viewModel.listMovieNowPlaying.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterNowPlaying.setItem(it.results)
        }

        binding.seeAllUpcoming.setOnClickListener(this)

        viewModel.loading.observe(viewLifecycleOwner) {
            showShimmer(it)
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(view?.context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showShimmer(loading: Boolean) {
        if (loading) {
            binding.loadingShimmerUpComing.visibility = View.VISIBLE
            binding.loadingShimmer.visibility = View.VISIBLE
            binding.loadingShimmerTopRated.visibility = View.VISIBLE
            binding.loadingShimmerNowPlaying.visibility = View.VISIBLE

        } else {
            binding.loadingShimmerUpComing.stopShimmer()
            binding.loadingShimmer.stopShimmer()
            binding.loadingShimmerTopRated.stopShimmer()
            binding.loadingShimmerNowPlaying.stopShimmer()

            binding.loadingShimmerUpComing.visibility = View.GONE
            binding.loadingShimmer.visibility = View.GONE
            binding.loadingShimmerTopRated.visibility = View.GONE
            binding.loadingShimmerNowPlaying.visibility = View.GONE

        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.see_all_upcoming -> {}
            R.id.see_all_popular -> {}
            R.id.see_all_top_rated -> {}
            R.id.see_all_now_playing -> {}
        }
    }
}