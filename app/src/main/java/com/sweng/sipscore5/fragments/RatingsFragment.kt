package com.sweng.sipscore5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweng.sipscore5.R
import com.sweng.sipscore5.adapter.RatingsAdapter
import com.sweng.sipscore5.models.Drink
import com.sweng.sipscore5.services.DrinkService
import com.sweng.sipscore5.services.DrinkServiceImpl

class RatingsFragment : Fragment() {

    private lateinit var categorySpinner: Spinner
    private lateinit var topRecyclerView: RecyclerView
    private lateinit var flopRecyclerView: RecyclerView
    private val drinkService : DrinkService = DrinkServiceImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ratings, container, false)

        categorySpinner = view.findViewById(R.id.category_spinner)
        topRecyclerView = view.findViewById(R.id.topRecyclerView)
        flopRecyclerView = view.findViewById(R.id.flopRecyclerView)

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
                when (parent.getItemAtPosition(position).toString()) {
                    "Waters" -> {
                        showDrinks(topRecyclerView, drinkService.getTopList(drinkService.getWaters()))
                        showDrinks(flopRecyclerView, drinkService.getFlopList(drinkService.getWaters()))
                    }
                    "Beers" -> {
                        showDrinks(topRecyclerView, drinkService.getTopList(drinkService.getBeers()))
                        showDrinks(flopRecyclerView, drinkService.getFlopList(drinkService.getBeers()))
                    }
                    "Energy Drinks" -> {
                        showDrinks(topRecyclerView, drinkService.getTopList(drinkService.getEnergyDrinks()))
                        showDrinks(flopRecyclerView, drinkService.getFlopList(drinkService.getEnergyDrinks()))
                    }
                    "Softdrinks" -> {
                        showDrinks(topRecyclerView, drinkService.getTopList(drinkService.getSoftdrinks()))
                        showDrinks(flopRecyclerView, drinkService.getFlopList(drinkService.getSoftdrinks()))
                    }
                    "Spirits" -> {
                        showDrinks(topRecyclerView, drinkService.getTopList(drinkService.getSpirits()))
                        showDrinks(flopRecyclerView, drinkService.getFlopList(drinkService.getSpirits()))
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        return view
    }

    private fun showDrinks(recyclerView: RecyclerView, drinks: List<Drink>) {
        val adapter = RatingsAdapter(drinks)
        recyclerView.adapter = adapter
    }
}