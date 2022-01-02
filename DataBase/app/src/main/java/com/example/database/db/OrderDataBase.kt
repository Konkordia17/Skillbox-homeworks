package com.example.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.data.Buyer
import com.example.database.data.Order

@Database(entities = [Order::class, Buyer::class], version = OrderDataBase.DB_VERSION)
abstract class OrderDataBase : RoomDatabase() {

    abstract fun orderDao(): OrderDao
    abstract fun buyerDao(): BuyerDao

    companion object {
        const val DB_VERSION = 2
        const val DB_NAME = "Order-database"
    }
}