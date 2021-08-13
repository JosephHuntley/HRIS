package com.team4.HRIS.payroll;

public class Payroll {
    private int id, payrate, netpay, hours, hPlan, vPlan, dPlan;
    private String name;
    private boolean fullTime, hourly;

    public Payroll() {
    }

    public Payroll(int payrate, int netpay, int hPlan, int vPlan, int dPlan, String name, boolean fullTime, boolean hourly) {
        this.payrate = payrate;
        this.netpay = netpay;
        this.hPlan = hPlan;
        this.vPlan = vPlan;
        this.dPlan = dPlan;
        this.name = name;
        this.fullTime = fullTime;
        this.hourly = hourly;
    }

    public Payroll(int id, int payrate, int netpay, int hPlan, int vPlan, int dPlan, String name, boolean fullTime, boolean hourly) {
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

    public int getPayrate() {
        return payrate;
    }

    public void setPayrate(int payrate) {
        this.payrate = payrate;
    }

    public int getNetpay() {
        return netpay;
    }
    public void setNetpay(){
        this.netpay = this.payrate * this.hours - this.dPlan - this.vPlan - this.hPlan;
    }

    public int gethPlan() {
        return hPlan;
    }

    public void sethPlan(int hPlan) {
        this.hPlan = hPlan;
    }

    public int getvPlan() {
        return vPlan;
    }

    public void setvPlan(int vPlan) {
        this.vPlan = vPlan;
    }

    public int getdPlan() {
        return dPlan;
    }

    public void setdPlan(int dPlan) {
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
