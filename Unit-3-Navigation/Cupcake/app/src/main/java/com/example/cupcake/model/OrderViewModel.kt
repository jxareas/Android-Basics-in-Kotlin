package com.example.cupcake.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
//    init {
//        resetOrder()
//    }

    private val _quantity = MutableLiveData(0)
    val quantity : LiveData<Int> = _quantity

    private val _cupcakeFlavor = MutableLiveData("")
    val flavor : LiveData<String> = _cupcakeFlavor

    val dateOptions = getPickupOptions()
    private val _date = MutableLiveData(dateOptions[0])
    val date : LiveData<String> = _date

    private val _price = MutableLiveData(0.0)
    val price : LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    fun setQuantity(numberCupcakes : Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setCupcakeFlavor(flavor : String) = _cupcakeFlavor.apply liveData@{
        value = flavor
        Log.d(TAG, "CupcakeFlavor: ${this@liveData.value}")
    }

    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    companion object  {
        private val TAG = this::class.java.simpleName
    }

    fun setDate(pickupDate : String) = _date.apply {

        value = pickupDate
        updatePrice()
        Log.d(TAG, "Date: ${this@apply.value}")
    }

    fun hasNoFlavorSet() : Boolean = _cupcakeFlavor.value.isNullOrEmpty()

    private fun getPickupOptions() : List<String> {
        val pickupOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Create a list of dates starting with the current date and the following 3 dates
        repeat(4) {
            pickupOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return pickupOptions
    }

//    private fun resetOrder() {
//        _quantity.value = 0
//        _cupcakeFlavor.value = ""
//        _date.value = dateOptions[0]
//        _price.value = 0.0
//    }




}