package com.example.miklosnagy.navigationdrawer

import API.Place
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
 class PlacesAdapterAdapter(
        private var restaurantList: Array<Place>
) : RecyclerView.Adapter<PlacesAdapterAdapter.RestaurantViewHolder>() {

    public fun addNewStatutes(statuses: List<Place>) {
        for (item in statuses) {
            restaurantList += item
            notifyDataSetChanged()
        }
    }

    class RestaurantViewHolder(val placesView: View)
        : RecyclerView.ViewHolder(placesView){

        val cardView=placesView.findViewById(R.id.card) as CardView
        val textView=cardView.findViewById(R.id.placeName) as TextView



    }


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int): PlacesAdapterAdapter.RestaurantViewHolder {

        val restaurantView = LayoutInflater.from(parent.context)
                .inflate(R.layout.placesview, parent, false) as View



        return RestaurantViewHolder(restaurantView)
    }

    override fun onBindViewHolder(
            holder: RestaurantViewHolder,
            position: Int) {
        holder.textView.text= restaurantList[position].name
        holder.cardView.setOnClickListener({
            val intent:Intent= Intent(it.context,NavigationActivity::class.java);
            intent.putExtra("startmap","tru");
            it.context.startActivity(intent)
        })

    }

    // invoked by the layout manager
    override fun getItemCount() = restaurantList.size


}