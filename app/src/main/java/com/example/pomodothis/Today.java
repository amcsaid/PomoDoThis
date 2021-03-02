package com.example.pomodothis;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Today {
    private String date;
    private int pomos;
    private int breaks;

    public Today(String date, int pomos, int breaks) {
        this.date = date;
        this.pomos = pomos;
        this.breaks = breaks;
    }

    public Today() {
        // Getting the date and formatting it
        Format f = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        this.date = f.format(now);
        this.pomos = 0;
        this.breaks = 0;
    }

    public void incrementPomos() {
        this.pomos++;
    }

    public void incrementBreaks() {
        this.breaks++;
    }

    // is Equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Today today = (Today) o;
        return getDate().equals(today.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate());
    }

    // Getters and Setter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPomos() {
        return pomos;
    }

    public void setPomos(int pomos) {
        this.pomos = pomos;
    }

    public int getBreaks() {
        return breaks;
    }

    public void setBreaks(int breaks) {
        this.breaks = breaks;
    }

    @Override
    public String toString() {
        return "Today{" +
                "date='" + date + '\'' +
                ", pomos=" + pomos +
                ", breaks=" + breaks +
                '}';
    }
}
