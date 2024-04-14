package com.example.retrofittest.repository

import com.example.retrofittest.api.PlantApi
import com.example.retrofittest.api.RetrofitInstance

class Repository {
    private val client = RetrofitInstance.getInstance()
        .create(PlantApi::class.java)

    suspend fun getAllPlants() = client.getAllPlants()
}