package com.sweng.sipscore5.services

import com.sweng.sipscore5.database.UserDAO
import com.sweng.sipscore5.database.UserDAOImpl
import com.sweng.sipscore5.models.User

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class UserService {
    private val dbManager : UserDAO = UserDAOImpl()


    fun registerUser(user : User) {
        runBlocking {
            launch(Dispatchers.IO) {
                dbManager.addUser(user.username, user.password)
            }
        }
    }

    fun checkUserExists(username: String): Boolean {
        var exists = false
        runBlocking {
            launch(Dispatchers.IO) {
                if (dbManager.getUserByName(username) != null)
                    exists = true
            }
        }
        return exists
    }

    fun checkLoginCredentials(loggedUser : User): Boolean {
        var isValid = false
        runBlocking {
            launch(Dispatchers.IO) {
                val storedUser = dbManager.getUserByName(loggedUser.username)
                if (storedUser!!.password == loggedUser.password)
                    isValid = true
            }
        }
        return isValid
    }

    fun deregisterUser(user : User) {
        runBlocking {
            launch(Dispatchers.IO) {
                dbManager.deleteUser(user.username)
            }
        }
    }
}