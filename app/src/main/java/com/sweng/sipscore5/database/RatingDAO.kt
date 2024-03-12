package com.sweng.sipscore5.database

import com.sweng.sipscore5.models.Rating

interface RatingDAO {
    fun updateRating(user : String, drinkId : Int, newRating : Int)

    fun getRatingsByDrinkID(drinkId : Int) : MutableList<Rating>?

    fun getRatingsForDrinkByUserID(user : String, drinkId : Int) : MutableList<Rating>?

    fun insertRating(user : String, drinkID : Int, rating : Int)


}