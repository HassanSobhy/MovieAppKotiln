package com.example.moviesapp.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.network.Results

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [Results].
 */
class DetailViewModel(result: Results, app: Application) : ViewModel() {


    private val _selectedMovie = MutableLiveData<Results>()

    val selectedMovie: LiveData<Results>
        get() = _selectedMovie

    init {
        _selectedMovie.value = result
    }

}