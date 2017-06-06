package com.patkayongo.gazette.domain;

public class Gazette {
    private final String title;
    private final String text;
    private final String sourceUrl;

    public Gazette(String title, String text, String sourceUrl) {
        this.title = title;
        this.text = text;
        this.sourceUrl = sourceUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }
}
