package com.example.database.db

import android.app.Application

class DataBaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DataBase.init(this)
    }
}