package com.argumpamungkas.moviesapps.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.argumpamungkas.moviesapps.databinding.CustomToolbarBinding
import com.argumpamungkas.moviesapps.databinding.FragmentBookmarkBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val moduleBookmarkFragment = module {
    factory { BookmarkFragment() }
}

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookmarkViewModel by viewModel()
    private lateinit var bindingToolbar: CustomToolbarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        bindingToolbar = _binding!!.customToolbar
        return _binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingToolbar.tvTitleToolbar.text = viewModel.titleToolbar
        viewModel.text.observe(viewLifecycleOwner){
            binding.textBookmark.text = it
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}