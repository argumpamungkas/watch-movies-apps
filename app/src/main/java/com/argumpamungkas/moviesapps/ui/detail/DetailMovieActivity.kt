package com.argumpamungkas.moviesapps.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.argumpamungkas.moviesapps.R
import com.argumpamungkas.moviesapps.adapter.SectionPagerAdapter
import com.argumpamungkas.moviesapps.databinding.ActivityDetailMovieBinding
import com.argumpamungkas.moviesapps.databinding.ToolbarDetailBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.util.imageFormat
import com.argumpamungkas.moviesapps.util.justYear
import com.argumpamungkas.moviesapps.util.runtimeFormat
import com.argumpamungkas.moviesapps.util.voteFormat
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleDetailMovieActivity = module {
    factory { DetailMovieActivity() }
}

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var bindingToolbar: ToolbarDetailBinding
    private val viewModel: DetailMovieViewModel by viewModel()
    private val movieTitle by lazy { intent.getStringExtra(Constant.MOVIE_TITLE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        bindingToolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar(bindingToolbar.toolbarDetail)
        supportActionBar?.apply {
            title = bindingToolbar.tvTitleToolbar.text
            setDisplayHomeAsUpEnabled(true)
        }
        bindingToolbar.tvTitleToolbar.text = movieTitle

        sectionPager()
    }

    override fun onStart() {
        super.onStart()
        viewModel.movieDetail.observe(this){ movie ->
            viewModel.find(movie)
            imageFormat(movie.backdrop_path, binding.ivBackdrop)
            imageFormat(movie.poster_path, binding.ivPoster)

            binding.tvTitle.text = movie.title

            binding.tvRuntime.text = runtimeFormat(movie.runtime)
            binding.tvReleaseDate.text = justYear(movie.release_date)
            binding.tvVote.text = voteFormat(movie.vote_average)

            binding.btnFavorite.setOnClickListener {
                viewModel.favorite(movie)

                if(viewModel.isFavorite.value == 0){
                    showMessage("Add Favorite")
                } else {
                    showMessage("Un favorite")
                }
            }
        }

        viewModel.isFavorite.observe(this){
            if (it == 0){
                binding.btnFavorite.setImageResource(R.drawable.ic_unfavorite)
            } else {
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
            }
            return@observe
        }
    }

    private fun sectionPager(){
        val adapterSection = SectionPagerAdapter(this)
        binding.apply {
            viewPager.adapter = adapterSection
            TabLayoutMediator(tabLayout, viewPager) {tab, position ->
                tab.text = resources.getString(Constant.TAB_TITLES[position])
            }.attach()
            supportActionBar?.elevation = 0f
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun showMessage(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}