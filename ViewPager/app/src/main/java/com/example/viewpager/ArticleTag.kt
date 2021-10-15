package com.example.viewpager

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class ArticleTag(val text: String) : Parcelable {
    POLITIC("Политика"),
    TECHNOLOGIES("технологии"),
    NEWS("Новости")
}