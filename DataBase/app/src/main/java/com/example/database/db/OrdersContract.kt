package com.example.database.db

object OrdersContract {
    const val TABLE_NAME = "orders"

    object Columns {
        const val ID = "id"
        const val BUYER_ID = "buyer_id"
        const val COST = "cost"
        const val PRODUCT = "product"
        const val STATUS = "status"
    }
}