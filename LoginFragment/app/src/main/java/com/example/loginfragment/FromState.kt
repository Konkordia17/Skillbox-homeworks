package com.example.loginfragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState(val valid: Boolean, val message: String) : Parcelable
