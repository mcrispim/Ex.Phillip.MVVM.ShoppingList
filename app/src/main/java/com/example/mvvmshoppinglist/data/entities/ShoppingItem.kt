package com.example.mvvmshoppinglist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var amount: Int
)
