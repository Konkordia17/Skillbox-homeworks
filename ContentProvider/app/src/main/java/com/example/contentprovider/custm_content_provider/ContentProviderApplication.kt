package com.example.contentprovider.custm_content_provider

import android.app.Application
import android.os.StrictMode
import com.example.contentprovider.BuildConfig

class ContentProviderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyDeath()
                    .build()
            )
        }
    }
}