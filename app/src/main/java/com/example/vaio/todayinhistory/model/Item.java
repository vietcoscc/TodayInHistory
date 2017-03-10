package com.example.vaio.todayinhistory.model;

/**
 * Created by vaio on 08/03/2017.
 */

public class Item {

    String id;
    String type;
    String info;
    String date;
    String day;
    String month;
    String year;


    public Item() {
    }

    public Item(String id, String type, String info, String date, String day, String month, String year) {
        this.id = id;
        this.type = type;
        this.info = info;
        this.date = date;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
