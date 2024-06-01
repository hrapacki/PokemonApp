package com.example.pokemonapp.model;

public class Pokemon {
    private String name;
    private String image;
    public Pokemon(String name) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public String getImage(){
        return image;
    }

}
