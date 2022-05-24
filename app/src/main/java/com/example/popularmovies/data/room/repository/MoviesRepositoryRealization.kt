package com.example.popularmovies.data.room.repository

import androidx.lifecycle.LiveData
import com.example.popularmovies.data.room.dao.MoviesDao
import com.example.popularmovies.models.MainResult

//Передаем реализацию интерфейс MoviesRepository и передаем в конструктор MoviesDao и реализуем
// наши методы из интерфейса MoviesRepository


class MoviesRepositoryRealization(private val moviesDao: MoviesDao):MoviesRepository {
    override val allMovies: LiveData<List<MainResult>>
        get() = moviesDao.getAllMovies()

    override suspend fun insertMovie(mainResult: MainResult, onSuccess: () -> Unit) {
        moviesDao.insert(mainResult)
        onSuccess()
    }

    override suspend fun deleteMovie(mainResult: MainResult, onSuccess: () -> Unit) {
        moviesDao.delete(mainResult)
        onSuccess()
    }
}