package com.sweng.sipscore5.services

import com.sweng.sipscore5.models.Drink

interface DrinkService {

    fun getAllDrinks() : List<Drink>

    fun getDrink(drinkId : Int) : Drink?

    fun getWaters() : List<Drink>

    fun getBeers() : List<Drink>

    fun getEnergyDrinks() : List<Drink>

    fun getSoftdrinks() : List<Drink>

    fun getSpirits() : List<Drink>

    fun calculateRating(userRating : Int) : Int
}