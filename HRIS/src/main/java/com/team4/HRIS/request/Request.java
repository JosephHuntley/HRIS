package com.team4.HRIS.request;

public class Request {
	private int id, empId, manId;
	private String reqDetails, respDetails, startDate, endDate, name, type;

	public Request() {
	}

	public Request(int empId, String type, String reqDetails, String startDate) {
		this.empId = empId;
		this.manId = manId;
		this.type	 = type;
		this.reqDetails = reqDetails;
		this.respDetails = respDetails;
		this.startDate = startDate;
	}

	public Request(int empId, int manId, String type, String reqDetails, String respDetails, String startDate,
				   String endDate, String name, int id) {
		this.empId = empId;
		this.manId = manId;
		this.type = type;
		this.reqDetails = reqDetails;
		this.respDetails = respDetails;
		this.startDate = startDate;
		this.endDate = endDate;
		this.name = name;
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getManId() {
		return manId;
	}

	public void setManId(int manId) {
		this.manId = manId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReqDetails() {
		return reqDetails;
	}

	public void setReqDetails(String reqDetails) {
		this.reqDetails = reqDetails;
	}

	public String getRespDetails() {
		return respDetails;
	}

	public void setRespDetails(String respDetails) {
		this.respDetails = respDetails;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Request{" +
				"empId=" + empId +
				", manId=" + manId +
				", type='" + type + '\'' +
				", reqDetails='" + reqDetails + '\'' +
				", respDetails='" + respDetails + '\'' +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", name='" + name + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}
