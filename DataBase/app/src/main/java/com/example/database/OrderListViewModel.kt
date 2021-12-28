package com.example.database

import androidx.lifecycle.*
import com.example.database.data.Order
import com.example.database.data.OrderStatus
import kotlinx.coroutines.launch
import kotlin.random.Random

class OrderListViewModel : ViewModel() {
    private val products = listOf(
        "Огурцы", "Томаты", "Свинина", "Яйца",
        "Молоко", "Хлеб", "Масло", "Сок", "Лук", "Перец", "Рыба", "Курица", "Ветчина", "Майонез"
    )
    private val costs = listOf(100, 500, 200, 250, 320, 184, 400, 122, 116, 520, 452, 369, 753, 453)
    private var ordersList = mutableListOf<Order>()
    private val ordersLiveData = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>>
        get() = ordersLiveData
    private val orderRepository = OrderRepository()

    fun addProductToDB(order: Order) {
        viewModelScope.launch {
            orderRepository.saveOrder(order)
        }
    }

    fun getOrders() {
        for (i in 1..30) {
            ordersList.add(
                Order(
                    i,
                    Random.nextInt(1, 3),
                    costs.random(),
                    products.random(),
                    OrderStatus.values().random()
                )
            )
        }
        ordersLiveData.value = ordersList
    }
}