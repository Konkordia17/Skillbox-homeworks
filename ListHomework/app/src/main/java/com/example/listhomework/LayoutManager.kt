package com.example.listhomework

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class LayoutManager : Parcelable {
    LINEAR_VERTICAL,
    LINEAR_HORIZONTAL,
    GRID,
    STAGGERED_GRID;
}



