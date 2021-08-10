package com.jsb.android_jetpack_practice.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jsb.android_jetpack_practice.databinding.ItemBeerLayoutBinding
import com.jsb.android_jetpack_practice.model.entities.beers.Beers
import com.jsb.android_jetpack_practice.model.entities.beers.BeersItem

class BeerListAdapter(private val fragment: Fragment): RecyclerView.Adapter<BeerListAdapter.ViewHolder>()
{
    private var beers: List<BeersItem> = listOf()

    class ViewHolder(view: ItemBeerLayoutBinding): RecyclerView.ViewHolder(view.root){
        val ivDishImage = view.ivBeerImage
        val tvTitle = view.tvBeerTitle
        val tvTag = view.tvBeerTag
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemBeerLayoutBinding = ItemBeerLayoutBinding.inflate(
            LayoutInflater.from(fragment.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beers[position]

        holder.tvTitle.text = beer.name
        holder.tvTag.text = beer.tagline
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun beerList(list: List<BeersItem>) {
        beers = list
        notifyDataSetChanged()
    }
}