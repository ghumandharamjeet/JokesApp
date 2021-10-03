package com.app.jokesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.jokesapp.R
import com.google.android.material.snackbar.Snackbar

class JokesFragment : Fragment(R.layout.fragment_jokes) {

    lateinit var jokesViewModel: JokesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        jokesViewModel = (activity as JokesActivity).jokesViewModel
        super.onViewCreated(view, savedInstanceState)
    }
}