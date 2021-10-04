package com.app.jokesapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.joketoday.db.JokesRepository

class JokesViewModelProviderFactory(val app: Application, private val repository: JokesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokesViewModel(app, repository) as T
    }
}