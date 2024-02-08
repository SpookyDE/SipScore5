package com.sweng.sipscore5.database
import com.sweng.sipscore5.models.User
import java.sql.Connection

class UserDAO(private val connection: Connection) {

    fun createUser(username: String, password: String): Boolean {
        val query = "INSERT INTO user (username, password) VALUES (?, ?)"
        val preparedStatement = connection.prepareStatement(query)

        preparedStatement.setString(1, username)
        preparedStatement.setString(2, password)

        return preparedStatement.executeUpdate() > 0
    }

    fun deleteUserById(id: Int): Boolean {
        val query = "DELETE FROM user WHERE id = ?"
        val preparedStatement = connection.prepareStatement(query)

        preparedStatement.setInt(1, id)

        return preparedStatement.executeUpdate() > 0
    }

    fun getUserById(id: Int): User? {
        val query = "SELECT * FROM user WHERE id = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, id)
        val resultSet = preparedStatement.executeQuery()

        return if (resultSet.next()) {
            User(
                id = resultSet.getInt("id"),
                username = resultSet.getString("username"),
                password = resultSet.getString("password")
            )
        } else {
            null
        }
    }

    fun getUserByUsername(username: String): User? {
        val query = "SELECT * FROM user WHERE username = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, username)
        val resultSet = preparedStatement.executeQuery()

        return if (resultSet.next()) {
            User(
                id = resultSet.getInt("id"),
                username = resultSet.getString("username"),
                password = resultSet.getString("password")
            )
        } else {
            null
        }
    }
}