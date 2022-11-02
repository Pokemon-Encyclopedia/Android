package com.example.pokemonencyclopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import com.bumptech.glide.Glide
import com.example.graphql.FindPokemonByIdQuery
import com.example.pokemonencyclopedia.databinding.ActivityPokemonInfoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("dataId")
        val name = intent.getStringExtra("dataName")
        val img = intent.getStringExtra("dataImg")
        val type = intent.getStringExtra("dataTypes")

        val apolloClient = ApolloClient.Builder()
            .serverUrl("http://10.120.74.59:8081/graphql")
            .build()

        lifecycleScope.launch(Dispatchers.Main) {
            val resFront = apolloClient.query(FindPokemonByIdQuery("${id?.toInt()?.plus(1)}")).execute()
            val resBack = apolloClient.query(FindPokemonByIdQuery("${id?.toInt()?.minus(1)}")).execute()


        }

        Glide.with(this)
            .load(img)
            .into(binding.pokemonImg)
        binding.pokemonName.text = setId(id, name)
        binding.pokemonType.text = type
    }

    private fun setId(id: String?, name: String?): String {
        return if (id?.toInt()!! < 10) {
            "00$id  $name"
        } else if (id.toInt() < 100) {
            "0$id  $name"
        } else "$id  $name"
    }
}
