package com.example.moshi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchMovieViewModel : ViewModel() {
    private val repository = SearchMovieRepository()
    private var currentCall: okhttp3.Call? = null
    private val movieListLiveData = MutableLiveData<Movie>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Throwable>()

    val movieList: LiveData<Movie>
        get() = movieListLiveData
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData
    val error: LiveData<Throwable>
        get() = errorLiveData

    fun search(text: String) {
        isLoadingLiveData.postValue(true)
        currentCall = repository.searchMovie(text, { movies ->
            isLoadingLiveData.postValue(false)
            movieListLiveData.postValue(movies)
            currentCall = null
        }, { errorLiveData.postValue(it) })
    }

    fun addScore() {
        val score = Score("Оценка", "${(0..10).random()}")
        val oldScore = movieListLiveData.value?.score?.toMutableMap() ?: mutableMapOf()
        oldScore.put(score.source, score.value)
        val movie = movieListLiveData.value?.copy(score = oldScore) ?: Movie()
        movieListLiveData.value = movie
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }
}