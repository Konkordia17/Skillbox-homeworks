package com.example.database.db

import android.content.Context
import androidx.room.Room

object DataBase {
    lateinit var instance: OrderDataBase
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            OrderDataBase::class.java,
            OrderDataBase.DB_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}