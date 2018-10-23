package com.example.admin.retrofitrxjava.models;

public class Source {
    public String id ;
    public String name,description, category, url ;

    public Source() {
    }

    public Source(String id, String name, String description, String category, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
