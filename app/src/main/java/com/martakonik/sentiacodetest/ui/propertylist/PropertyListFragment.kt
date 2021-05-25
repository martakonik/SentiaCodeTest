package com.martakonik.sentiacodetest.ui.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.martakonik.sentiacodetest.data.Property
import com.martakonik.sentiacodetest.databinding.FragmentPropertyListBinding
import com.martakonik.sentiacodetest.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyListFragment : Fragment() {

    private var _binding: FragmentPropertyListBinding? = null
    private val viewModel: PropertyListViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.load()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPropertyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val propertyAdapter = PropertyAdapter { propertyId ->
            val action =
                PropertyListFragmentDirections.actionFragmentPropertyListToFragmentPropertyDetails(
                    propertyId
                )
            findNavController().navigate(action)
        }

        binding.propertyRecyclerView.adapter = propertyAdapter
        binding.propertyRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.state.observe(viewLifecycleOwner, Observer {
            it.let { uiState ->
                when (uiState) {
                    is UiState.DataReceived -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        loadData(uiState.propertyList, propertyAdapter)
                    }
                    is UiState.ErrorReceived -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        Snackbar.make(
                            binding.propertyRecyclerView,
                            uiState.errorMessage,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    UiState.Loading -> binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun loadData(propertyList: List<Property>, propertyAdapter: PropertyAdapter) {
        propertyAdapter.dataSet = propertyList
        propertyAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}