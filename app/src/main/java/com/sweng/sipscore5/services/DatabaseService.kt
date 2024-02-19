package com.sweng.sipscore5.services

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DatabaseService {
    private val url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11682359"
    private val user = "sql11682359"
    private val password = "PAj19BFVvG"

    fun registerUser(username: String, uPassword: String) {
        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    val connection: Connection = DriverManager.getConnection(url, user, password)
                    val statement: Statement = connection.createStatement()

                    val sql = "INSERT INTO user (username, password) VALUES ('$username', '$uPassword')"
                    statement.executeUpdate(sql)

                    connection.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}