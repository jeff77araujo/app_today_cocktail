package com.app.todayscocktail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.todayscocktail.R
import com.app.todayscocktail.entity.Cocktail
import com.bumptech.glide.Glide

class CocktailsListAdapter: RecyclerView.Adapter<CocktailsListAdapter.CocktailsListViewHolder>() {

    var data = listOf<Cocktail>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsListAdapter.CocktailsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cocktails_layout, parent, false) as View
        return CocktailsListViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CocktailsListAdapter.CocktailsListViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item.name, item.thumbUrl)
    }

    inner class CocktailsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private val itemTitle = itemView.findViewById<TextView>(R.id.drinkName)
        private val itemImage = itemView.findViewById<ImageView>(R.id.drinkImage)

        fun bind(title: String, url: String) {
            itemTitle.text = title
            val url: String = url

            if(url.isNotEmpty()) {
                Glide.with(itemView.context)
                        .load(url)
                        .placeholder(R.drawable.ic_drink)
                        .error(R.drawable.ic_drink)
                        .into(itemImage)
            } else {
                Glide.with(itemView.context).clear(itemView)
                itemImage.setImageResource(R.drawable.ic_drink)
            }
        }
    }

}