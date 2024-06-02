package com.example.pokemonapp.model;

import java.util.List;

public class Pokemon {
    private String name;
    private String image;
    private int id;
    private int weight;


    public Pokemon(String name, int id, int weight ) {
        this.name = name;
        this.id = id;
        this.weight = weight;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

}
