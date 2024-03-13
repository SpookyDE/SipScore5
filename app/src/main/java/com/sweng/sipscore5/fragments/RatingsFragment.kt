package com.sweng.sipscore5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.sweng.sipscore5.R
import com.sweng.sipscore5.adapter.RatingsAdapter
import com.sweng.sipscore5.models.Drink
import com.sweng.sipscore5.services.DrinkService
import com.sweng.sipscore5.services.DrinkServiceImpl

class RatingsFragment : Fragment() {

    private lateinit var categorySpinner: Spinner
    private lateinit var topRecyclerView: RecyclerView
    private lateinit var flopRecyclerView: RecyclerView
    private lateinit var toggleButton: ToggleButton
    private val drinkService : DrinkService = DrinkServiceImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ratings, container, false)

        categorySpinner = view.findViewById(R.id.category_spinner)
        topRecyclerView = view.findViewById(R.id.topRecyclerView)
        flopRecyclerView = view.findViewById(R.id.flopRecyclerView)
        toggleButton = view.findViewById(R.id.toggle_button)

        topRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        flopRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.drink_categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCategory = parent.getItemAtPosition(position).toString()
                val topDrinks = drinkService.getTopList(getDrinkList(selectedCategory))
                val flopDrinks = drinkService.getFlopList(getDrinkList(selectedCategory))

                showDrinks(topRecyclerView, topDrinks)
                showDrinks(flopRecyclerView, flopDrinks)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                topRecyclerView.visibility = View.VISIBLE
                flopRecyclerView.visibility = View.GONE
            } else {
                topRecyclerView.visibility = View.GONE
                flopRecyclerView.visibility = View.VISIBLE
            }
        }

        return view
    }

    private fun showDrinks(recyclerView: RecyclerView, drinks: List<Drink>) {
        val adapter = RatingsAdapter(drinks)
        recyclerView.adapter = adapter
    }

    private fun getDrinkList(category: String): List<Drink> {
        return when (category) {
            "Wasser" -> drinkService.getWaters()
            "Biere" -> drinkService.getBeers()
            "Energydrinks" -> drinkService.getEnergyDrinks()
            "Softdrinks" -> drinkService.getSoftdrinks()
            "Spirituosen" -> drinkService.getSpirits()
            else -> emptyList()
        }
    }
}