package com.example.popularmovies.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.popularmovies.models.MainResult

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mainResult: MainResult)

    @Delete
    suspend fun delete(mainResult: MainResult)

    @Query("SELECT*from movie_table")
    fun getAllMovies():LiveData<List<MainResult>>
}