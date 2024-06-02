package com.example.pokemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PokemonDetail extends AppCompatActivity {
    private TextView nameTextView;
    private TextView idTextView;
    private TextView weightTextView;
    private TextView typeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        nameTextView = findViewById(R.id.pokemonName);
        idTextView = findViewById(R.id.pokemonId);
        weightTextView = findViewById(R.id.pokemonWeight);
        typeTextView = findViewById(R.id.pokemonType);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id", 0);
        int weight = intent.getIntExtra("weight", 0);
        String type = intent.getStringExtra("type");

        nameTextView.setText("Name: " + name);
        idTextView.setText("Pokemon ID: " + id);
        weightTextView.setText("Weight: " + weight + "Kg");
        typeTextView.setText("Type: " + type);
    }
}
