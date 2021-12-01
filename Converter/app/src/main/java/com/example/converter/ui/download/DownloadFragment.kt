package com.example.converter.ui.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.converter.R
import com.example.converter.databinding.FragmentDownlandBinding

class DownloadFragment : Fragment(R.layout.fragment_downland) {
    private lateinit var binding: FragmentDownlandBinding
    private val downloadViewModel: DownloadViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownlandBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        downloadViewModel.downloadFile()
        observeViewModelState()
    }

    private fun observeViewModelState() {
        downloadViewModel.liveData.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_downloadFragment_to_calculationFragment)
            }
        }
    }
}