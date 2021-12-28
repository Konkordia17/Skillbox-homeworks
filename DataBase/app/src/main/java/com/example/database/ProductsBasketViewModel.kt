package com.example.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.data.Order
import kotlinx.coroutines.launch

class ProductsBasketViewModel : ViewModel() {
    private val productsBasketViewModel = MutableLiveData<List<Order>>()
    val basket: LiveData<List<Order>>
        get() = productsBasketViewModel
    private val repository = OrderRepository()
    private var productsList = emptyList<Order>()

    fun getProductList() {
        viewModelScope.launch {
            productsList = repository.getAllOrder()
            productsBasketViewModel.value = productsList
        }
    }

    fun deleteProduct(order: Order) {
        viewModelScope.launch {
            repository.remoteProduct(order)
            getProductList()
        }
    }
}