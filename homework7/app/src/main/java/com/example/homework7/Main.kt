package com.example.homework7

fun main() {
    val virtualWallet = VirtualWallet()
    virtualWallet.addMoney(Currency.EURO, 500)
    virtualWallet.addMoney(Currency.RUBLE, 1500)
    virtualWallet.addMoney(Currency.DOLLAR, 100)

    println("Количество денег в виртуальном кошельке: ${virtualWallet.moneyInUSD()}$")

    val realWallet = RealWallet()
    realWallet.addMoney(Currency.DOLLAR, 100,5)
    realWallet.addMoney(Currency.DOLLAR, 100,5)
    realWallet.addMoney(Currency.DOLLAR, 100,5)
    realWallet.addMoney(Currency.DOLLAR, 100,5)



    println("Количество денег в реальном кошельке: ${realWallet.moneyInUSD()}$")

}

