package com.example.popularmovies.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.popularmovies.REALISATION
import com.example.popularmovies.data.room.repository.MoviesRepositoryRealization
import com.example.popularmovies.models.MainResult

class FavoriteFragmentViewModel:ViewModel() {

    fun getAllMovies():LiveData<List<MainResult>>{
        return REALISATION.allMovies
    }
}