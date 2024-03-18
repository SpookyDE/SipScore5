package com.sweng.sipscore5.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sweng.sipscore5.R
import com.sweng.sipscore5.services.UserService
import com.sweng.sipscore5.services.UserServiceImpl

class AccountFragment : Fragment()  {
    private lateinit var uTextField: EditText
    private lateinit var npTextField: EditText
    private lateinit var opTextField: EditText
    private lateinit var registerButton: Button
    private val userService : UserService = UserServiceImpl()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        uTextField = view.findViewById(R.id.usernameEditText)
        opTextField = view.findViewById(R.id.oldPasswordEditText)
        npTextField = view.findViewById(R.id.newPasswordEditText)
        registerButton = view.findViewById(R.id.registerButton)
        val sharedPref = requireActivity().getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", null)!!
        uTextField.hint = username

        registerButton.setOnClickListener {
            val password = npTextField.text.toString()
            val newPassword = opTextField.text.toString()



            if (!userService.checkOldPassword(username, password)) {
                Toast.makeText(requireContext(), "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT).show()
            } else {
                userService.changePassword(username, newPassword)
                Toast.makeText(requireContext(), "Passwort erfolgreich geändert", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        }
        return view
    }
}