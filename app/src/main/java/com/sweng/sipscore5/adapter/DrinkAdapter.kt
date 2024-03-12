package com.sweng.sipscore5.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sweng.sipscore5.R
import com.sweng.sipscore5.models.Drink
import com.sweng.sipscore5.services.DrinkService
import com.sweng.sipscore5.services.DrinkServiceImpl
import com.sweng.sipscore5.services.RatingService
import com.sweng.sipscore5.services.RatingServiceImpl

class DrinkAdapter(private val context: Context, private var drinkList: List<Drink>, private val user : String) :
    RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {
    private val ratingService : RatingService = RatingServiceImpl()
    private val drinkService : DrinkService = DrinkServiceImpl()

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
                showRatingDialog(drink)
            }
        }


        private fun showRatingDialog(drink: Drink) {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.rating_dialog, null)
            val alertDialogBuilder = AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle("Bewerten Sie ${drink.name}")

            val ratingSeekBar = dialogView.findViewById<SeekBar>(R.id.seekBarRating)
            val ratingTextView = dialogView.findViewById<TextView>(R.id.textViewRating)

            ratingSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    ratingTextView.text = (progress + 1).toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            alertDialogBuilder.setPositiveButton("Bewerten") { dialog, _ ->
                val rating = ratingSeekBar.progress + 1
                Toast.makeText(context, "Bewertung für ${drink.name}: $rating", Toast.LENGTH_SHORT).show()
                if (ratingService.checkIfRatingExists(user, drink.id)) {
                    ratingService.updateRating(user, drink.id, rating)
                } else {
                    ratingService.insertRating(user, drink.id, rating)
                }
                val newRating = ratingService.calculateRating(drink.id)!!
                drinkService.updateRating(drink.id, newRating)
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton("Abbrechen") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}