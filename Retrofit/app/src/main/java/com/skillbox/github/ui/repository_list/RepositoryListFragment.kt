package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.github.R
import com.skillbox.github.data.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryListFragment : Fragment(R.layout.fragment_repository_list) {
    lateinit var repositoryList: RecyclerView
    private var repositoryAdapter: RepositoryAdapter? = null
    private val repositoriesListViewModel: RepositoryListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositoryList = requireView().findViewById(R.id.repositoryList)
        initList()
        observeViewModelState()
        Networking.githubApi.getRepositoriesList()
            .enqueue(object : Callback<List<PublicRepository>> {
                override fun onResponse(
                    call: Call<List<PublicRepository>>,
                    response: Response<List<PublicRepository>>
                ) {
                    val repositories = response.body()
                    repositoryAdapter?.updateRepositories(repositories.orEmpty())
                }

                override fun onFailure(call: Call<List<PublicRepository>>, t: Throwable) {

                }
            })
    }

    private fun initList() {
        repositoryAdapter = RepositoryAdapter()
        with(repositoryList) {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeViewModelState() {
        repositoriesListViewModel.repositorieLiveData
            .observe(viewLifecycleOwner) { newList ->
                repositoryAdapter?.updateRepositories(newList)
            }

    }

}