package com.mycom.myhouse.sale.dto;

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
public class SaleParamDto {
	private String searchWord;
	private String dong;
	private String dongCode;
	private String jibun;
	
	private int userSeq;
	private String userEmail;
	private int no;
	
	private int limit;
	private int offset;
	
}
