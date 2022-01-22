package com.example.inventory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.model.Item
import com.example.inventory.model.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewItemEntry(name : String, price : String, quantity : String) : Item =
        Item(itemName = name, itemPrice = price.toDouble(), quantityInStock = quantity.toInt())

    fun addNewItemEntry(name : String, price : String, quantity : String) : Unit =
        insertItem(getNewItemEntry(name, price, quantity))

    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String): Boolean =
        !(itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank())

}