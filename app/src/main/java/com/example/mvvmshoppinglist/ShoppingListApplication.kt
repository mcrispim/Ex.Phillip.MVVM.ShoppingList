package com.example.mvvmshoppinglist

import android.app.Application
import com.example.mvvmshoppinglist.data.db.ShoppingDb
import com.example.mvvmshoppinglist.data.repositories.ShoppingItemRepository

class ShoppingListApplication : Application() {

    lateinit var shoppingDb: ShoppingDb
    lateinit var shoppingRepository: ShoppingItemRepository

    override fun onCreate() {
        super.onCreate()
        shoppingDb = ShoppingDb(this)
        shoppingRepository = ShoppingItemRepository(shoppingDb.getShoppingItemDao())
    }
}