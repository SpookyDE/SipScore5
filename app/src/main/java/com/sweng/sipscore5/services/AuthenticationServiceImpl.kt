package com.sweng.sipscore5.services

import com.sweng.sipscore5.database.DatabaseHelper
import com.sweng.sipscore5.database.UserDAO

class AuthenticationServiceImpl : AuthenticationService {
    override fun registerUser(username: String, password: String) {
        val databaseHelper = DatabaseHelper()
        val connection = databaseHelper.connect()
        connection?.use { conn ->
            val userDAO = UserDAO(conn)
            userDAO.createUser(username, password)
        }
    }

    override fun checkLoginCredentials(username: String, password: String): Boolean {
        val databaseHelper = DatabaseHelper()
        val connection = databaseHelper.connect()
        return connection?.use { conn ->
            val userDAO = UserDAO(conn)
            val user = userDAO.getUserByUsername(username)
            user?.password == password
        } ?: false
    }
}