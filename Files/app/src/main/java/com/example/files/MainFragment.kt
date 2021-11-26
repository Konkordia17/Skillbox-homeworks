package com.example.files

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.files.databinding.FragmentLayoutBinding

class MainFragment : Fragment(R.layout.fragment_layout) {
    private lateinit var binding: FragmentLayoutBinding
    private val mainFragmentViewModel: MainFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentViewModel.checkIsLoaded()
        observeViewModelState()
        binding.button.setOnClickListener {
            mainFragmentViewModel.checkIfFileExists(binding.editText.text.toString())
        }
    }

    private fun observeViewModelState() {
        mainFragmentViewModel.toastLiveData.observe(viewLifecycleOwner) { it ->
            toast(it)
        }
        mainFragmentViewModel.isVisible.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                binding.editText.isEnabled = false
                binding.button.isEnabled = false
            } else {
                binding.progressBar.visibility = View.GONE
                binding.editText.isEnabled = true
                binding.button.isEnabled = true
            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}

