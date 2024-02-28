package com.sweng.sipscore5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sweng.sipscore5.R
import com.sweng.sipscore5.services.DrinkService
import com.sweng.sipscore5.services.DrinkServiceImpl

class DrinksFragment : Fragment() {

    private lateinit var textView: TextView
    private val drinkService : DrinkService = DrinkServiceImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drinks, container, false)
        val exampleText = drinkService.getFirstDrinkName()
        textView = view.findViewById(R.id.textView)
        textView.text = exampleText
        return view
    }
}