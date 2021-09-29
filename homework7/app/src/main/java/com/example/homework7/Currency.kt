package com.example.homework7

enum class Currency {
    RUBLE,
    DOLLAR,
    EURO;

    companion object {
        val nationalCurrency = RUBLE
    }


    object CurrencyConverter {
        val rubleExchangeRate = 72.51
        val euroExchangeRate = 0.86
    }
}

val Currency.isNationalCurrency: Boolean
    get() = this == Currency.nationalCurrency

fun Currency.convertToUSD(value: Double): Double {
    return when (this) {
        Currency.RUBLE -> value / Currency.CurrencyConverter.rubleExchangeRate
        Currency.EURO -> value / Currency.CurrencyConverter.euroExchangeRate
        Currency.DOLLAR -> value
    }

}

