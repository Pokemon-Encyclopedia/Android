package com.example.pokemonencyclopedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.apollographql.apollo3.ApolloClient
import com.example.graphql.PokemonListQuery
import com.example.pokemonencyclopedia.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ids = intent.getIntExtra("id", 0)
        val apolloClient = ApolloClient.Builder()
            .serverUrl("http://10.120.74.59:8081/graphql")
            .build()

        lifecycleScope.launch(Dispatchers.Main) {
            val res = apolloClient.query(PokemonListQuery()).execute()

            val list2 = res.data?.findAll
            val list = mutableListOf<PokemonListQuery.FindAll>()

            for (i in 0 until 151) {
                list.add(list2!![i]!!)
            }

            val adapter = PokemonAdapter(list as List<PokemonListQuery.FindAll>, this@MainActivity)
            binding.encyclopediaRecyclerView.adapter = adapter
            binding.encyclopediaRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)

            adapter.itemClick = object :PokemonAdapter.ItemClick {
                override fun onClick(view: View, data: PokemonListQuery.FindAll, position: Int) {
                    startActivity(Intent(this@MainActivity, PokemonInfoActivity::class.java)
                        .putExtra("dataId", data.id)
                        .putExtra("dataName", data.name)
                        .putExtra("dataImg", data.front_default)
                        .putStringArrayListExtra("dataTypes", data.types as ArrayList<String>)
                    )
                }
            }
        }
    }
}