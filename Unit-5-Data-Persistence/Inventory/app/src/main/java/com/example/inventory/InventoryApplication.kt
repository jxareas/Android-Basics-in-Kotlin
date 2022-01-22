package com.example.inventory

import android.app.Application
import com.example.inventory.model.ItemRoomDatabase

class InventoryApplication : Application() {
    val database : ItemRoomDatabase by lazy { ItemRoomDatabase.database(this) }
}
