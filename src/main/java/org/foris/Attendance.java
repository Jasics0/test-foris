package org.foris;

public class Attendance {
    private int minutes;
    private int days;
    private int lastDay;

    public Attendance() {
        this.minutes = 0;
        this.days = 0;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getDays() {
        return days;
    }

    public int getLastDay() {
        return lastDay;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }
}
