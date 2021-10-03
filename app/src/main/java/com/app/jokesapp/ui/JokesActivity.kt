package com.app.jokesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.jokesapp.R
import kotlinx.android.synthetic.main.activity_jokes.*

class JokesActivity : AppCompatActivity() {
    lateinit var jokesViewModel: JokesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}