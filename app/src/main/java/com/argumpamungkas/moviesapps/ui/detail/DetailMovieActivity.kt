package com.argumpamungkas.moviesapps.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        bindingToolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar(bindingToolbar.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sectionPager()
    }

    override fun onStart() {
        super.onStart()
        viewModel.movieDetail.observe(this){
            imageFormat(it.backdrop_path, binding.ivBackdrop)
            imageFormat(it.poster_path, binding.ivPoster)

            binding.tvTitle.text = it.title

            binding.tvRuntime.text = runtimeFormat(it.runtime)
            binding.tvReleaseDate.text = justYear(it.release_date)
            binding.tvVote.text = voteFormat(it.vote_average)
        }
    }

    override fun onPause() {
        super.onPause()

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
}