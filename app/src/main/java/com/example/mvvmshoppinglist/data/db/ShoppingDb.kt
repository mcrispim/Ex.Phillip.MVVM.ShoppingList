package com.example.mvvmshoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmshoppinglist.data.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDb : RoomDatabase() {

    abstract fun getShoppingItemDao(): ShoppingItemDao

    companion object {
        @Volatile
        private var instance: ShoppingDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDb::class.java, "ShoppingDB.db"
            ).build()
    }
}