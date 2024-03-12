package com.sweng.sipscore5.services

import com.sweng.sipscore5.database.RatingDAO
import com.sweng.sipscore5.database.RatingDAOImpl
import com.sweng.sipscore5.models.Rating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RatingServiceImpl : RatingService {
    private val dbManager: RatingDAO = RatingDAOImpl()
    override fun calculateRating(drinkId: Int) : Int? {
        var avgRating: Int? = null
        runBlocking {
            launch(Dispatchers.IO) {
                val ratings = dbManager.getRatingsByDrinkID(drinkId) ?: return@launch
                avgRating = if (ratings.isEmpty()) {
                    null // Return null if there are no ratings
                } else {
                    val totalRating = ratings.sumOf { it.value }
                    totalRating / ratings.size
                }
            }.join()
        }
        return avgRating
    }

    override fun checkIfRatingExists(user: String, drinkId: Int): Boolean {
        var ratings: MutableList<Rating>
        var exists = false
        runBlocking {
            launch(Dispatchers.IO) {
                ratings = dbManager.getRatingsForDrinkByUserID(user, drinkId)!!
                if (ratings.isNotEmpty())
                    exists = true
            }
        }
        return exists
    }

    override fun insertRating(user: String, drinkId: Int, rating: Int) {
        runBlocking {
            launch(Dispatchers.IO) {
                dbManager.insertRating(user, drinkId, rating)
            }
        }
    }

    override fun updateRating(user: String, drinkId: Int, rating: Int) {
        runBlocking {
            launch(Dispatchers.IO) {
                dbManager.updateRating(user, drinkId, rating)
            }
        }
    }
}