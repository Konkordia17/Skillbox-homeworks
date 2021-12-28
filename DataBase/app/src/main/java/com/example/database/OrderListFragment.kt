package com.example.database

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.data.Buyer
import com.example.database.databinding.FragmentOrdersListBinding
import com.example.database.db.DataBase
import kotlinx.coroutines.launch

class OrderListFragment : Fragment(R.layout.fragment_orders_list) {
    private var orderAdapter: OrderAdapter? = null
    private lateinit var binding: FragmentOrdersListBinding
    private val viewModel: OrderListViewModel by viewModels()
    private val buyerDao = DataBase.instance.buyerDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getOrders()
        saveBuyers()
        testRelation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observe()
        binding.basketButton.setOnClickListener {
            findNavController().navigate(R.id.action_orderListFragment_to_productsBasketFragment)
        }
    }

    private fun initList() {
        orderAdapter = OrderAdapter {
            viewModel.addProductToDB(it)
        }
        with(binding.orderList) {
            adapter = orderAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observe() {
        viewModel.orders.observe(viewLifecycleOwner) {
            orderAdapter?.updateList(it)
        }
    }

    private fun saveBuyers() {
        val buyers = listOf(
            Buyer(
                id = 1,
                "Ivan", "Petrov", "Pushkin street"
            ),
            Buyer(
                id = 2, "Alena", "Ivanova", "Gagarin Street"
            )
        )
        lifecycleScope.launch {
            buyerDao.insertBuyers(buyers)
        }
    }

    private fun testRelation() {
        lifecycleScope.launch {
            val buyers = buyerDao.getAllUsersWithRelations()
            Log.d("qwerty", buyers.toString())
        }
    }
}
