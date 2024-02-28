package com.sweng.sipscore5.services

import com.sweng.sipscore5.database.DrinkDAO
import com.sweng.sipscore5.database.DrinkDAOImpl
import com.sweng.sipscore5.models.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DrinkServiceImpl : DrinkService {
    private val dbManager: DrinkDAO = DrinkDAOImpl()
    override fun getFirstDrinkName(): String? {
        var drinks: List<Drink>
        var drinkName = ""
        runBlocking {
            launch(Dispatchers.IO) {
                drinks = dbManager.getAllDrinks()
                drinkName = drinks.firstOrNull()?.name.toString()
            }
        }
        return drinkName
    }
}