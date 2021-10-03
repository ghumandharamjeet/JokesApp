package com.app.joketoday.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "jokes"
)
data class Joke(@PrimaryKey val id: String,
                val status: String,
                val joke: String)
