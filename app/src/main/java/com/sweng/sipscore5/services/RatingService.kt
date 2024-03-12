package com.sweng.sipscore5.services

interface RatingService {

    fun calculateRating(drinkId : Int) : Int?

    fun checkIfRatingExists(user : String, drinkId : Int) : Boolean

    fun insertRating (user : String, drinkId : Int, rating : Int)

    fun updateRating (user : String, drinkId : Int, rating : Int)
}