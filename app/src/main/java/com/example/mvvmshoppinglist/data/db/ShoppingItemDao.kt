package com.example.mvvmshoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.mvvmshoppinglist.data.entities.ShoppingItem

@Dao
interface ShoppingItemDao {

    @Upsert
    suspend fun upsertShoppingItem(item: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItem(): LiveData<List<ShoppingItem>>
}