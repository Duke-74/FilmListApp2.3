package com.example.filmlistapp2;

public class Film {
    private String name, description, poster, id;

    public Film(String name, String description, String poster, String id) {
        this.name = name;
        this.description = description;
        this.poster = poster;
        this.id = id;
    }

    public Film(){
        this.name = "";
        this.description = "";
        this.poster = "";
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
