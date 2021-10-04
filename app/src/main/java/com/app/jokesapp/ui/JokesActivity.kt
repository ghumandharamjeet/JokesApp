package com.app.jokesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.jokesapp.R
import com.app.joketoday.db.JokesDatabase
import com.app.joketoday.db.JokesRepository
import kotlinx.android.synthetic.main.activity_jokes.*

class JokesActivity : AppCompatActivity() {
    lateinit var jokesViewModel: JokesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

        val newsViewModelProviderFactory = JokesViewModelProviderFactory(application, JokesRepository(JokesDatabase(this)))
        jokesViewModel = ViewModelProvider(this, newsViewModelProviderFactory).get(JokesViewModel::class.java)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}