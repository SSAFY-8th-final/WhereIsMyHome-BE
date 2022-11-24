package com.mycom.myhouse.user.dto;

import java.util.List;

import com.mycom.myhouse.sale.dto.SaleDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FavResultDto {
	private String result;	// 결과
	private List<SaleDto> list; // 관심목록
	private int count;  // 총 건수
}
