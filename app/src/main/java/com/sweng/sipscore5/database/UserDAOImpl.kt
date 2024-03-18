package com.sweng.sipscore5.database

import com.sweng.sipscore5.models.User
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class UserDAOImpl : UserDAO {
    private val url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11682359"
    private val dbUser = "sql11682359"
    private val dbPassword = "PAj19BFVvG"
    override fun addUser(username: String, password: String) {
        try {
            val connection: Connection = DriverManager.getConnection(url, dbUser, dbPassword)
            val statement: Statement = connection.createStatement()

            val sql = "INSERT INTO user (username, password) VALUES ('$username', '$password')"
            statement.executeUpdate(sql)

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getUserByName(username: String): User? {
        var user: User? = null
        try {
            val connection: Connection = DriverManager.getConnection(url, dbUser, dbPassword)
            val statement: Statement = connection.createStatement()

            val sql = "SELECT * FROM user WHERE username='$username'"
            val resultSet: ResultSet = statement.executeQuery(sql)

            if (resultSet.next()) {
                val foundUsername = resultSet.getString("username")
                val foundPassword = resultSet.getString("password")

                user = User(foundUsername, foundPassword)
            }

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return user
    }

    override fun changePassword(username: String, password: String) {
        try {
            val connection: Connection = DriverManager.getConnection(url, dbUser, dbPassword)
            val statement: Statement = connection.createStatement()

            val sql = "UPDATE user SET password='$password' WHERE username='$username'"
            statement.executeUpdate(sql)

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun deleteUser(username: String) {
        try {
            val connection: Connection = DriverManager.getConnection(url, dbUser, dbPassword)
            val statement: Statement = connection.createStatement()

            val sql = "DELETE FROM user WHERE username='$username'"
            statement.executeUpdate(sql)

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}