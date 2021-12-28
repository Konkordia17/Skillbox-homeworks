package com.example.database.data

import androidx.room.*
import com.example.database.db.BuyersContract
import com.example.database.db.OrdersContract

@Entity(
    tableName = OrdersContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Buyer::class, parentColumns = [BuyersContract.Column.ID],
        childColumns = [OrdersContract.Columns.BUYER_ID]
    )]
)
@TypeConverters(OrderStatusConverter::class)
data class Order(
    @PrimaryKey
    @ColumnInfo(name = OrdersContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = OrdersContract.Columns.BUYER_ID)
    val buyerId: Int,
    @ColumnInfo(name = OrdersContract.Columns.COST)
    val cost: Int,
    @ColumnInfo(name = OrdersContract.Columns.PRODUCT)
    val products: String,
    @ColumnInfo(name = OrdersContract.Columns.STATUS)
    val status: OrderStatus
)

