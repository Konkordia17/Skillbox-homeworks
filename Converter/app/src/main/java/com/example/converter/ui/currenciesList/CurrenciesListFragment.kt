package com.example.converter.ui.currenciesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.converter.ui.currenciesList.adapter.CurrencyAdapter
import com.example.converter.R
import com.example.converter.databinding.FragmentCurrenciesListBinding

class CurrenciesListFragment : Fragment(R.layout.fragment_currencies_list) {
    private lateinit var binding: FragmentCurrenciesListBinding
    private var currencyAdapter: CurrencyAdapter? = null
    private val currenciesListViewModel: CurrenciesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrenciesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeViewModelState()
        currenciesListViewModel.getListWithCoroutine()
    }

    private fun initList() {
        currencyAdapter = CurrencyAdapter{
            currency ->  setFragmentResult(KEY, bundleOf("1" to currency))
            findNavController().navigateUp()
        }

        with(binding.currencyListRecyclerView) {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeViewModelState() {
        currenciesListViewModel.currencies.observe(viewLifecycleOwner) { newList ->
            currencyAdapter?.updateValutes(newList)
        }
    }
    companion object{
        val KEY = "Currency list"
    }
}
