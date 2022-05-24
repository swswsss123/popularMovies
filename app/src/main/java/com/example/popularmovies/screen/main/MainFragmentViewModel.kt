package com.example.popularmovies.screen.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.REALISATION
import com.example.popularmovies.data.retrofit.api.RetrofitRepository
import com.example.popularmovies.data.room.MoviesRoomDatabase
import com.example.popularmovies.data.room.repository.MoviesRepositoryRealization
import com.example.popularmovies.models.Movies
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application: Application):AndroidViewModel(application) {
    private val repository = RetrofitRepository()
    lateinit var realization : MoviesRepositoryRealization
    val myMovies : MutableLiveData<Response<Movies>> = MutableLiveData() //переменая с инецелизацией
    val context = application

    fun getMoviesRetrofit(){
        viewModelScope.launch {
            myMovies.value =repository.getMovies()  //укладываем все наши фильмы в лайв дату
        }
    }

    fun initDatabase(){
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REALISATION = MoviesRepositoryRealization(daoMovie)
    }
}