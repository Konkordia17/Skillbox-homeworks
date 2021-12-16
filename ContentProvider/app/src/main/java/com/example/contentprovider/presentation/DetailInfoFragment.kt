package com.example.contentprovider.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.contentprovider.R
import com.example.contentprovider.databinding.FragmentDetailInfoBinding

class DetailInfoFragment : Fragment(R.layout.fragment_detail_info) {
    private lateinit var binding: FragmentDetailInfoBinding
    private val args: DetailInfoFragmentArgs by navArgs()
    private val viewModel: DetailInfoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameContactTextView.text = args.contact.name
        binding.phonesContact.text = args.contact.phone.joinToString(", ")
        binding.emailsContact.text = args.contact.email.joinToString(", ")
        binding.deleteContactButton.setOnClickListener {
            viewModel.delete(
                args.contact.id
            )
        }
    }
}