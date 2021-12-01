package com.example.converter.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Parcelize
@Root(strict = false, name = "Valute")
data class Valute @JvmOverloads constructor(
    @field:Element(name = "CharCode", required = false) var charCode: String? = null,
    @field:Element(name = "Nominal", required = false) var nominal: String? = null,
    @field:Element(name = "Name", required = false) var name: String? = null,
    @field:Element(name = "Value", required = false) var value: String? = null
):Parcelable
