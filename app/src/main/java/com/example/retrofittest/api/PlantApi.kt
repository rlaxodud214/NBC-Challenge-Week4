package com.example.retrofittest.api

import com.example.retrofittest.adapter.PlantAdapter
import com.example.retrofittest.model.Plant
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlantApi {
    @GET("/googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
    suspend fun getAllPlants(): List<Plant>

    @GET("~~~~~~/{number}")
    suspend fun getPlant(
        @Path("number") number: Int,
    ): Plant
}