package com.example.pokemonapp.ApiService;

import com.example.pokemonapp.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApiService {
    @GET("pokemon/{id}/")
    Call<Pokemon> getPokemon(@Path("id") int id);
}
