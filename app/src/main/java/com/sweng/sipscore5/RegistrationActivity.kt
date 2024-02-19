package com.sweng.sipscore5

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sweng.sipscore5.services.UserService

class RegistrationActivity  : AppCompatActivity() {
    private lateinit var uTextField: EditText
    private lateinit var pTextField: EditText
    private lateinit var pcTextField: EditText
    private lateinit var registerButton: Button

    private val dbManager = UserService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        uTextField = findViewById(R.id.usernameEditText)
        pTextField = findViewById(R.id.passwordEditText)
        pcTextField = findViewById(R.id.confirmPasswordEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = uTextField.text.toString()
            val password = pTextField.text.toString()
            val confirmedPassword = pcTextField.text.toString()

            if (dbManager.checkUserExists(username)) {
                Toast.makeText(this, "Benutzer existiert bereits", Toast.LENGTH_SHORT).show()
            } else if (password != confirmedPassword) {
                Toast.makeText(this, "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT).show()
            } else {
                dbManager.registerUser(username, password)
                Toast.makeText(this, "Registrierung erfolgreich", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        setContentView(R.layout.activity_main)
    }
}