package com.example.moshi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moshi.databinding.MovieFragmentBinding


class SearchFragment : Fragment(R.layout.movie_fragment) {
    private var _binding: MovieFragmentBinding? = null
    private val binding: MovieFragmentBinding
        get() = _binding!!
    private val viewModel: SearchMovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
        setListeners()
    }

    private fun setListeners() {
        binding.searchButton.setOnClickListener {
            val movieTitle = binding.titleEditText.text.toString()
            viewModel.search(movieTitle)
        }
        binding.repeatSearchButton.setOnClickListener {
            val movieTitle = binding.titleEditText.text.toString()
            viewModel.search(movieTitle)
            binding.textError.visibility = View.GONE
            binding.repeatSearchButton.visibility = View.GONE
        }
        binding.buttonToAddScore.setOnClickListener {
            viewModel.addScore()
        }
    }

    private fun initList() {
        movieAdapter = MovieAdapter()
        with(binding.moviesList) {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun bindViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            updateLoaadingState(it)
        }
        viewModel.movieList.observe(viewLifecycleOwner) {
            movieAdapter.submitList(listOf(it))
        }
        viewModel.error.observe(viewLifecycleOwner) {
            researchMovies(it)
        }
    }

    fun updateLoaadingState(isLoading: Boolean) {
        binding.moviesList.isVisible = isLoading.not()
        binding.progressBar.isVisible = isLoading
        binding.titleEditText.isEnabled = isLoading.not()
        binding.searchButton.isEnabled = isLoading.not()
    }

    private fun researchMovies(e: Throwable) {
        binding.progressBar.isVisible = false
        binding.textError.visibility = View.VISIBLE
        binding.textError.text = e.message
        binding.repeatSearchButton.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}