package com.example.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchMovieViewModel : ViewModel() {
    private val repository = SearchMovieRepository()
    private var currentCall: okhttp3.Call? = null
    private val movieListLiveData = MutableLiveData<List<Movie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Throwable>()

    val movieList: LiveData<List<Movie>>
        get() = movieListLiveData
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData
    val error: LiveData<Throwable>
        get() = errorLiveData

    fun search(text: String, year: String, type: String) {
        isLoadingLiveData.postValue(true)
        currentCall = repository.searchMovie(text, year, type, { movies ->
            isLoadingLiveData.postValue(false)
            movieListLiveData.postValue(movies)
            currentCall = null
        }, {
            errorLiveData.postValue(it)
        })
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }
}