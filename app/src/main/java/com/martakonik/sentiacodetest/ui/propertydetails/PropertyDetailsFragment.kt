package com.martakonik.sentiacodetest.ui.propertydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.martakonik.sentiacodetest.databinding.FragmentPropertyDetailsBinding

class PropertyDetailsFragment : Fragment() {

    private var _binding: FragmentPropertyDetailsBinding? = null
    private val viewModel: PropertyDetailsViewModel by viewModels()
    private val binding get() = _binding!!
    private val args: PropertyDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPropertyDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.propertyIdTextView.text = args.propertyId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}