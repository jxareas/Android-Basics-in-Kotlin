package com.jonareas.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jonareas.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val costOfService = binding.costOfService.text.toString().toDoubleOrNull()

        if(costOfService == null) {
            binding.tipResult.text = getString(R.string.tip_amount_default)
            return
        }

        val tipPercentage =
            when(binding.tipOptions.checkedRadioButtonId)
            {
                R.id.option_fifteen_percent -> 0.15
                R.id.option_eighteen_percent -> 0.18
                else -> 0.2
            }

        var tip = costOfService * tipPercentage

        if(binding.roundUpSwitch.isChecked)
            tip = kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}