package com.sweng.sipscore5.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweng.sipscore5.R
import com.sweng.sipscore5.models.Drink
import com.sweng.sipscore5.adapter.DrinkAdapter
import com.sweng.sipscore5.services.DrinkService
import com.sweng.sipscore5.services.DrinkServiceImpl

class DrinksFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var drinkAdapter: DrinkAdapter
    private val drinkService : DrinkService = DrinkServiceImpl()
    private var drinkList : List<Drink> = emptyList<Drink>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drinks, container, false)
        drinkList = drinkService.getAllDrinks()
        drinkAdapter = DrinkAdapter(requireContext(), drinkList)

        searchEditText = view.findViewById(R.id.editTextSearch)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = drinkAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val filteredList = drinkList.filter {
                    it.name.contains(s.toString(), ignoreCase = true) ||
                            it.category.contains(s.toString(), ignoreCase = true)
                }
                drinkAdapter.updateList(filteredList)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

}