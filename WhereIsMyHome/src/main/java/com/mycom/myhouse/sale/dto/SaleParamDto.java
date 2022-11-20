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
public class SaleParamDto {
	private String searchWord;
	private String dong;
	private String dongCode;
	private String jibun;
	
	private String userEmail;
	
}
