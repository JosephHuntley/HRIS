package com.team4.HRIS.payroll;

public class Payroll {
    private int id;
    private String name;
    private boolean fullTime, hourly;
    private double payrate, netpay, hours, hPlan, vPlan, dPlan;

    public Payroll() {
    }

    public Payroll(boolean fullTime, boolean hourly, double payrate) {
        this.fullTime = fullTime;
        this.hourly = hourly;
        this.payrate = payrate;
    }

    public Payroll(double payrate, double netpay, double hPlan, double vPlan, double dPlan, String name, boolean fullTime, boolean hourly) {
        this.payrate = payrate;
        this.netpay = netpay;
        this.hPlan = hPlan;
        this.vPlan = vPlan;
        this.dPlan = dPlan;
        this.name = name;
        this.fullTime = fullTime;
        this.hourly = hourly;
    }

    public Payroll(int id, double payrate, double netpay, double hPlan, double vPlan, double dPlan, String name, boolean fullTime, boolean hourly) {
        this.id = id;
        this.payrate = payrate;
        this.netpay = netpay;
        this.hPlan = hPlan;
        this.vPlan = vPlan;
        this.dPlan = dPlan;
        this.name = name;
        this.fullTime = fullTime;
        this.hourly = hourly;
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
