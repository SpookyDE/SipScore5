package com.sweng.sipscore5.services

interface AuthenticationService {

    fun registerUser(username: String, password: String)

    fun checkLoginCredentials(username: String, password: String) : Boolean
}