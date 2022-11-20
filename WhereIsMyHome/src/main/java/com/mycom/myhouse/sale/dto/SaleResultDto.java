package com.mycom.myhouse.sale.dto;

import java.util.List;

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
public class SaleResultDto {
	private int no;
	private String result;
	private SaleDto dto;  // 게시글 상세
	private List<SaleDto> list;  // 게시글 목록
	private int count;  // 게시글 총 건수
}
