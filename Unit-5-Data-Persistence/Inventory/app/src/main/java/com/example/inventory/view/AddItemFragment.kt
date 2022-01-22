package com.example.inventory.view

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.inventory.InventoryApplication
import com.example.inventory.databinding.FragmentAddItemBinding
import com.example.inventory.model.Item
import com.example.inventory.utils.string
import com.example.inventory.viewmodel.InventoryViewModel
import com.example.inventory.viewmodel.InventoryViewModelFactory

/**
 * Fragment to add or update an item in the Inventory database.
 */
class AddItemFragment : Fragment() {

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }
    private lateinit var item: Item

    private val navigationArgs: ItemDetailFragmentArgs by navArgs()

    // Binding object instance corresponding to the fragment_add_item.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.saveAction.setOnClickListener {
            addNewItem()
        }
    }

    private fun isEntryValid(): Boolean = binding.run {
        viewModel.isEntryValid(itemName.string, itemPrice.string, itemCount.string)
    }

    private fun addNewItem() = binding.run {
        if (isEntryValid()) {
            viewModel.addNewItemEntry(itemName.string, itemPrice.string, itemCount.string)
        }
        val action = AddItemFragmentDirections.actionAddItemFragmentToItemListFragment()
        findNavController().navigate(action)


    }

    /**
     * Called before fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}
