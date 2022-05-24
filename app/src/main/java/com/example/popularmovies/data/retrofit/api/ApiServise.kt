package com.example.popularmovies.data.retrofit.api

import com.example.popularmovies.models.Movies
import retrofit2.Response
import retrofit2.http.GET

interface ApiServise {
    //тут прописываем сылку на инпоинт по сути часть сылки а именно show
    @GET("3/movie/popular?api_key=5250a97df6ac375b55bcd5780f4950b2&language=en-US&page=1")
    suspend fun getPopularMovies():Response<Movies>
}