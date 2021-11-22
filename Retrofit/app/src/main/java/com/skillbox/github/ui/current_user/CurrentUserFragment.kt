package com.skillbox.github.ui.current_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentCurrentUserBinding


class CurrentUserFragment : Fragment(R.layout.fragment_current_user) {
    lateinit var binding: FragmentCurrentUserBinding
    private val currentUserViewModel: CurrentUserViewModel by viewModels()
    private var followersAdapter: FollowsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currentUserViewModel.getUserAndFollowers()
        observeViewModel()
        initFollowerList()
    }

    private fun observeViewModel() {
        currentUserViewModel.userLiveData.observe(viewLifecycleOwner) {
            binding.nameTextView.text = it.name
            binding.loginTextView.text = it.login
            Glide.with(requireContext())
                .load(it.avatar_url)
                .into(binding.imageViewUser)
        }
        currentUserViewModel.errorText.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        currentUserViewModel.followersListLiveData.observe(viewLifecycleOwner) { newList ->
            followersAdapter?.updateFollowersList(newList)
        }
    }

    private fun initFollowerList() {
        followersAdapter = FollowsAdapter()
        with(binding.userFollowsRec) {
            adapter = followersAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
}
