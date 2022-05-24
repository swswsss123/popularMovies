package com.example.popularmovies.models

data class Movies(
    val page: Int,
    val results: List<MainResult>,
    val total_pages: Int,
    val total_results: Int
)