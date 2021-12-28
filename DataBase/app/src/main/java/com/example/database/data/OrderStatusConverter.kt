package com.example.database.data

import androidx.room.TypeConverter
import com.example.database.data.OrderStatus

class OrderStatusConverter {
    @TypeConverter
    fun convertStatusToString(status: OrderStatus): String = status.name

    @TypeConverter
    fun convertStringToStatus(status: String): OrderStatus = OrderStatus.valueOf(status)
}