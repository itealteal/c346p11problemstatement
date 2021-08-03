package com.example.c346p09problemstatement;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id,String title,String singers,int year,int stars){
        this.id=id;
        this.title=title;
        this.singers=singers;
        this.year=year;
        this.stars=stars;
    }

    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getSingers(){return singers;}
    public int getYear(){return year;}
    public int getStars(){return stars;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        String description = title+"\n"+singers+" - "+year+"\n";
        for(int i =0;i<stars;i++){
            description+= "★";
        }
        return description;
    }

    public  String StarsToString(){
        switch (stars) {
            case 1: return "★";

            case 2: return "★★";

            case 3: return "★★★";

            case 4: return "★★★★";

            case 5: return "★★★★★";

            default: return "";
        }
    }
}
