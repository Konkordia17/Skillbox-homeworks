package com.skillbox.multithreading.threading

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.multithreading.R
import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network

class ThreadingFragment : Fragment(R.layout.fragment_threading) {
    lateinit var movieList: RecyclerView
    private lateinit var handler: Handler
    private val mainHandler = Handler(Looper.getMainLooper())

    private val movieIds = listOf(
        "tt0111161",
        "tt0108052",
        "tt0137523",
        "tt0109830",
        "tt0245429",
        "tt6751668"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieList = requireView().findViewById(R.id.movieList)
        val backgroundThread = HandlerThread("handler").apply {
            start()
        }
        handler = Handler(backgroundThread.looper)
        initMovie()
    }

    private fun initMovie() {
        handler.post {
            Network.fetchMovies(movieIds) { movies ->
                mainHandler.post {
                    with(movieList) {
                        adapter = MovieAdapter(movies)
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                    }
                }
            }

            mainHandler.postDelayed({
                Toast.makeText(requireContext(), "Список обновлен", Toast.LENGTH_SHORT).show()
            }, 1000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.looper.quit()
    }
}

