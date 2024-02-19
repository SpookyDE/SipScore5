package com.sweng.sipscore5

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.sweng.sipscore5.services.DatabaseService

class RegistrationActivity  : AppCompatActivity() {
    private lateinit var uTextField: EditText
    private lateinit var pTextField: EditText
    private lateinit var registerButton: Button
    private val dbManager = DatabaseService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        uTextField = findViewById(R.id.usernameEditText)
        pTextField = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            val username = uTextField.text.toString()
            val password = pTextField.text.toString()
            dbManager.registerUser(username, password)
            finish()

        }
    }

    override fun onStop() {
        super.onStop()
        setContentView(R.layout.activity_main)
    }
}