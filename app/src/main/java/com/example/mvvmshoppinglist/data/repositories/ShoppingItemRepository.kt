package com.example.mvvmshoppinglist.data.repositories

import androidx.lifecycle.LiveData
import com.example.mvvmshoppinglist.data.db.ShoppingDb
import com.example.mvvmshoppinglist.data.db.ShoppingItemDao
import com.example.mvvmshoppinglist.data.entities.ShoppingItem

class ShoppingItemRepository(
    val shoppingDatabase: ShoppingDb
) {
    private val shoppingDao = shoppingDatabase.getShoppingItemDao()

    suspend fun upsertItem(item: ShoppingItem) {
        shoppingDao.upsertShoppingItem(item)
    }

    suspend fun deleteItem(item: ShoppingItem) {
        shoppingDao.deleteShoppingItem(item)
    }

    fun getAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.getAllShoppingItem()
    }
}