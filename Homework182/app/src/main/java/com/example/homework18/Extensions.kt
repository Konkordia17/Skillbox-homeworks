package com.example.homework18

import android.service.media.MediaBrowserService
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false):View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}