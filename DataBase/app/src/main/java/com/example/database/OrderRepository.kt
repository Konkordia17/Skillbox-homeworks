package com.example.database

import com.example.database.data.Order
import com.example.database.db.DataBase

class OrderRepository {

    private val orderDao = DataBase.instance.orderDao()

    suspend fun getAllOrder(): List<Order> {
        return orderDao.getAllOrders()
    }

    suspend fun saveOrder(order: Order) {
        orderDao.insertOrders(listOf(order))
    }

    suspend fun remoteProduct(order: Order) {
        orderDao.removeProduct(order)
    }
}