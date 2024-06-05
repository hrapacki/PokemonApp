package com.example.pokemonapp.model;

import java.util.Map;

public class Pokemon {
    private String name;
    private int id;
    private int weight;
    private String imageUrl;

    public Pokemon(String name, int id, int weight, String type, Map<String, String> sprites) {
        this.name = name;
        this.id = id;
        this.weight = weight;
        if (sprites != null) {
            this.imageUrl = sprites.get("front_default");
        }
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


    public String getImageUrl() {
        return imageUrl;
    }
}
