package com.example.mvvmshoppinglist.ui.Views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshoppinglist.ShoppingListApplication
import com.example.mvvmshoppinglist.data.entities.ShoppingItem
import com.example.mvvmshoppinglist.databinding.ActivityShoppingListBinding
import com.example.mvvmshoppinglist.ui.adapters.ShoppingListAdapter
import com.example.mvvmshoppinglist.ui.viewModels.ShoppingListViewModel
import com.example.mvvmshoppinglist.ui.viewModels.ShoppingListViewModelFactory


class ShoppingListActivity : AppCompatActivity() {

    lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as ShoppingListApplication).shoppingRepository
        val factory = ShoppingListViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ShoppingListViewModel::class.java]

        val rv = binding.rvShoppingList
        val adapter = ShoppingListAdapter(listOf(), viewModel)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            rv.context,
            (rv.layoutManager as LinearLayoutManager).orientation
        )
        rv.addItemDecoration(dividerItemDecoration)

        viewModel.getAllItems().observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

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