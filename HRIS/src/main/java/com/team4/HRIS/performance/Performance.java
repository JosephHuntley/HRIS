package com.team4.HRIS.performance;

public class Performance {
    private int empId, rating;
    private String remarks;

    public Performance() {
    }

    public Performance(int empId, int rating, String remarks) {
        this.empId = empId;
        this.rating = rating;
        this.remarks = remarks;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "empId=" + empId +
                ", rating=" + rating +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
