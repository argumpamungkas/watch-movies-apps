package com.argumpamungkas.moviesapps.ui.detail.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.argumpamungkas.moviesapps.R
import com.argumpamungkas.moviesapps.databinding.FragmentOverviewBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.persistence.SharedPreferences
import com.argumpamungkas.moviesapps.ui.detail.DetailMovieActivity
import com.argumpamungkas.moviesapps.util.dateFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleOverviewFragment = module {
    factory { OverviewFragment() }
}

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding
    private val viewModel: OverviewViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieDetail.observe(viewLifecycleOwner){
            binding.tvOverview.text = it.overview

            val genres = it.genres.map { genre -> genre.name }
            binding.tvGenre.text = genres.joinToString(", ")

            val dateRelease = dateFormat(it.release_date)
            binding.tvReleaseDate.text = dateRelease

            val production = it.production_companies.map {production -> production.name  }
            binding.tvProduction.text = production.joinToString(", ")
        }
    }

}