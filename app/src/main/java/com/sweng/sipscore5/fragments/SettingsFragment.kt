package com.sweng.sipscore5.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.sweng.sipscore5.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val sharedPref = requireActivity().getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", null)
        val loggedInAsText = view.findViewById<TextView>(R.id.text_logged_in_as)
        loggedInAsText.text = "Eingeloggt als ${username}"

        val editProfileButton = view.findViewById<Button>(R.id.button_edit_profile)
        editProfileButton.setOnClickListener {
                findNavController().navigate(R.id.accountFragment)
        }

        val logoutButton = view.findViewById<Button>(R.id.button_logout)
        logoutButton.setOnClickListener {
            requireActivity().finish()
        }

        return view
    }
}