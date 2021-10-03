package com.app.joketoday.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.joketoday.models.Joke

@Dao
interface JokesDao {

    @Insert
    suspend fun insert(joke: Joke): Long

    @Query("SELECT * FROM jokes")
    fun getAllJokes(): LiveData<List<Joke>>

    @Delete
    suspend fun deleteJoke(joke: Joke)
}