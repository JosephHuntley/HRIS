package com.team4.HRIS.payroll;
// Author - Joseph Huntley
// Team 4
public class Paystub {
    private int id;
    private String name;
    private boolean fullTime, hourly;
    private double payrate, netpay, hours, hPlan, vPlan, dPlan;

    public Paystub() {
    }

    public Paystub(boolean fullTime, boolean hourly, double payrate) {
        this.fullTime = fullTime;
        this.hourly = hourly;
        this.payrate = payrate;
    }

    public Paystub(int id, String name, boolean fullTime, boolean hourly, double payrate, double netpay, double hours, double hPlan, double vPlan, double dPlan) {
        this.id = id;
        this.name = name;
        this.fullTime = fullTime;
        this.hourly = hourly;
        this.payrate = payrate;
        this.netpay = netpay;
        this.hours = hours;
        this.hPlan = hPlan;
        this.vPlan = vPlan;
        this.dPlan = dPlan;
    }

    public Paystub(String name, boolean fullTime, boolean hourly, double payrate, double netpay, double hours, double hPlan, double vPlan, double dPlan) {
        this.name = name;
        this.fullTime = fullTime;
        this.hourly = hourly;
        this.payrate = payrate;
        this.netpay = netpay;
        this.hours = hours;
        this.hPlan = hPlan;
        this.vPlan = vPlan;
        this.dPlan = dPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPayrate() {
        return payrate;
    }

    public void setPayrate(double payrate) {
        this.payrate = payrate;
    }

    public double getNetpay() {
        return netpay;
    }
    public void setNetpay(){
        this.netpay = this.payrate * this.hours - this.dPlan - this.vPlan - this.hPlan;
    }

    public double gethPlan() {
        return hPlan;
    }

    public void sethPlan(double hPlan) {
        this.hPlan = hPlan;
    }

    public double getvPlan() {
        return vPlan;
    }

    public void setvPlan(double vPlan) {
        this.vPlan = vPlan;
    }

    public double getdPlan() {
        return dPlan;
    }

    public void setdPlan(double dPlan) {
        this.dPlan = dPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public boolean isHourly() {
        return hourly;
    }

    public void setHourly(boolean hourly) {
        this.hourly = hourly;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Paystub{" +
                "id=" + id +
                ", payrate=" + payrate +
                ", netpay=" + netpay +
                ", hours=" + hours +
                ", hPlan=" + hPlan +
                ", vPlan=" + vPlan +
                ", dPlan=" + dPlan +
                ", name='" + name + '\'' +
                ", fullTime=" + fullTime +
                ", hourly=" + hourly +
                '}';
    }
}
