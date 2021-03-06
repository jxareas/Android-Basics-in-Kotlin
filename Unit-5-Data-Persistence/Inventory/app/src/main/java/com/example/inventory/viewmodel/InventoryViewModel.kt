package com.example.inventory.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.inventory.model.Item
import com.example.inventory.model.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {


    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    private fun insertItem(item: Item) {
        viewModelScope.launch { itemDao.insert(item) }
    }

    private fun updateItem(item : Item) {
            viewModelScope.launch { itemDao.update(item) }
    }

    fun sellItem(item: Item) {
        if (item.quantityInStock > 0) {
            val newItem = item.copy(quantityInStock =  item.quantityInStock - 1)
            updateItem(newItem)
        }
    }


    fun deleteItem(item: Item) {
        viewModelScope.launch { itemDao.delete(item) }
    }

    fun isStockAvailable(item: Item): Boolean =
        item.quantityInStock > 0

    fun retrieveItem(itemId : Int) : LiveData<Item> =
        itemDao.getItem(itemId).asLiveData()

    private fun getNewItemEntry(name : String, price : String, quantity : String) : Item =
        Item(itemName = name, itemPrice = price.toDouble(), quantityInStock = quantity.toInt())

    fun addNewItemEntry(name : String, price : String, quantity : String) : Unit =
        insertItem(getNewItemEntry(name, price, quantity))

    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String): Boolean =
        !(itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank())

}