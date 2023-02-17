package com.argumpamungkas.moviesapps.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.argumpamungkas.moviesapps.adapter.AdapterListLinear
import com.argumpamungkas.moviesapps.databinding.FragmentSearchBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieSearchModel
import com.argumpamungkas.moviesapps.persistence.SharedPreferences
import com.argumpamungkas.moviesapps.ui.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleSearchFragment = module {
    factory { SearchFragment() }
}

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences
    private var textSearch = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPreferences(requireContext())

        val adapterSearch =
            AdapterListLinear(arrayListOf(), object : AdapterListLinear.OnAdapterListener {
                override fun onClick(item: ItemMovieSearchModel) {
                    moveDetail(item.id, item.title)
                }
            })

        binding.rvSearchListMovie.adapter = adapterSearch
        viewModel.listMovie.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) {
                if (viewModel.loadingMore.value == true) {
                    adapterSearch.setAddMovie(it.results)
                } else {
                    adapterSearch.setItemMovie(it.results)
                }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.loadingMore.observe(viewLifecycleOwner) {
            showLoadingMore(it)
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    adapterSearch.clear()
                } else {
                    viewModel.fetchSearchMovie(query.toString(), 1, true)
                    binding.nestedScrollView.scrollTo(0,0)
                    textSearch = query
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    adapterSearch.clear()
                } else {
                    viewModel.fetchSearchMovie(newText.toString(), 1, true)
                    binding.nestedScrollView.scrollTo(0,0)
                    textSearch = newText
                }
                return false
            }
        })

        binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, _, _, _ ->
            val view = v.getChildAt(v.childCount - 1) as View
            val diff = view.bottom - (v.height + v.scrollY)

            if (diff == 0) {
                if (viewModel.pageMovie <= viewModel.totalPages && viewModel.loadingMore.value == false) {
                    viewModel.fetchSearchMovie(textSearch, viewModel.pageMovie, false)
                }
            }
        }
    }

    private fun moveDetail(movieId: Int, movieTitle: String) {
        sharedPref.putMovieId(Constant.MOVIE_ID, movieId)
        startActivity(
            Intent(requireContext(), DetailMovieActivity::class.java)
                .putExtra(Constant.MOVIE_TITLE, movieTitle)
        )
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