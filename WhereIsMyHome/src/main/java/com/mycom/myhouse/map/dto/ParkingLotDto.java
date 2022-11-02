package com.mycom.myhouse.map.dto;

public class ParkingLotDto {
	String parkingName;
	String addr;
	String parkingTypeNm;
	String operationRuleNm;	// 운영구분
	String capacity;	// 총 주차면
	String payName;	// 유료 or 무료
	String rates;	// 기본 주차요금
	String timeRate; // 기본 주차시간(분)
	String lat;
	String lng;
	
	public String getParkingName() {
		return parkingName;
	}
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getParkingTypeNm() {
		return parkingTypeNm;
	}
	public void setParkingTypeNm(String parkingTypeNm) {
		this.parkingTypeNm = parkingTypeNm;
	}
	public String getOperationRuleNm() {
		return operationRuleNm;
	}
	public void setOperationRuleNm(String operationRuleNm) {
		this.operationRuleNm = operationRuleNm;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getRates() {
		return rates;
	}
	public void setRates(String rates) {
		this.rates = rates;
	}
	public String getTimeRate() {
		return timeRate;
	}
	public void setTimeRate(String timeRate) {
		this.timeRate = timeRate;
	}
	
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "ParkingLotDto [parkingName=" + parkingName + ", addr=" + addr + ", parkingTypeNm=" + parkingTypeNm
				+ ", operationRuleNm=" + operationRuleNm + ", capacity=" + capacity + ", payName=" + payName
				+ ", rates=" + rates + ", timeRate=" + timeRate + ", lat=" + lat + ", lng=" + lng + "]";
	}

	
}
