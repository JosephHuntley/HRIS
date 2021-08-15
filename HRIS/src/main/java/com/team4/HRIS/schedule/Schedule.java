package com.team4.HRIS.schedule;
// Author - Joseph Huntley
// Team 4
public class Schedule {
    String monStart, monEnd, tueStart, tueEnd, wedStart, wedEnd, thurStart, thurEnd, friStart, friEnd, satStart, satEnd,
            sunStart, sunEnd;
    // This is meant for the employee id the schedule belongs to
    int id;

    public Schedule() {
    }

    public Schedule(String monStart, String monEnd, String tueStart, String tueEnd, String wenStart, String wenEnd,
                    String thurStart, String thurEnd, String friStart, String friEnd, String satStart, String satEnd,
                    String sunStart, String sunEnd, int id) {
        this.monStart = monStart;
        this.monEnd = monEnd;
        this.tueStart = tueStart;
        this.tueEnd = tueEnd;
        this.wedStart = wenStart;
        this.wedEnd = wenEnd;
        this.thurStart = thurStart;
        this.thurEnd = thurEnd;
        this.friStart = friStart;
        this.friEnd = friEnd;
        this.satStart = satStart;
        this.satEnd = satEnd;
        this.sunStart = sunStart;
        this.sunEnd = sunEnd;
        this.id = id;
    }

    public Schedule(String monStart, String monEnd, String tueStart, String tueEnd, String wenStart, String wenEnd,
                    String thurStart, String thurEnd, String friStart, String friEnd, String satStart, String satEnd,
                    String sunStart, String sunEnd) {
        this.monStart = monStart;
        this.monEnd = monEnd;
        this.tueStart = tueStart;
        this.tueEnd = tueEnd;
        this.wedStart = wenStart;
        this.wedEnd = wenEnd;
        this.thurStart = thurStart;
        this.thurEnd = thurEnd;
        this.friStart = friStart;
        this.friEnd = friEnd;
        this.satStart = satStart;
        this.satEnd = satEnd;
        this.sunStart = sunStart;
        this.sunEnd = sunEnd;
    }

    public String getMonStart() {
        return monStart;
    }

    public void setMonStart(String monStart) {
        this.monStart = monStart;
    }

    public String getMonEnd() {
        return monEnd;
    }

    public void setMonEnd(String monEnd) {
        this.monEnd = monEnd;
    }

    public String getTueStart() {
        return tueStart;
    }

    public void setTueStart(String tueStart) {
        this.tueStart = tueStart;
    }

    public String getTueEnd() {
        return tueEnd;
    }

    public void setTueEnd(String tueEnd) {
        this.tueEnd = tueEnd;
    }

    public String getWedStart() {
        return wedStart;
    }

    public void setWedStart(String wedStart) {
        this.wedStart = wedStart;
    }

    public String getWedEnd() {
        return wedEnd;
    }

    public void setWedEnd(String wedEnd) {
        this.wedEnd = wedEnd;
    }

    public String getThurStart() {
        return thurStart;
    }

    public void setThurStart(String thurStart) {
        this.thurStart = thurStart;
    }

    public String getThurEnd() {
        return thurEnd;
    }

    public void setThurEnd(String thurEnd) {
        this.thurEnd = thurEnd;
    }

    public String getFriStart() {
        return friStart;
    }

    public void setFriStart(String friStart) {
        this.friStart = friStart;
    }

    public String getFriEnd() {
        return friEnd;
    }

    public void setFriEnd(String friEnd) {
        this.friEnd = friEnd;
    }

    public String getSatStart() {
        return satStart;
    }

    public void setSatStart(String satStart) {
        this.satStart = satStart;
    }

    public String getSatEnd() {
        return satEnd;
    }

    public void setSatEnd(String satEnd) {
        this.satEnd = satEnd;
    }

    public String getSunStart() {
        return sunStart;
    }

    public void setSunStart(String sunStart) {
        this.sunStart = sunStart;
    }

    public String getSunEnd() {
        return sunEnd;
    }

    public void setSunEnd(String sunEnd) {
        this.sunEnd = sunEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "monStart='" + monStart + '\'' +
                ", monEnd='" + monEnd + '\'' +
                ", tueStart='" + tueStart + '\'' +
                ", tueEnd='" + tueEnd + '\'' +
                ", wenStart='" + wedStart + '\'' +
                ", wenEnd='" + wedEnd + '\'' +
                ", thurStart='" + thurStart + '\'' +
                ", thurEnd='" + thurEnd + '\'' +
                ", friStart='" + friStart + '\'' +
                ", friEnd='" + friEnd + '\'' +
                ", satStart='" + satStart + '\'' +
                ", satEnd='" + satEnd + '\'' +
                ", sunStart='" + sunStart + '\'' +
                ", sunEnd='" + sunEnd + '\'' +
                ", id=" + id +
                '}';
    }
}
