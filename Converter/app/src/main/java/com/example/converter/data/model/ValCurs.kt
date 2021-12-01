package com.example.converter.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Parcelize
@Root(name = "ValCurs", strict = false)
data class ValCurs @JvmOverloads constructor(
    @field:ElementList(name = "Valute", inline = true) var valutes: List<Valute>? = null
): Parcelable