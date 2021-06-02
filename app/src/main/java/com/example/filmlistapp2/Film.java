package com.example.filmlistapp2;

public class Film {
    private String name, description, poster;

    public Film(String name, String description, String poster) {
        this.name = name;
        this.description = description;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
