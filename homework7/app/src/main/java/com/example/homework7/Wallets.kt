package com.example.homework7

sealed class Wallets {
    abstract fun moneyInUSD(): Double
}

class VirtualWallet : Wallets() {
    private var dollar: Double = 0.0
    private var euro: Double = 0.0
    private var ruble: Double = 0.0

    fun addMoney(typeCurrency: Currency, amount: Int) {

        when (typeCurrency) {
            Currency.RUBLE -> ruble += amount
            Currency.EURO -> euro += amount
            Currency.DOLLAR -> dollar += amount
        }
    }

    override fun moneyInUSD(): Double {
        return Currency.RUBLE.convertToUSD(ruble) + dollar + Currency.EURO.convertToUSD(euro)
    }

}

class RealWallet() : Wallets() {
    private val dollars: MutableMap<Int, Int> = mutableMapOf()
    private val euros: MutableMap<Int, Int> = mutableMapOf()
    private val rubles: MutableMap<Int, Int> = mutableMapOf()

    fun addMoney(typeCurrency: Currency, nominalValue: Int, quantity: Int) {
        when (typeCurrency) {
            Currency.RUBLE -> if (rubles.containsKey(nominalValue)) {
                rubles[nominalValue] = rubles.getValue(nominalValue) + quantity
            } else rubles[nominalValue] = quantity
            Currency.DOLLAR -> if (dollars.containsKey(nominalValue)) {
                dollars[nominalValue] = dollars.getValue(nominalValue) + quantity
            } else dollars[nominalValue] = quantity
            Currency.EURO -> if (euros.containsKey(nominalValue)) {
                euros[nominalValue] = euros.getValue(nominalValue) + quantity
            } else euros[nominalValue] = quantity
        }

    }

    override fun moneyInUSD(): Double {
        var sumRub = 0
        var sumEuro = 0
        var sumDollar = 0
        rubles.forEach {
            sumRub += it.key * it.value
        }
        euros.forEach {
            sumEuro += it.key * it.value
        }
        dollars.forEach {
            sumDollar += it.key * it.value
        }
        return Currency.RUBLE.convertToUSD(sumRub.toDouble()) + sumDollar +
                Currency.EURO.convertToUSD(sumEuro.toDouble())
    }
}

