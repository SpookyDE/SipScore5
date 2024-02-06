package com.sweng.sipscore5.database
import com.sweng.sipscore5.models.User
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

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
}