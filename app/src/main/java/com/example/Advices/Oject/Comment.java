package com.example.Advices.Oject;

public class Comment {
    private String name;
    private String comment;
    private int idf;

    public Comment(String name, String comment, int idf) {
        this.name = name;
        this.comment = comment;
        this.idf = idf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }
}
