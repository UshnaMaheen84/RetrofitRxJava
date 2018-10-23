package com.example.admin.retrofitrxjava.models;

import java.util.List;

public class News {

    public String status ;
    public List<Source> sources  ;

    public News() {
    }

    public News(String status, List<Source> sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
