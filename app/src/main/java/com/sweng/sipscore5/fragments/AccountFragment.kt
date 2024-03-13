package com.sweng.sipscore5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sweng.sipscore5.R
import com.sweng.sipscore5.models.User
import com.sweng.sipscore5.services.UserService
import com.sweng.sipscore5.services.UserServiceImpl

class AccountFragment : Fragment()  {
    private lateinit var uTextField: EditText
    private lateinit var pTextField: EditText
    private lateinit var pcTextField: EditText
    private lateinit var registerButton: Button
    private val userService : UserService = UserServiceImpl()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        uTextField = view.findViewById(R.id.usernameEditText)
        pTextField = view.findViewById(R.id.passwordEditText)
        pcTextField = view.findViewById(R.id.confirmPasswordEditText)
        registerButton = view.findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = uTextField.text.toString()
            val password = pTextField.text.toString()
            val confirmedPassword = pcTextField.text.toString()
            val user = User(username, password)

            if (userService.checkUserExists(username)) {
                Toast.makeText(requireContext(), "Benutzer existiert bereits", Toast.LENGTH_SHORT).show()
            } else if (password != confirmedPassword) {
                Toast.makeText(requireContext(), "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT).show()
            } else {
                userService.registerUser(user)
                Toast.makeText(requireContext(), "Änderungen erfolgreich", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        }
        return view
    }
}