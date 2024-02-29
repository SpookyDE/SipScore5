package com.sweng.sipscore5.services

import com.sweng.sipscore5.database.DrinkDAO
import com.sweng.sipscore5.database.DrinkDAOImpl
import com.sweng.sipscore5.models.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DrinkServiceImpl : DrinkService {
    private val dbManager: DrinkDAO = DrinkDAOImpl()
    override fun getAllDrinks(): MutableList<Drink> {
        var drinks = mutableListOf<Drink>()
        runBlocking {
            launch(Dispatchers.IO) {
                drinks = dbManager.getAllDrinks()
            }
        }
        return drinks
    }
    override fun getDrink(drinkId: Int): Drink? {
        var drink: Drink? = null
        runBlocking {
            launch(Dispatchers.IO) {
                drink = dbManager.getDrinkById(drinkId)
            }
        }
        return drink
    }

    override fun getWaters(): List<Drink> {
        var drinks = mutableListOf<Drink>()
        runBlocking {
            launch(Dispatchers.IO) {
                drinks = dbManager.getAllDrinksByCategory("Wasser")!!
            }
        }
        return drinks
    }

    override fun getBeers(): List<Drink> {
        var drinks = mutableListOf<Drink>()
        runBlocking {
            launch(Dispatchers.IO) {
                drinks = dbManager.getAllDrinksByCategory("Bier")!!
            }
        }
        return drinks
    }

    override fun getEnergyDrinks(): List<Drink> {
        var drinks = mutableListOf<Drink>()
        runBlocking {
            launch(Dispatchers.IO) {
                drinks = dbManager.getAllDrinksByCategory("Energydrink")!!
            }
        }
        return drinks
    }

    override fun getSoftdrinks(): List<Drink> {
        var drinks = mutableListOf<Drink>()
        runBlocking {
            launch(Dispatchers.IO) {
                drinks = dbManager.getAllDrinksByCategory("Softdrink")!!
            }
        }
        return drinks
    }

    override fun getSpirits(): List<Drink> {
        var drinks = mutableListOf<Drink>()
        runBlocking {
            launch(Dispatchers.IO) {
                drinks = dbManager.getAllDrinksByCategory("Spirituose")!!
            }
        }
        return drinks
    }

    override fun calculateRating(userRating: Int): Int {
        TODO("Not yet implemented")
    }


}