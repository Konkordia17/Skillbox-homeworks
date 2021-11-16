package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.data.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailInfoFragment : Fragment(R.layout.fragment_detail_info) {
    lateinit var imageDetailInfo: ImageView
    lateinit var nameDetailInfo: TextView
    lateinit var fullNameDetailInfo: TextView
    lateinit var addToFavouritesButton: ImageButton
    private val args: DetailInfoFragmentArgs by navArgs()
    var addedToRepository = MutableLiveData<Boolean>()
    private lateinit var repository: PublicRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = args.repository
        Networking.githubApi.checkIsFavourite(repository.owner.login, repository.name)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    addedToRepository.value = response.code() == 204
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    addedToRepository.value = false
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameDetailInfo = requireView().findViewById(R.id.nameDetailInfo)
        fullNameDetailInfo = requireView().findViewById(R.id.fullNameDetailInfo)
        imageDetailInfo = requireView().findViewById(R.id.imageDetailInfo)
        addToFavouritesButton = requireView().findViewById(R.id.addToFavouritesButton)
        nameDetailInfo.text = repository.name
        fullNameDetailInfo.text = repository.full_name
        Glide.with(requireContext())
            .load(repository.owner.avatar_url)
            .into(imageDetailInfo)
        addedToRepository.observe(viewLifecycleOwner, Observer {
            if (it) {
                Glide.with(requireContext())
                    .load(R.drawable.ic_star_true)
                    .into(addToFavouritesButton)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.ic_star_)
                    .into(addToFavouritesButton)
            }
        })

        addToFavouritesButton.setOnClickListener {
            when (addedToRepository.value) {
                true -> removeFromFavourites()
                false -> putToFavourites()
            }
        }
    }

    private fun removeFromFavourites() {
        Networking.githubApi
            .removeFromFavourites(repository.owner.login, repository.name)
            .enqueue(
                object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        addedToRepository.value = response.code() != 204
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
    }

    private fun putToFavourites() {
        Networking.githubApi
            .putToFavourites(repository.owner.login, repository.name)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    addedToRepository.value = response.code() == 204
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}

