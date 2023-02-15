package com.argumpamungkas.moviesapps.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.argumpamungkas.moviesapps.adapter.AdapterListLinear
import com.argumpamungkas.moviesapps.databinding.FragmentSearchBinding
import com.argumpamungkas.moviesapps.model.ItemMovieModel
import com.argumpamungkas.moviesapps.model.ItemMovieSearchModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleSearchFragment = module {
    factory { SearchFragment() }
}

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()

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

        val adapterSearch =
            AdapterListLinear(arrayListOf(), object : AdapterListLinear.OnAdapterListener {
                override fun onClick(item: ItemMovieSearchModel) {
                    showMessage(item.title)
                }
            })

        binding.rvSearchListMovie.adapter = adapterSearch
        viewModel.listUser.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) adapterSearch.setItemMovie(it.results)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    adapterSearch.clear()
                } else {
                    viewModel.fetchSearchMovie(query.toString())
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    adapterSearch.clear()
                } else {
                    viewModel.fetchSearchMovie(newText.toString())
                }
                return false
            }

        })
    }

    private fun showMessage(msg: String) {
        Toast.makeText(view?.context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }

}