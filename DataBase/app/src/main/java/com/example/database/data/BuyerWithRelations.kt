package com.example.database.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.database.db.BuyersContract
import com.example.database.db.OrdersContract

data class BuyerWithRelations(
    @Embedded
    val buyer: Buyer,
    @Relation(
        parentColumn = BuyersContract.Column.ID,
        entityColumn = OrdersContract.Columns.BUYER_ID
    )
    val orders: List<Order>
)