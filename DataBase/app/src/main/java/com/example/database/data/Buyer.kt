package com.example.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.database.db.BuyersContract

@Entity(
    tableName = BuyersContract.TABLE_NAME,
    indices = [Index(BuyersContract.Column.NAME)]
)
data class Buyer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BuyersContract.Column.ID)
    val id: Int,
    @ColumnInfo(name = BuyersContract.Column.NAME)
    val name: String,
    @ColumnInfo(name = BuyersContract.Column.SURNAME)
    val surname: String,
    @ColumnInfo(name = BuyersContract.Column.ADDRESS)
    val address: String,
    @ColumnInfo(name = BuyersContract.Column.AGE, defaultValue = "0")
    val age: Int,
)
