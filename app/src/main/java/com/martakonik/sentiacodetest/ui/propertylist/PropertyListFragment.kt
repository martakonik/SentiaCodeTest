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
import com.martakonik.sentiacodetest.databinding.FragmentPropertyListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyListFragment : Fragment() {

    private var _binding: FragmentPropertyListBinding? = null
    private val viewModel: PropertyListViewModel by viewModels()

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPropertyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val propertyAdapter = PropertyAdapter() { propertyId ->
            val action =
                PropertyListFragmentDirections.actionFragmentPropertyListToFragmentPropertyDetails(
                    propertyId
                )
            findNavController().navigate(action)
        }
        binding.propertyRecyclerView.adapter = propertyAdapter
        binding.propertyRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.getPropertyList()

        viewModel.state.observe(viewLifecycleOwner, Observer {
            it.let { list -> //update recycler view
                val i = list.size
                propertyAdapter.dataSet = list
                propertyAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}