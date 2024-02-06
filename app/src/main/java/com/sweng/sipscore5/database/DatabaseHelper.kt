package com.sweng.sipscore5.database

import java.sql.Connection
import java.sql.DriverManager

class DatabaseHelper {

    fun connect(): Connection? {
        var connection: Connection? = null
        try {
            val url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11682359"
            val username = "sql11682359"
            val password = "PAj19BFVvG"

            connection = DriverManager.getConnection(url, username, password)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return connection
    }
}
