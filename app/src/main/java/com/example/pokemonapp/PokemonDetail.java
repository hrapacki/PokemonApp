package com.example.pokemonapp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

public class PokemonDetail extends AppCompatActivity {
    private TextView nameTextView;
    private TextView idTextView;
    private TextView weightTextView;
    private ImageView pokemonImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        nameTextView = findViewById(R.id.pokemonName);
        idTextView = findViewById(R.id.pokemonId);
        weightTextView = findViewById(R.id.pokemonWeight);
        pokemonImageView = findViewById(R.id.pokemonImage); // Znajdź ImageView w widoku

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id", 0);
        int weight = intent.getIntExtra("weight", 0);
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png";

        nameTextView.setText("Name: " + name);
        idTextView.setText("Pokemon ID: " + id);
        weightTextView.setText("Weight: " + weight + "Kg");

        Glide.with(this)
                .load(imageUrl) // Wczytaj obrazek z URL
                .into(pokemonImageView); // Wyświetl obrazek w ImageView
    }
}
