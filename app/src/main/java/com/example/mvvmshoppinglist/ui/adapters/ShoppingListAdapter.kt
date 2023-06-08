package com.example.mvvmshoppinglist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmshoppinglist.ui.viewModels.ShoppingListViewModel
import com.example.mvvmshoppinglist.data.entities.ShoppingItem
import com.example.mvvmshoppinglist.databinding.ShoppingItemBinding

class ShoppingListAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingListViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ShoppingItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ShoppingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.binding.apply {
            tvItemName.text = currentItem.name
            tvItemAmount.text = currentItem.amount.toString()

            icDelete.setOnClickListener {
                viewModel.deleteItem(currentItem)
            }

            icPlus.setOnClickListener {
                currentItem.amount++
                viewModel.upsertItem(currentItem)
            }

            icMinus.setOnClickListener {
                if (currentItem.amount > 0) {
                    currentItem.amount--
                    viewModel.upsertItem(currentItem)
                }
            }
        }
    }

}
