package com.example.mvvmshoppinglist.ui.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmshoppinglist.R
import com.example.mvvmshoppinglist.ui.adapters.ShoppingListAdapter
import com.example.mvvmshoppinglist.ui.viewModels.ShoppingListViewModel
import com.example.mvvmshoppinglist.ui.viewModels.ShoppingListViewModelFactory
import com.example.mvvmshoppinglist.data.db.ShoppingDb
import com.example.mvvmshoppinglist.data.entities.ShoppingItem
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

        val rv = binding.rvShoppingList
        val adapter = ShoppingListAdapter(listOf(), viewModel)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        viewModel.getAllItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fabAddItem.setOnClickListener {
            AddShoppingItemDialog(this,
            object: AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsertItem(item)
                }
            }).show()
        }
    }
}