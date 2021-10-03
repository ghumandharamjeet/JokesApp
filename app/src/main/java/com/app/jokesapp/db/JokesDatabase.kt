package com.app.joketoday.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.joketoday.models.Joke

@Database(
    entities = [Joke::class],
    version = 1
)
abstract class JokesDatabase: RoomDatabase() {

    abstract fun getJokesDao(): JokesDao

    companion object{

        @Volatile
        private var instance : JokesDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?: createDatabase(context).also{instance = it}
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            JokesDatabase::class.java,
            "jokes_db.db").build()
    }
}