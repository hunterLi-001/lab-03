package com.example.listycity3

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateListOf

class CityRepository {
    //using mutable listing for later updating the list of cities and province
    private val _cities = mutableStateListOf(
        City("Edmonton", "AB"),
        City("Vancouver", "BC"),
        City("Toronto", "ON")
    )

    val cities: List<City>
        get() = _cities

    //adding new fun to support adding new cities
    fun addCity(city: City) {
        _cities.add(city)
    }

    //fun to track existing city and replace it with updated city
    fun updateCity(oldCity : City, updatedCity: City) {
        val index = _cities.indexOf(oldCity)
        if (index != -1) {
            _cities[index] = updatedCity
        }
    }
}

