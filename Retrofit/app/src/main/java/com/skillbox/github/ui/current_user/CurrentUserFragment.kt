package com.skillbox.github.ui.current_user

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.data.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentUserFragment : Fragment(R.layout.fragment_current_user) {
    lateinit var imageViewUser: ImageView
    lateinit var nameTextView: TextView
    lateinit var loginTextView: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Networking.githubApi.getUserInfo().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                imageViewUser = requireView().findViewById(R.id.imageViewUser)
                nameTextView = requireView().findViewById(R.id.nameTextView)
                loginTextView = requireView().findViewById(R.id.loginTextView)

                nameTextView.text = user?.name
                loginTextView.text = user?.login
                Glide.with(requireContext())
                    .load(user?.avatar_url)
                    .into(imageViewUser)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}