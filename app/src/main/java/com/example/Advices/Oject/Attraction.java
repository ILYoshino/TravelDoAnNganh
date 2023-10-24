package com.example.Advices.Oject;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class Attraction {
    private int id;
    private String name;
    private String address;
    private String detail;
    private String pic;
    private String Lat;
    private String Long;
    private int idf;

    public Attraction(int id, String name, String address, String pic, String detail, String lat, String aLong, int idf) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.detail = detail;
        this.pic = pic;
        this.Lat = lat;
        this.Long = aLong;
        this.idf = idf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
