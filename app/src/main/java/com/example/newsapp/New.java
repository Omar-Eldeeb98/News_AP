package com.example.newsapp;

import java.io.Serializable;

public class New implements Serializable {

    private static final long id=1L;

    private String imageUrl ;
    private String title;
    private String source;
    private String date;
    private String description;
    private String webView;

    public New() {
    }

    public New(String imageUrl, String title, String source, String date ,String description, String webView ) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.source = source;
        this.date = date;
        this.description = description;
        this.webView = webView;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getWebView() {
        return webView;
    }
}
