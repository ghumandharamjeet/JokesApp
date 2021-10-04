package com.app.joketoday.api

import com.app.joketoday.models.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface
JokesAPI {

    @Headers("Accept: application/json")
    @GET("/")
    suspend fun getAJoke(): Response<Joke>
}