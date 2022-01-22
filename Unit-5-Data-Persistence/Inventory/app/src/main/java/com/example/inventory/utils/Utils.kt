package com.example.inventory.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.inventory.model.Item
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat

inline fun<reified T : ViewBinding>
        ViewGroup.viewBind(
    crossinline inflater : (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToRoot : Boolean = false) : T =
    inflater.invoke(LayoutInflater.from(context), this, attachToRoot)

val Item.formattedPrice : String
    get() = NumberFormat.getCurrencyInstance().format(itemPrice)

val TextInputEditText.string : String
    get() = text.toString()

