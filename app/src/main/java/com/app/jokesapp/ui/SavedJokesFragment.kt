package com.app.jokesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.jokesapp.R
import com.app.jokesapp.adapters.JokesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved_jokes.*

class SavedJokesFragment : Fragment(R.layout.fragment_saved_jokes) {

    lateinit var jokesViewModel: JokesViewModel
    lateinit var jokesAdapter: JokesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        jokesViewModel = (activity as JokesActivity).jokesViewModel
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        jokesViewModel.getSavedNews().observe(viewLifecycleOwner, Observer{
            jokesAdapter.differ.submitList(it)
        })

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                var article = jokesAdapter.differ.currentList.get(position)
                jokesViewModel.deleteArticle(article)
                Snackbar.make(view, "Joke Deleted!", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        jokesViewModel.saveArticle(article)
                    }
                }.show()
            }
        }
    }

    private fun setupRecyclerView() {
        jokesAdapter = JokesAdapter()
        rvSavedJokes.apply {
            adapter = jokesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}