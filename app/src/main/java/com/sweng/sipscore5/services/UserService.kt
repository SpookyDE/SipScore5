package com.sweng.sipscore5.services

import com.sweng.sipscore5.models.User
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet

class UserService {
    private val url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11682359"
    private val dbUser = "sql11682359"
    private val dbPassword = "PAj19BFVvG"

    fun registerUser(username: String, password: String) {
        runBlocking {
            launch(Dispatchers.IO) {
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
        }
    }

    fun checkUserExists(username: String): Boolean {
        var exists = false
        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    val connection: Connection = DriverManager.getConnection(url, dbUser, dbPassword)
                    val statement: Statement = connection.createStatement()

                    val sql = "SELECT * FROM user WHERE username='$username'"
                    val resultSet: ResultSet = statement.executeQuery(sql)

                    exists = resultSet.next()

                    connection.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return exists
    }

    fun checkLoginCredentials(user : User): Boolean {
        var isValid = false
        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    val connection: Connection = DriverManager.getConnection(url, dbUser, dbPassword)
                    val statement: Statement = connection.createStatement()

                    val checkUserQuery = "SELECT * FROM user WHERE username='${user.username}'"
                    val userResultSet: ResultSet = statement.executeQuery(checkUserQuery)

                    if (userResultSet.next()) {
                        val storedPassword = userResultSet.getString("password")
                        if (storedPassword == user.password) {
                            isValid = true
                        }
                    }

                    connection.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return isValid
    }
}