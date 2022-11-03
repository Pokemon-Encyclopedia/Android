package com.example.pokemonencyclopedia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonencyclopedia.databinding.FirstActivityBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: FirstActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kantoMedal.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java)
                .putExtra("id", 151))
        }

        binding.saintMedal.setOnClickListener {
            // 251
        }
    }
}