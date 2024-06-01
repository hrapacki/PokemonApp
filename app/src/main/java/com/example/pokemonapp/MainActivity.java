package com.example.pokemonapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokemonapp.ApiService.PokemonApiService;
import com.example.pokemonapp.R;
import com.example.pokemonapp.model.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Creating Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonApiService apiService = retrofit.create(PokemonApiService.class);

        // Executing API request
        Call<Pokemon> call = apiService.getPokemon(1);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Pokemon pokemon = response.body();
                    if (pokemon != null) {
                        textView.setText(pokemon.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                textView.setText("Error: " + t.getMessage());
            }
        });
    }
}

