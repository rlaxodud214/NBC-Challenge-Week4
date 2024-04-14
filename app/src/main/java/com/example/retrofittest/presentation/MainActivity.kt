package com.example.retrofittest.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.adapter.PlantAdapter
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.viewModel.PlantViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val plantViewModel: PlantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setPlant()
    }

    private fun setPlant() {
        plantViewModel.getAllPlants()

        plantViewModel.plants.observe(this) {
            val plantAdapter = PlantAdapter(it)

            with(binding.rvImage) {
                adapter = plantAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }
}