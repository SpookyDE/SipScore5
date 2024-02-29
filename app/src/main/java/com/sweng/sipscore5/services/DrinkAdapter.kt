package com.sweng.sipscore5.services

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sweng.sipscore5.R
import com.sweng.sipscore5.models.Drink

class DrinkAdapter(private val context: Context, private var drinkList: List<Drink>) :
    RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    fun updateList(newList: List<Drink>) {
        drinkList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.drink_item, parent, false)
        return DrinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = drinkList[position]
        holder.bind(drink)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val buttonRate: Button = itemView.findViewById(R.id.buttonRate)

        fun bind(drink: Drink) {
            nameTextView.text = drink.name
            buttonRate.setOnClickListener {
                Toast.makeText(context, "Bewerten geklickt für ${drink.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}