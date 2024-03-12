package com.sweng.sipscore5.database

import com.sweng.sipscore5.models.Drink
import com.sweng.sipscore5.models.Rating
import java.sql.Connection
import java.sql.DriverManager

class RatingDAOImpl : RatingDAO {
    private val dbUrl = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11682359"
    private val dbUser = "sql11682359"
    private val dbPassword = "PAj19BFVvG"
    override fun updateRating(user : String, drinkId: Int, newRating: Int) {
        var connection : Connection? = null
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
            val statement = connection.prepareStatement("UPDATE ratings SET rating = ? WHERE user_id = ? AND drink_id = ?")
            statement.setInt(3, drinkId)
            statement.setString(2, user)
            statement.setInt(1, newRating)
            statement.executeUpdate()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }
    }

    override fun getRatingsByDrinkID(drinkId: Int): MutableList<Rating> {
        val ratings = mutableListOf<Rating>()
        var connection: Connection? = null
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
            val statement = connection.prepareStatement("SELECT * FROM ratings WHERE drink_id=?")
            statement.setInt(1, drinkId)
            val resultSet = statement.executeQuery()
            while (resultSet.next()) {
                val user = resultSet.getString("user_id")
                val rating = resultSet.getInt("rating")
                ratings.add(Rating(drinkId, user, rating))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }
        return ratings
    }

    override fun getRatingsForDrinkByUserID(user: String, drinkId: Int): MutableList<Rating> {
        val ratings = mutableListOf<Rating>()
        var connection: Connection? = null
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
            val statement = connection.prepareStatement("SELECT * FROM ratings WHERE user_id=? AND drink_id=?")
            statement.setString(1, user)
            statement.setInt(2, drinkId)
            val resultSet = statement.executeQuery()
            while (resultSet.next()) {
                val rating = resultSet.getInt("rating")
                ratings.add(Rating(drinkId, user, rating))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }
        return ratings
    }

    override fun insertRating(user: String, drinkID: Int, rating: Int) {
        var connection : Connection? = null
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
            val statement = connection.prepareStatement("INSERT INTO ratings (user_id, drink_id, rating) VALUES (?,?,?)")
            statement.setString(1, user)
            statement.setInt(2, drinkID)
            statement.setInt(3, rating)
            statement.executeUpdate()
            connection.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }
    }
}