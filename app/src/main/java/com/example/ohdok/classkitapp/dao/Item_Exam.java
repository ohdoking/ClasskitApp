package com.example.ohdok.classkitapp.dao;

public class Item_Exam {

    private String name;
    private int size;
    private int year;
    private String[] MM;

    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public int getYear() {
        return year;
    }
    public String[] getMonth() {
        return MM;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setMonth(String[] MM) {
        this.MM = MM;
    }
}