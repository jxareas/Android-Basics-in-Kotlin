package com.example.inventory.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    // Because of the Flow return type, ROOM runs this query on the background thread
    // You don't have to specify it explicitly as a suspend function
    @Query("SELECT * FROM item WHERE item_id = :itemId")
     fun getItem(itemId : Int) : Flow<Item>

    @Query("SELECT * FROM item ORDER BY name ASC")
     fun getItems() : Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item : Item)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(item : Item)

    @Delete
    suspend fun delete(item : Item)
}