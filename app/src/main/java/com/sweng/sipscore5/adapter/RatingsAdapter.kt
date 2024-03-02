package com.sweng.sipscore5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sweng.sipscore5.R
import com.sweng.sipscore5.models.Drink

class RatingsAdapter(private val drinkList: List<Drink>) : RecyclerView.Adapter<RatingsAdapter.RatingsDrinkItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingsDrinkItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rating_drink_item, parent, false)
        return RatingsDrinkItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RatingsDrinkItemViewHolder, position: Int) {
        val drink = drinkList[position]
        holder.bind(drink)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    inner class RatingsDrinkItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        fun bind(drink: Drink) {
            textViewName.text = drink.name
        }
    }
}
