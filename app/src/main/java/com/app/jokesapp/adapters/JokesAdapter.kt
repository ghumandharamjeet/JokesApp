package com.app.jokesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.jokesapp.R
import com.app.joketoday.models.Joke
import kotlinx.android.synthetic.main.item_joke_layout.view.*

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>(){

    inner class JokesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallBack = object: DiffUtil.ItemCallback<Joke>(){

        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer<Joke>(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {

        return JokesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_joke_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {

        val jokeData = differ.currentList[position]
        holder.itemView.apply {
            tv_joke.text = jokeData.joke
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}