package com.example.moviesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.network.MovieApi
import com.example.moviesapp.network.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<List<Results>>()

    val response: LiveData<List<Results>>
        get() = _response

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getPopularMoviesResponse()
    }

    fun getPopularMoviesResponse() {
        coroutineScope.launch {
            var getPopularMoviesResponse = MovieApi.retrofitService.getPopularMovies()
            try {
                _response.value = getPopularMoviesResponse.await().results
            } catch (e: Exception) {
                _response.value = ArrayList()

            }
        }
    }


    fun getTopRatedMoviesResponse() {
        coroutineScope.launch {
            var getTopRatedMoviesResponse = MovieApi.retrofitService.getTopRatedMovies()
            try {
                _response.value = getTopRatedMoviesResponse.await().results
            } catch (e: Exception) {
                _response.value = ArrayList()

            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}