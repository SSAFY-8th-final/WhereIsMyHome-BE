package com.mycom.myhouse.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseDto {
	private int no;
	private String dong;
	private String AptName;
	private String gugun;
	private String code;	// 구군 코드
	private String dealAmount;	// 거래금액, String임!!!!!
	private String buildYear;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private String area;	// 면적, String타입
	private String floor;		// -1 : 층수 X
	private String jibun;	// 지번
	private int house_no;	// null인 경우 존재함
	
	private Double lat;	// 위도
	private Double lng;	// 경도
	private String img;
	
	
}
