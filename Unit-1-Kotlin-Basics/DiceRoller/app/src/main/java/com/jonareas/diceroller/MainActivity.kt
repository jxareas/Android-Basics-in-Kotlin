package com.jonareas.diceroller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollDiceButton : Button = findViewById(R.id.button_roll_dice)
        rollDiceButton.setOnClickListener {
            rolLDice()
        }
    }

    private fun rolLDice() {

//        showDiceRolledToast()

        val textViewDiceNumber : TextView = findViewById(R.id.text_view_dice)
        val textViewRolledDice : TextView = findViewById(R.id.text_view_rolled_dice)
        val imageViewDice : ImageView = findViewById(R.id.image_view_dice)

        textViewRolledDice.visibility = View.VISIBLE
        val dice = Dice().roll()

        textViewDiceNumber.text = dice.toString()

        val diceDrawableResource = when(dice) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_1
        }

        imageViewDice.setImageResource(diceDrawableResource)

    }

    private fun showDiceRolledToast() {
        Toast.makeText(
            this,
            getString(R.string.toast_dice_rolled),
            Toast.LENGTH_SHORT
        ).show()
    }
}

class Dice(val numSides : Int = 6) {
    fun roll() = (1..numSides).random()
}