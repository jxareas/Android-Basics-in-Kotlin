package com.example.inventory.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.databinding.ItemListItemBinding
import com.example.inventory.model.Item
import com.example.inventory.utils.formattedPrice
import com.example.inventory.utils.viewBind

class ItemListAdapter(private val onItemClicked : ItemClick) :
    ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback) {

    private companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.itemId == newItem.itemId

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.itemName == newItem.itemName

    }

    inner class ItemViewHolder(private val binding : ItemListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(item : Item) : Unit = binding.run {
                    itemName.text = item.itemName
                    itemPrice.text = item.formattedPrice
                    itemQuantity.text = item.quantityInStock.toString()
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(parent.viewBind(ItemListItemBinding::inflate))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem : Item = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onItemClicked.onClick(currentItem)
        }
    }

    @FunctionalInterface
    fun interface ItemClick { fun onClick(item : Item) }

}