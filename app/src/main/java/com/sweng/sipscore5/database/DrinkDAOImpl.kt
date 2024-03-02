package com.sweng.sipscore5.database

import com.sweng.sipscore5.models.Drink
import java.sql.Connection
import java.sql.DriverManager

class DrinkDAOImpl : DrinkDAO {
    private val dbUrl = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11682359"
    private val dbUser = "sql11682359"
    private val dbPassword = "PAj19BFVvG"
    override fun getAllDrinks(): MutableList<Drink> {
        val drinks = mutableListOf<Drink>()
        var connection: Connection? = null
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
            val statement = connection.prepareStatement("SELECT * FROM drinks")
            val resultSet = statement.executeQuery()
            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val name = resultSet.getString("name")
                val category = resultSet.getString("category")
                val rating = resultSet.getInt("rating")
                drinks.add(Drink(id, name, category, rating))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }
        return drinks
    }

    override fun getAllDrinksByCategory(category: String): MutableList<Drink> {
        val drinks = mutableListOf<Drink>()
        var connection: Connection? = null
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
            val statement = connection.prepareStatement("SELECT * FROM drinks WHERE category=?")
            statement.setString(1, category)
            val resultSet = statement.executeQuery()
            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val name = resultSet.getString("name")
                val rating = resultSet.getInt("rating")
                drinks.add(Drink(id, name, category, rating))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }
        return drinks
    }


    override fun getDrinkById(drinkId: Int): Drink? {
        var drink: Drink? = null
        var connection: Connection? = null
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
            val statement = connection.prepareStatement("SELECT * FROM drinks WHERE id='$drinkId'")
            statement.setInt(1, drinkId)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                val name = resultSet.getString("name")
                val category = resultSet.getString("category")
                val rating = resultSet.getInt("rating")
                drink = Drink(drinkId, name, category, rating)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.close()
        }
        return drink
    }


    override fun updateRating(drinkId: Int, newRating: Int) {
        TODO("Not yet implemented")
    }

}