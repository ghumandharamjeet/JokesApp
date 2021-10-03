package com.app.joketoday.api

import com.app.joketoday.models.Joke
import retrofit2.Response
import retrofit2.http.GET

interface
JokesAPI {

    @GET
    suspend fun getAllJokes(): Response<Joke>
}