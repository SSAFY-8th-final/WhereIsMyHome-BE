package com.mycom.myhouse.sale.dto;

import java.util.Date;

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
public class SaleDto {
	private int no;
	private String dongCode;
	private String addressDetail;
	private Date postDate;
	private Date moveInDate;
	private String moveInDateStr;
	private String saleAmount;
	
	private String description;
	private String room;
	private String bathroom;
	private String floor;
	
	private String moveInCode;
	private String saleTypeCode;
	
	private String houseinfoNo;
	private String userEmail;
	
}
