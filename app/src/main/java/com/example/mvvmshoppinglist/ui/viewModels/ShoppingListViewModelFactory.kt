package com.example.mvvmshoppinglist.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmshoppinglist.data.repositories.ShoppingItemRepository

class ShoppingListViewModelFactory(
    private val repository: ShoppingItemRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingListViewModel(repository) as T
    }
}