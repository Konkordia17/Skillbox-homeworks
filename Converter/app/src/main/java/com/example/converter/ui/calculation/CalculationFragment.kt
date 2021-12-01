package com.example.converter.ui.calculation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.converter.R
import com.example.converter.data.model.Valute
import com.example.converter.databinding.FragmentCalculationBinding
import com.example.converter.ui.currenciesList.CurrenciesListFragment

class CalculationFragment : Fragment(R.layout.fragment_calculation) {
    private lateinit var binding: FragmentCalculationBinding
    private val viewModel: CalculationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelState()
        binding.inputAmountEditText.addTextChangedListener {
            viewModel.setFromAmount(binding.inputAmountEditText.text.toString())
        }

        binding.changeInputCurrencyButton.setOnClickListener {
            setFragmentResultListener(CurrenciesListFragment.KEY) { _, bundle ->
                val currency = bundle.getParcelable<Valute>("1")
                if (currency != null) {
                    viewModel.setFromCurrency(currency)
                }
            }
            openCurrencyList()
        }
        binding.changeOutputCurrencyButton.setOnClickListener {
            setFragmentResultListener(CurrenciesListFragment.KEY) { requestKey, bundle ->
                val currency = bundle.getParcelable<Valute>("1")
                if (currency != null) {
                    viewModel.setInCurrency(currency)
                }
            }
            openCurrencyList()
        }
        binding.changeValueButton.setOnClickListener {
            viewModel.changeCurrencies()
        }
    }

    private fun observeViewModelState() {
        viewModel.currencyFrom.observe(viewLifecycleOwner) {
            it?.let { binding.inputCurrencyTextView.text = it.charCode }
        }
        viewModel.currencyIn.observe(viewLifecycleOwner) {
            binding.outputCurrencyTextView.text = it.charCode
        }
        viewModel.amountIn.observe(viewLifecycleOwner) {
            binding.converterTextView.text = it.toString()
        }
        viewModel.amountFrom.observe(viewLifecycleOwner) {
            viewModel.calculate()
        }
    }

    private fun openCurrencyList() {
        findNavController().navigate(R.id.action_calculationFragment_to_currenciesListFragment2)
    }
}



