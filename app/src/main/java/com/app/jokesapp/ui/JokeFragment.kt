package com.app.jokesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.jokesapp.R
import kotlinx.android.synthetic.main.fragment_joke.*
import kotlinx.android.synthetic.main.item_joke_layout.*

class JokesFragment : Fragment(R.layout.fragment_joke) {

    lateinit var jokesViewModel: JokesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        jokesViewModel = (activity as JokesActivity).jokesViewModel
        super.onViewCreated(view, savedInstanceState)

        jokesViewModel.jokesLiveData.observe(viewLifecycleOwner, { jokeResource ->

            jokeResource.data?.let {
                tv_joke.text = it.joke
            }
        })

        btAddToFav.setOnClickListener {
            jokesViewModel.saveJoke()
        }

        btNewJoke.setOnClickListener {

        }

        btShareJoke.setOnClickListener {

        }

    }
}