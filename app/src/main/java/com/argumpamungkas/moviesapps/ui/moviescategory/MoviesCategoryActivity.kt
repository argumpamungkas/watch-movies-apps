package com.argumpamungkas.moviesapps.ui.moviescategory

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.argumpamungkas.moviesapps.R
import com.argumpamungkas.moviesapps.adapter.AdapterListMoviesCategory
import com.argumpamungkas.moviesapps.databinding.ActivityMoviesCategoryBinding
import com.argumpamungkas.moviesapps.databinding.ToolbarDetailBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieModel
import com.argumpamungkas.moviesapps.persistence.SharedPreferences
import com.argumpamungkas.moviesapps.ui.detail.DetailMovieActivity
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
    private val movieCategory by lazy {
        intent.getStringExtra(Constant.MOVIES_CATEGORY)
    }
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesCategoryBinding.inflate(layoutInflater)
        bindingToolbar = binding.toolbar
        setContentView(binding.root)
        sharedPref = SharedPreferences(this)

        setSupportActionBar(bindingToolbar.toolbarDetail)
        supportActionBar?.apply {
            title = bindingToolbar.tvTitleToolbar.text
            setDisplayHomeAsUpEnabled(true)
        }
        bindingToolbar.tvTitleToolbar.text = movieCategory

        setupRecyclerView()
        binding.nestedScrollView.scrollTo(0,0)
        fetchMovie()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loading.observe(this) {
            showLoading(it)
        }

        viewModel.loadingMore.observe(this){
            showLoadingMore(it)
        }

        observeMovie()

        binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                if (viewModel.page <= viewModel.totalPages && viewModel.loadingMore.value == false) {
                    fetchMovie()
                }
            }
        }

    }

    private fun setupRecyclerView() {
        adapter = AdapterListMoviesCategory(
            arrayListOf(),
            object : AdapterListMoviesCategory.OnAdapterListener {
                override fun onClick(item: ItemMovieModel) {
                    moveDetail(item.id, item.title)
                }
            })
        binding.rvListMovieCategoryDetail.adapter = adapter
    }

    private fun fetchMovie() {
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

    private fun observeMovie() {
        when (movieCategory) {
            getString(R.string.movie_upcoming) -> {
                viewModel.listMovieUpcoming.observe(this) {
                    if (it.results.isNotEmpty()) {
                        if (viewModel.isFirstLoad.value == true){
                            adapter.setItemMovie(it.results)
                        } else {
                            adapter.setAddMovie(it.results)
                        }
                    }
                }
            }
            getString(R.string.movie_popular) -> {
                viewModel.listMoviePopular.observe(this) {
                    if (it.results.isNotEmpty()) {
                        if (viewModel.isFirstLoad.value == true){
                            adapter.setItemMovie(it.results)
                        } else {
                            adapter.setAddMovie(it.results)
                        }
                    }
                }
            }
            getString(R.string.movie_top_rated) -> {
                viewModel.listMovieTopRated.observe(this) {
                    if (it.results.isNotEmpty()) {
                        if (viewModel.isFirstLoad.value == true){
                            adapter.setItemMovie(it.results)
                        } else {
                            adapter.setAddMovie(it.results)
                        }
                    }
                }
            }
            getString(R.string.movie_now_playing) -> {
                viewModel.listMovieNowPlaying.observe(this) {
                    if (it.results.isNotEmpty()) {
                        if (viewModel.isFirstLoad.value == true){
                            adapter.setItemMovie(it.results)
                        } else {
                            adapter.setAddMovie(it.results)
                        }
                    }
                }
            }
        }
    }

    private fun moveDetail(movieId: Int, movieTitle: String) {
        sharedPref.putMovieId(Constant.MOVIE_ID, movieId)
        startActivity(
            Intent(this, DetailMovieActivity::class.java)
                .putExtra(Constant.MOVIE_TITLE, movieTitle)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }

    private fun showLoadingMore(loading: Boolean) {
        if (loading) {
            binding.loadingMore.visibility = View.VISIBLE
        } else {
            binding.loadingMore.visibility = View.GONE
        }
    }
}