package com.example.database.db

import androidx.room.*
import com.example.database.data.Order

@Dao
interface OrderDao {

    @Query("SELECT * FROM ${OrdersContract.TABLE_NAME}")
    suspend fun getAllOrders(): List<Order>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: List<Order>)

    @Delete
    suspend fun removeProduct(order: Order)
}