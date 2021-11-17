package com.skillbox.github.ui.detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentDetailInfoBinding
import com.skillbox.github.ui.repository_list.PublicRepository

class DetailInfoFragment : Fragment(R.layout.fragment_detail_info) {
    private lateinit var binding: FragmentDetailInfoBinding
    private val args: DetailInfoFragmentArgs by navArgs()
    private val detailInfoViewModel: DetailInfoViewModel by viewModels()
    private lateinit var repository: PublicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = args.repository
        detailInfoViewModel.checkRepository(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameDetailInfo.text = repository.name
        binding.fullNameDetailInfo.text = repository.full_name
        Glide.with(requireContext())
            .load(repository.owner.avatar_url)
            .into(binding.imageDetailInfo)
        observeLiveData()
        setListeners()
    }

    private fun setListeners() {
        binding.addToFavouritesButton.setOnClickListener {
            detailInfoViewModel.putOrRemoveRepository(repository)
        }
    }

    private fun observeLiveData() {
        detailInfoViewModel.addedToRepository.observe(viewLifecycleOwner, Observer {
            if (it) {
                Glide.with(requireContext())
                    .load(R.drawable.ic_star_true)
                    .into(binding.addToFavouritesButton)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.ic_star_)
                    .into(binding.addToFavouritesButton)
            }
        })
    }
}

