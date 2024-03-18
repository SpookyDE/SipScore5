package com.sweng.sipscore5.database

import com.sweng.sipscore5.models.User

interface UserDAO {
    fun addUser (username : String, password : String)

    fun getUserByName (username : String) : User?

    fun changePassword (username : String, password : String)

    fun deleteUser (username : String)

}