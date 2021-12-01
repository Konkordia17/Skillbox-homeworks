package com.example.converter.ui.calculation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.converter.data.model.Valute
import java.math.RoundingMode

class CalculationViewModel : ViewModel() {
    private val amountConversionFromLiveData = MutableLiveData<String>()
    private val amountConversionInLiveData = MutableLiveData<Double>()
    private val currencyInLiveData = MutableLiveData<Valute>()
    private val currencyFromLiveData = MutableLiveData<Valute>()

    val amountFrom: LiveData<String>
        get() = amountConversionFromLiveData

    val amountIn: LiveData<Double>
        get() = amountConversionInLiveData

    val currencyFrom: LiveData<Valute>
        get() = currencyFromLiveData

    val currencyIn: LiveData<Valute>
        get() = currencyInLiveData

    fun setInCurrency(valute: Valute) {
        currencyInLiveData.value = valute
        calculate()
    }

    fun setFromCurrency(valute: Valute) {
        currencyFromLiveData.value = valute
        calculate()
    }

    fun setFromAmount(text: String) {
        amountConversionFromLiveData.value = text
    }

    fun calculate() {
        val fromAmount = amountConversionFromLiveData.value.toString().toDoubleOrNull()
        val fromCurrency = currencyFromLiveData.value?.value
            ?.replace(",", ".")?.toDoubleOrNull()
        val inCurrency = currencyInLiveData.value?.value
            ?.replace(",", ".")?.toDoubleOrNull()
        if (fromAmount != null && fromCurrency != null && inCurrency != null) {
            val result = (fromAmount * fromCurrency / inCurrency).toBigDecimal()
                .setScale(4, RoundingMode.UP).toDouble()
            amountConversionInLiveData.value = result
        }
    }

    fun changeCurrencies() {
        val tempValute = currencyFromLiveData.value
        currencyFromLiveData.value = currencyInLiveData.value
        tempValute?.let {
            currencyInLiveData.value = it
            calculate()
        }
    }
}


