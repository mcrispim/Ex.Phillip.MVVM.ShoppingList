package com.example.mvvmshoppinglist.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmshoppinglist.data.entities.ShoppingItem
import com.example.mvvmshoppinglist.data.repositories.ShoppingItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel( val repository: ShoppingItemRepository) : ViewModel() {

    fun upsertItem(item: ShoppingItem) = viewModelScope.launch(Dispatchers.IO) {
            repository.upsertItem(item)
        }

    fun deleteItem(item: ShoppingItem) = viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }

    fun getAllItems() = repository.getAllShoppingItems()
}