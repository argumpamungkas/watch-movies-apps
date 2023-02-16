package com.argumpamungkas.moviesapps.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.argumpamungkas.moviesapps.adapter.AdapterListFavorite
import com.argumpamungkas.moviesapps.adapter.AdapterListLinear
import com.argumpamungkas.moviesapps.databinding.CustomToolbarBinding
import com.argumpamungkas.moviesapps.databinding.FragmentFavoriteBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieDetailResponse
import com.argumpamungkas.moviesapps.model.ItemMovieSearchModel
import com.argumpamungkas.moviesapps.persistence.SharedPreferences
import com.argumpamungkas.moviesapps.ui.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleFavoriteFragment = module {
    factory { FavoriteFragment() }
}

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var bindingToolbar: CustomToolbarBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        bindingToolbar = binding.customToolbar
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPreferences(requireContext())

        bindingToolbar.tvTitleToolbar.text = viewModel.titleToolbar

        val adapterListFav = AdapterListFavorite(arrayListOf(), object : AdapterListFavorite.OnAdapterListener{
            override fun onClick(item: ItemMovieDetailResponse) {
                sharedPref.putMovieId(Constant.MOVIE_ID, item.id)
                startActivity(Intent(requireContext(), DetailMovieActivity::class.java)
                    .putExtra(Constant.MOVIE_TITLE, item.title)
                )
            }
        })

        binding.rvListFavorite.adapter = adapterListFav
        viewModel.movie.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                adapterListFav.clear()
                adapterListFav.setItemMovie(it as ArrayList<ItemMovieDetailResponse>)
            } else {
                binding.rvListFavorite.visibility = View.GONE

            }
        }


    }
}