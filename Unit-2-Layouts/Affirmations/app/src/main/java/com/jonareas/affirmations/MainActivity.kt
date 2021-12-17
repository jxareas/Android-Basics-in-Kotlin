package com.jonareas.affirmations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jonareas.affirmations.adapter.AffirmationAdapter
import com.jonareas.affirmations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()



    }

    private fun setUpRecyclerView() : Unit = binding.recyclerView.run recycler@ {
        this@MainActivity.let context@ {
            adapter = AffirmationAdapter(it)
        }

    }


}