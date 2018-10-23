package com.example.admin.retrofitrxjava.models;

import java.util.List;

public class headlineModel {

    public String status ;
    public List<Article> articles  ;

    public headlineModel() {
    }

    public headlineModel(String status, List<Article> articles) {
        this.status = status;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
