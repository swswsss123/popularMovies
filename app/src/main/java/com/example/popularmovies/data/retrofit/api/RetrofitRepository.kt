package com.example.popularmovies.data.retrofit.api

import com.example.popularmovies.models.Movies
import retrofit2.Response

class RetrofitRepository {
    // в этом класе реализуем ретрофит
    suspend fun getMovies():Response<Movies>{
        return RetrofitInstance.api.getPopularMovies()
    }
}