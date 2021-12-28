package com.example.database

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.databinding.FragmentProductBasketBinding

class ProductsBasketFragment : Fragment(R.layout.fragment_product_basket) {
    private lateinit var binding: FragmentProductBasketBinding
    private var productsBasketAdapter: ProductsBasketAdapter? = null
    private val productsViewModel: ProductsBasketViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsViewModel.getProductList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBasketBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductsBasketList()
        liveDataObserve()
    }

    private fun initProductsBasketList() {
        productsBasketAdapter = ProductsBasketAdapter {
            productsViewModel.deleteProduct(it)
        }
        with(binding.basketList) {
            adapter = productsBasketAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun liveDataObserve() {
        productsViewModel.basket.observe(viewLifecycleOwner) {
            productsBasketAdapter?.updateProductsList(it)
        }
    }
}