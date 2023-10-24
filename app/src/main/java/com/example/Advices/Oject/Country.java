package com.example.Advices.Oject;

public class Country {
    private int idcountry;
    private String nameCountry;
    private String image;

    public Country(int idcountry, String nameCountry, String image) {
        this.idcountry = idcountry;
        this.nameCountry = nameCountry;
        this.setImage(image);
    }

    public int getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(int idcountry) {
        this.idcountry = idcountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
