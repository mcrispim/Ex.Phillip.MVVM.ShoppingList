package com.example.mvvmshoppinglist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    var name: String,
    var amount: Int
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
}
