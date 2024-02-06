package com.sweng.sipscore5.database

import com.sweng.sipscore5.models.Drink
import java.sql.Connection

class DrinkDAO(private val connection: Connection) {

    fun createDrink(name: String, category: String, rating: Int): Boolean {
        val query = "INSERT INTO drinks (name, category, rating) VALUES (?, ?, ?)"
        val preparedStatement = connection.prepareStatement(query)

        preparedStatement.setString(1, name)
        preparedStatement.setString(2, category)
        preparedStatement.setInt(3, rating)

        return preparedStatement.executeUpdate() > 0
    }

    fun getDrinkById(id: Int): Drink? {
        val query = "SELECT * FROM drinks WHERE id = ?"
        val preparedStatement = connection.prepareStatement(query)

        preparedStatement.setInt(1, id)
        val resultSet = preparedStatement.executeQuery()

        return if (resultSet.next()) {
            Drink(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("category"),
                resultSet.getInt("rating")
            )
        } else {
            null
        }
    }

    fun deleteDrinkById(id: Int): Boolean {
        val query = "DELETE FROM drinks WHERE id = ?"
        val preparedStatement = connection.prepareStatement(query)

        preparedStatement.setInt(1, id)

        return preparedStatement.executeUpdate() > 0
    }

    fun getAllDrinks(): List<Drink> {
        val drinkList = mutableListOf<Drink>()
        val query = "SELECT * FROM drinks"
        val preparedStatement = connection.prepareStatement(query)
        val resultSet = preparedStatement.executeQuery()

        while (resultSet.next()) {
            val drink = Drink(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("category"),
                resultSet.getInt("rating")
            )
            drinkList.add(drink)
        }

        return drinkList
    }
}