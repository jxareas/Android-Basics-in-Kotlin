package com.example.inventory.utils

import com.google.android.material.textfield.TextInputEditText

val TextInputEditText.string : String
    get() = this.text.toString()