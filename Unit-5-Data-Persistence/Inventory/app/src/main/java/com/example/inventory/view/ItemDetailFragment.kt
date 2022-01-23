package com.example.inventory.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.inventory.InventoryApplication
import com.example.inventory.R
import com.example.inventory.databinding.FragmentItemDetailBinding
import com.example.inventory.model.Item
import com.example.inventory.utils.formattedPrice
import com.example.inventory.viewmodel.InventoryViewModel
import com.example.inventory.viewmodel.InventoryViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * [ItemDetailFragment] displays the details of the selected item.
 */
class ItemDetailFragment : Fragment() {
    private lateinit var item: Item
    private val navigationArgs: ItemDetailFragmentArgs by navArgs()
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        initItem()
        return binding.root
    }

    private fun initItem() {
        val id = navigationArgs.itemId
        viewModel.retrieveItem(id).observe(viewLifecycleOwner) { selectedItem ->
            selectedItem?.let {
                item = selectedItem
                bindItemToView(item)
            }
        }
    }





    private fun bindItemToView(item: Item) = with(binding) {
        itemName.text = item.itemName
        itemPrice.text = item.formattedPrice
        itemCount.text = item.quantityInStock.toString()
        sellItem.isEnabled = viewModel.isStockAvailable(item)
        sellItem.setOnClickListener { viewModel.sellItem(item) }
        deleteItem.setOnClickListener { showConfirmationDialog() }
    }

    /**
     * Displays an alert dialog to get the user's confirmation before deleting the item.
     */
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }

    /**
     * Deletes the current item and navigates to the list fragment.
     */
    private fun deleteItem() {
        viewModel.deleteItem(item)
        findNavController().navigateUp()
    }

    /**
     * Called when fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
