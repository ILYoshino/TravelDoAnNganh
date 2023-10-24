package com.example.Advices.Oject;

public class City {
    private int idCity;
    private int idf;
    private String nameCity;
    private String state;
    private String img;

    public City(int idCity, String nameCity, String img, String state, int idf) {
        this.idCity = idCity;
        this.setIdf(idf);
        this.nameCity = nameCity;
        this.state = state;
        this.img = img;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }
}
