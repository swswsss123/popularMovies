package com.example.popularmovies.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularmovies.data.room.dao.MoviesDao
import com.example.popularmovies.models.MainResult


@Database(entities = [MainResult::class], version = 2)
abstract class MoviesRoomDatabase:RoomDatabase() {
    // cоздание базы данных Room
    abstract fun getMovieDao():MoviesDao

    companion object{
        private var database:MoviesRoomDatabase ?= null
        //создание базы данных проверяем есть ли она существует с помощью операторов if и else
        fun getInstance(context: Context):MoviesRoomDatabase{
            return if(database == null){
                database = Room
                    .databaseBuilder(context,MoviesRoomDatabase::class.java,"db")
                    .build()
                return database as MoviesRoomDatabase
            }else{
                return database as MoviesRoomDatabase
            }
        }
    }
}