package com.example.pokemonencyclopedia

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graphql.PokemonListQuery
import com.example.pokemonencyclopedia.databinding.PokemonItemBinding

class PokemonAdapter(private val list: List<PokemonListQuery.FindAll>, private val context: Context): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PokemonItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: PokemonItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(findAll: PokemonListQuery.FindAll) {
            Glide
                .with(context)
                .load(findAll.front_default)
                .into(binding.image)

            binding.name.text = findAll.name
        }
    }
}