package com.mycom.myhouse.proxy.parking;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mycom.myhouse.map.dto.ParkingLotDto;

public class ParkingSAXHandler extends DefaultHandler{
	private List<ParkingLotDto> parkingList = new ArrayList<>();
	
	private String temp;
	private ParkingLotDto dto;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("row")) {
			dto = new ParkingLotDto();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("PARKING_NAME") ) {
			dto.setParkingName(temp.trim());
		}
		else if(qName.equals("ADDR")) {	// 주소
			dto.setAddr(temp.trim());
		}else if(qName.equals("PARKING_TYPE_NM")) {
			dto.setParkingTypeNm(temp.trim());
		}else if(qName.equals("OPERATION_RULE_NM")) {
			dto.setOperationRuleNm(temp.trim());
		}else if(qName.equals("CAPACITY")) {
			dto.setCapacity(temp.trim());
		}else if(qName.equals("PAY_NM")) {
			dto.setPayName(temp.trim());
		}else if(qName.equals("RATES")) {
			dto.setRates(temp.trim());
		}else if(qName.equals("TIME_RATE")) {
			dto.setTimeRate(temp.trim());
		}else if(qName.equals("LAT")) {
			dto.setLat(temp.trim());
		}else if(qName.equals("LNG")) {
			dto.setLng(temp.trim());
		}else if(qName.equals("row")) {
			if(dto.getLat().equals("0") || dto.getLng().equals("0")) return;
			parkingList.add(dto);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		temp = new String(ch,start,length);
	}

	public List<ParkingLotDto> getParkingList() {
		return parkingList;
	}
	
	
	
}
