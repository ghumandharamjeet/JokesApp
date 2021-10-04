package com.app.jokesapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.joketoday.JokesApplication
import com.app.joketoday.api.Resource
import com.app.joketoday.db.JokesRepository
import com.app.joketoday.models.Joke
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class JokesViewModel(
    app: Application,
    val jokesRepository : JokesRepository
) : AndroidViewModel(app) {

    val jokesLiveData: MutableLiveData<Resource<Joke>> = MutableLiveData()

    init {
        getJoke()
    }

    fun getJoke() = viewModelScope.launch {
        SafeJokeFetchCall()
    }

    private suspend fun SafeJokeFetchCall() {
        jokesLiveData.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = jokesRepository.getJokes()
                jokesLiveData.postValue(handleJokesResponse(response))
            } else {
                jokesLiveData.postValue(Resource.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> jokesLiveData.postValue(Resource.Error("Network Failure"))
                else -> jokesLiveData.postValue(Resource.Error("Error occurred"))
            }
        }
    }

    private fun handleJokesResponse(response: Response<Joke>) : Resource<Joke> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<JokesApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}