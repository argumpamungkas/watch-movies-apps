package com.argumpamungkas.moviesapps.ui.moviescategory

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.argumpamungkas.moviesapps.R
import com.argumpamungkas.moviesapps.adapter.AdapterListMoviesCategory
import com.argumpamungkas.moviesapps.databinding.ActivityMoviesCategoryBinding
import com.argumpamungkas.moviesapps.databinding.ToolbarDetailBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleMoviesCategory = module {
    factory { MoviesCategoryActivity() }
}

class MoviesCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesCategoryBinding
    private lateinit var bindingToolbar: ToolbarDetailBinding
    private lateinit var adapter: AdapterListMoviesCategory
    private val viewModel: MoviesCategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesCategoryBinding.inflate(layoutInflater)
        bindingToolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar(bindingToolbar.toolbarDetail)
        supportActionBar?.apply {
            title = bindingToolbar.tvTitleToolbar.text
            setDisplayHomeAsUpEnabled(true)
        }
        val movieCategory = intent.getStringExtra(Constant.MOVIES_CATEGORY)
        bindingToolbar.tvTitleToolbar.text = movieCategory

        setupRecyclerView()

        when (movieCategory) {
            getString(R.string.movie_upcoming) -> {
                viewModel.fetchMovieUpcoming()
            }
            getString(R.string.movie_popular) -> {
                viewModel.fetchMoviePopular()
            }
            getString(R.string.movie_top_rated) -> {
                viewModel.fetchMovieTopRated()
            }
            getString(R.string.movie_now_playing) -> {
                viewModel.fetchMovieNowPlaying()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loading.observe(this){
            showLoading(it)
        }

        viewModel.listMovieUpcoming.observe(this) {
            if (it.results.isNotEmpty()) adapter.setItemMovie(it.results)
        }

        viewModel.listMoviePopular.observe(this) {
            if (it.results.isNotEmpty()) adapter.setItemMovie(it.results)
        }

        viewModel.listMovieTopRated.observe(this) {
            if (it.results.isNotEmpty()) adapter.setItemMovie(it.results)
        }

        viewModel.listMovieNowPlaying.observe(this) {
            if (it.results.isNotEmpty()) adapter.setItemMovie(it.results)
        }
    }

    private fun setupRecyclerView() {
        adapter = AdapterListMoviesCategory(
            arrayListOf(),
            object : AdapterListMoviesCategory.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
                }
            })
        binding.rvListMovieCategoryDetail.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(loading: Boolean) {
        if (loading){
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }
}