package com.example.popularmovies.data.room.repository

import androidx.lifecycle.LiveData
import com.example.popularmovies.models.MainResult

interface MoviesRepository {
    //количество и название методов

    val allMovies:LiveData<List<MainResult>>
    suspend fun insertMovie(mainResult: MainResult,onSuccess:()->Unit)
    suspend fun deleteMovie(mainResult: MainResult,onSuccess:()->Unit)
}