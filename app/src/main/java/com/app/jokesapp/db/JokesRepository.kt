package com.app.joketoday.db

import com.app.joketoday.api.RetrofitInstance
import com.app.joketoday.models.Joke

class JokesRepository(var db: JokesDatabase) {

    suspend fun getJokes() =
        RetrofitInstance.api.getAllJokes()

    suspend fun insert(joke: Joke) = db.getJokesDao().insert(joke)

    suspend fun deleteJoke(joke: Joke) = db.getJokesDao().deleteJoke(joke)

    fun getAllSavedJokes() = db.getJokesDao().getAllJokes()
}