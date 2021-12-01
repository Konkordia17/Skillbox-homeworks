package com.example.converter.ui.currenciesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.converter.data.Data
import com.example.converter.data.model.Valute

class CurrenciesListViewModel : ViewModel() {
    private val currenciesListLiveData = MutableLiveData<List<Valute>>()

    val currencies: LiveData<List<Valute>>
        get() = currenciesListLiveData

    fun getListWithCoroutine() {
        currenciesListLiveData.value = Data.valutes
    }
}

