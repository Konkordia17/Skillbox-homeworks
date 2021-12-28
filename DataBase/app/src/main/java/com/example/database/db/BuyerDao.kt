package com.example.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.data.Buyer
import com.example.database.data.BuyerWithRelations

@Dao
interface BuyerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuyers(buyers: List<Buyer>)

    @Query("SELECT *FROM ${BuyersContract.TABLE_NAME}")
    suspend fun getAllUsersWithRelations(): List<BuyerWithRelations>
}