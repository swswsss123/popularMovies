package com.example.popularmovies.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.REALISATION
import com.example.popularmovies.data.room.repository.MoviesRepositoryRealization
import com.example.popularmovies.models.MainResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragmentViewModel:ViewModel() {
    lateinit var realization: MoviesRepositoryRealization
    // только тут мы сможем добавлть наши фильмы в бд
    fun insert(mainResult: MainResult,onSuccess:() -> Unit)=
        viewModelScope.launch (Dispatchers.IO){
            REALISATION.insertMovie(mainResult){
                onSuccess()
            }
        }
    fun delete(mainResult: MainResult,onSuccess: () -> Unit)=
            viewModelScope.launch (Dispatchers.IO){
                REALISATION.deleteMovie(mainResult){
                    onSuccess()
                }
            }
}