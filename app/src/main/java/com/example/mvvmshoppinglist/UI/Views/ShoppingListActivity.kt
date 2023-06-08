package com.example.mvvmshoppinglist.UI.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmshoppinglist.UI.viewModels.ShoppingListViewModel
import com.example.mvvmshoppinglist.UI.viewModels.ShoppingListViewModelFactory
import com.example.mvvmshoppinglist.data.db.ShoppingDb
import com.example.mvvmshoppinglist.data.repositories.ShoppingItemRepository
import com.example.mvvmshoppinglist.databinding.ActivityShoppingListBinding

class ShoppingListActivity : AppCompatActivity() {

    lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = ShoppingDb(this)
        val repository = ShoppingItemRepository(db)
        val factory = ShoppingListViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ShoppingListViewModel::class.java]


    }
}