package com.example.admin.retrofitrxjava.models;

public class Icon {

    private String url;
    private int width,height,bytes;

    public Icon() {
    }

    public Icon(String url, int width, int height, int bytes) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.bytes = bytes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }
}
