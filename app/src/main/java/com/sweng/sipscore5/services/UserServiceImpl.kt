package com.sweng.sipscore5.services

import com.sweng.sipscore5.database.UserDAO
import com.sweng.sipscore5.database.UserDAOImpl
import com.sweng.sipscore5.models.User

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class UserServiceImpl : UserService {
    private val dbManager : UserDAO = UserDAOImpl()


    override fun registerUser(user : User) {
        runBlocking {
            launch(Dispatchers.IO) {
                dbManager.addUser(user.username, user.password)
            }
        }
    }

    override fun checkUserExists(username: String): Boolean {
        var exists = false
        runBlocking {
            launch(Dispatchers.IO) {
                if (dbManager.getUserByName(username) != null)
                    exists = true
            }
        }
        return exists
    }

    override fun checkLoginCredentials(loggedUser : User): Boolean {
        var isValid = false
        runBlocking {
            launch(Dispatchers.IO) {
                val storedUser = dbManager.getUserByName(loggedUser.username)
                if (storedUser !=null) {
                    if (storedUser.password == loggedUser.password) {
                        isValid = true
                    }
                }
            }
        }
        return isValid
    }

    override fun deregisterUser(user : String) {
        runBlocking {
            launch(Dispatchers.IO) {
                dbManager.deleteUser(user)
            }
        }
    }

    override fun changePassword(username : String, newPassword: String) {
        runBlocking {
            launch(Dispatchers.IO) {
                dbManager.changePassword(username, newPassword)
            }
        }
    }

    override fun checkOldPassword(username: String, oldPassword : String): Boolean {
        var isValid = false
        runBlocking {
            launch(Dispatchers.IO) {
                val user = dbManager.getUserByName(username)!!
                if (user.password == oldPassword) {
                    isValid = true
                }
            }
        }
        return isValid
    }
}