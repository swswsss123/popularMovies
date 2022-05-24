package com.example.popularmovies.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_table")
data class MainResult(
    // Добавляем анатацию PrimaryKey для автоматической генерации ID
    @PrimaryKey(autoGenerate = true)
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    @ColumnInfo
    val overview: String,

    val popularity: Double,

    @ColumnInfo
    val poster_path: String,
    @ColumnInfo
    val release_date: String,
    @ColumnInfo
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Serializable