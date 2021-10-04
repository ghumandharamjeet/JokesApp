package com.app.jokesapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.jokesapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_joke.*


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
            Snackbar.make(view, "Joke Saved!", Snackbar.LENGTH_SHORT).show()
        }

        btNewJoke.setOnClickListener {
            jokesViewModel.getJoke()
        }

        btShareJoke.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, tv_joke.text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}