package com.sweng.sipscore5.services

import com.sweng.sipscore5.models.User

interface UserService {

    fun registerUser(user: User)
    fun checkUserExists(username: String): Boolean
    fun checkLoginCredentials(loggedUser: User): Boolean
    fun deregisterUser(user: String)
    fun changePassword(username : String, newPassword : String)
    fun checkOldPassword(username : String, oldPassword : String) : Boolean
}