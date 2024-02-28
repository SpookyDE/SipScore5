package com.sweng.sipscore5.database

import com.sweng.sipscore5.models.Drink

interface DrinkDAO {

    fun getAllDrinks() : MutableList<Drink>

    fun getAllDrinksByCategory(category : String) : MutableList<Drink>?

    fun getDrinkById(drinkId : Int) : Drink?

    fun updateRating(drinkId: Int, newRating : Int)
}