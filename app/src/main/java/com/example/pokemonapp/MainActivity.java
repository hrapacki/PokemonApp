package com.example.pokemonapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapp.ApiService.PokemonApiService;
import com.example.pokemonapp.model.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private List<Pokemon> pokemonList = new ArrayList<>();
    private static final int POKEMON_COUNT = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PokemonAdapter(pokemonList, this);
        recyclerView.setAdapter(adapter);

        // Creating Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonApiService apiService = retrofit.create(PokemonApiService.class);

        CountDownLatch latch = new CountDownLatch(POKEMON_COUNT);

        for (int id = 1; id <= POKEMON_COUNT; id++) {
            // Executing API request
            Call<Pokemon> call = apiService.getPokemon(id);
            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Pokemon pokemon = response.body();
                        synchronized (pokemonList) {
                            pokemonList.add(pokemon);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    }
                    latch.countDown();
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed to fetch data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    latch.countDown();
                }
            });
        }

        new Thread(() -> {
            try {
                latch.await();
                runOnUiThread(() -> {
                    // Sort the list by ID
                    pokemonList.sort((p1, p2) -> Integer.compare(p1.getId(), p2.getId()));
                    adapter.notifyDataSetChanged();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
