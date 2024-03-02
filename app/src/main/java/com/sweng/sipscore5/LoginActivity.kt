package com.sweng.sipscore5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sweng.sipscore5.models.User
import com.sweng.sipscore5.services.UserService
import com.sweng.sipscore5.services.UserServiceImpl

class LoginActivity : AppCompatActivity() {
    private lateinit var uTextField: EditText
    private lateinit var pTextField: EditText
    private lateinit var loginBtn: Button
    private lateinit var user : User
    private val userService : UserService = UserServiceImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        uTextField = findViewById(R.id.usernameEditText)
        pTextField = findViewById(R.id.passwordEditText)
        loginBtn = findViewById(R.id.loginButton)

        loginBtn.setOnClickListener {
            val username = uTextField.text.toString()
            val password = pTextField.text.toString()
            user = User(username, password)

            if (!userService.checkLoginCredentials(user)) {
                Toast.makeText(this, "Benutzername oder Passwort falsch.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Anmelden erfolgreich", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AppActivity::class.java)
                val sharedPref = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("username", user.username)
                editor.apply()
                startActivity(intent)
                finish()
            }
        }
    }
}