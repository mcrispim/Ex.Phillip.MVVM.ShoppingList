package com.example.mvvmshoppinglist.ui.Views

import com.example.mvvmshoppinglist.data.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}