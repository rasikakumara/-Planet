package com.rasika.planets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rasika.planets.model.Planet
import com.rasika.planets.databinding.PlanetRowBinding

class PlanetAdapter (private val onClickListener: OnClickListener) :
    PagingDataAdapter<Planet, PlanetAdapter.MyViewHolder>(PLANET_COMPARATOR) {

    inner class MyViewHolder(private val binding: PlanetRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(planet: Planet?) {
            println("List data name:"+planet?.name)
            println("List data climate:"+planet?.climate)
            binding.planetNameTextView.text = planet?.name
            binding.planetClimateTextView.text = planet?.climate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PlanetRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val planet = getItem(position)
        holder.bind(planet)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(planet!!)
        }
    }

    companion object {
        private val PLANET_COMPARATOR = object : DiffUtil.ItemCallback<Planet>() {
            override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
                return oldItem == newItem
            }
        }
    }

    class OnClickListener(val clickListener: (planet: Planet) -> Unit) {
        fun onClick(planet: Planet) = clickListener(planet)
    }
}