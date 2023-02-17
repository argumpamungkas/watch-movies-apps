package com.argumpamungkas.moviesapps.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.argumpamungkas.moviesapps.R
import com.argumpamungkas.moviesapps.adapter.AdapterListMovie
import com.argumpamungkas.moviesapps.databinding.CustomToolbarBinding
import com.argumpamungkas.moviesapps.databinding.FragmentHomeBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieModel
import com.argumpamungkas.moviesapps.persistence.SharedPreferences
import com.argumpamungkas.moviesapps.ui.detail.DetailMovieActivity
import com.argumpamungkas.moviesapps.ui.moviescategory.MoviesCategoryActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleHomeFragment = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences

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
        sharedPref = SharedPreferences(requireContext())
        Log.i("idMovie", "${sharedPref.getMovieId(Constant.MOVIE_ID)} sebelum atau sesudah")

        getMovie()

        viewModel.loading.observe(viewLifecycleOwner) {
            showShimmer(it)
        }

        binding.seeAllUpcoming.setOnClickListener(this)
        binding.seeAllPopular.setOnClickListener(this)
        binding.seeAllTopRated.setOnClickListener(this)
        binding.seeAllNowPlaying.setOnClickListener(this)

    }

    private fun getMovie(){

        val adapterUpcoming =
            AdapterListMovie(arrayListOf(), object : AdapterListMovie.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    moveDetail(item.id, item.title)
                }
            })

        binding.rvListMovieUpcoming.adapter = adapterUpcoming
        viewModel.listMovieUpcoming.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterUpcoming.setItem(it.results)
        }

        val adapterPopular =
            AdapterListMovie(arrayListOf(), object : AdapterListMovie.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    moveDetail(item.id, item.title)
                }
            })

        binding.rvListMoviePopular.adapter = adapterPopular
        viewModel.listMoviePopular.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterPopular.setItem(it.results)
        }

        val adapterTopRated =
            AdapterListMovie(arrayListOf(), object : AdapterListMovie.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    moveDetail(item.id, item.title)
                }
            })

        binding.rvListMovieTopRated.adapter = adapterTopRated
        viewModel.listMovieTopRated.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterTopRated.setItem(it.results)
        }

        val adapterNowPlaying =
            AdapterListMovie(arrayListOf(), object : AdapterListMovie.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    moveDetail(item.id, item.title)
                }
            })

        binding.rvListMovieNowPlaying.adapter = adapterNowPlaying
        viewModel.listMovieNowPlaying.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterNowPlaying.setItem(it.results)
        }
    }

    private fun moveDetail(movieId: Int, movieTitle: String){
        sharedPref.putMovieId(Constant.MOVIE_ID, movieId)
        startActivity(Intent(requireContext(), DetailMovieActivity::class.java)
            .putExtra(Constant.MOVIE_TITLE, movieTitle)
        )
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
            R.id.see_all_upcoming -> {
                startActivity(Intent(view?.context, MoviesCategoryActivity::class.java)
                    .putExtra(Constant.MOVIES_CATEGORY, getString(R.string.movie_upcoming))
                )
            }
            R.id.see_all_popular -> {
                startActivity(Intent(view?.context, MoviesCategoryActivity::class.java)
                    .putExtra(Constant.MOVIES_CATEGORY, getString(R.string.movie_popular))
                )
            }
            R.id.see_all_top_rated -> {
                startActivity(Intent(view?.context, MoviesCategoryActivity::class.java)
                    .putExtra(Constant.MOVIES_CATEGORY, getString(R.string.movie_top_rated))
                )
            }
            R.id.see_all_now_playing -> {
                startActivity(Intent(view?.context, MoviesCategoryActivity::class.java)
                    .putExtra(Constant.MOVIES_CATEGORY, getString(R.string.movie_now_playing))
                )
            }
        }
    }
}