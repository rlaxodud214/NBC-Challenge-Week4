package com.example.retrofittest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittest.model.Plant
import com.example.retrofittest.repository.Repository
import kotlinx.coroutines.launch

class PlantViewModel : ViewModel() {
    private var _plants = MutableLiveData<List<Plant>>()
    val plants: LiveData<List<Plant>> = _plants

    private val repository = Repository()

    fun getAllPlants() = viewModelScope.launch {
        _plants.value = repository.getAllPlants()
    }
}