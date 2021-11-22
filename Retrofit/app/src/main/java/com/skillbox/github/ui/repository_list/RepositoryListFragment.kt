package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentRepositoryListBinding

class RepositoryListFragment : Fragment(R.layout.fragment_repository_list) {
    private lateinit var binding: FragmentRepositoryListBinding
    private val repositoriesListViewModel: RepositoryListViewModel by viewModels()
    private var repositoryAdapter: RepositoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeViewModelState()
        repositoriesListViewModel.getListWithCoroutine()
    }

    private fun initList() {
        repositoryAdapter = RepositoryAdapter { repository ->
            val action =
                RepositoryListFragmentDirections.actionRepositoryListFragmentToDetailInfoFragment(
                    repository
                )
            findNavController().navigate(action)
        }
        with(binding.repositoryList) {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeViewModelState() {
        repositoriesListViewModel.repositories.observe(viewLifecycleOwner) { newList ->
            repositoryAdapter?.updateRepositories(newList)
        }
    }
}