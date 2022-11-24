package com.mycom.myhouse.sale.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.sale.dao.SaleDao;
import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleParamDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;

@Service
public class SaleServiceImpl implements SaleService{
	
	@Autowired
	SaleDao dao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";

	@Override
	public SaleResultDto saleList(SaleParamDto saleParamDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<SaleDto> list = dao.saleList(saleParamDto);
			int count = dao.saleListTotalCount(saleParamDto);
			
			// 로그인한 사용자이면
			if(saleParamDto.getUserSeq() != 0) {
				// 찜한 매물 no 가져오기
				int[] favSaleNo = dao.getUserFav(saleParamDto.getUserSeq());
				
				for (int i = 0; i < favSaleNo.length; i++) {
					for (int j = 0; j < list.size(); j++) {
						if(favSaleNo[i] == list.get(j).getNo())
							list.get(j).setFav(true);
					}
				}
			}
			
			saleResultDto.setList(list);
			saleResultDto.setCount(count);
			
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		
		return saleResultDto;
	}
	
	@Override
	public SaleResultDto saleListSearchWord(SaleParamDto saleParamDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<SaleDto> list = dao.saleListSearchWord(saleParamDto);
			int count = dao.saleListSearchWordTotalCount(saleParamDto);
			
			// 로그인한 사용자이면
			if(saleParamDto.getUserSeq() != 0) {
				// 찜한 매물 no 가져오기
				int[] favSaleNo = dao.getUserFav(saleParamDto.getUserSeq());
				
				for (int i = 0; i < favSaleNo.length; i++) {
					for (int j = 0; j < list.size(); j++) {
						if(favSaleNo[i] == list.get(j).getNo())
							list.get(j).setFav(true);
					}
				}
			}
			
			saleResultDto.setList(list);
			saleResultDto.setCount(count);
			
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		
		return saleResultDto;
	}
	
	@Override
	public SaleResultDto saleDelete(int no) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			dao.saleDelete(no);
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto saleListDealer(SaleParamDto saleParamDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			List<SaleDto> list = dao.saleListDealer(saleParamDto);
			int count = dao.saleListDealerCount(saleParamDto);
			saleResultDto.setResult(SUCCESS);
			saleResultDto.setList(list);
			saleResultDto.setCount(count);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto saleDetail(int no) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			SaleDto dto = dao.saleDetail(no);
			saleResultDto.setResult(SUCCESS);
			saleResultDto.setDto(dto);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto saleInsert(SaleDto saleDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			dao.saleInsert(saleDto);
			saleResultDto.setResult(SUCCESS);
			saleResultDto.setNo(saleDto.getNo());
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}
	
	@Override
	public SaleResultDto saleUpdateMoveInDate(SaleDto saleDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			String dateStr = saleDto.getMoveInDateStr();
			Date date = java.sql.Date.valueOf(dateStr);
			saleDto.setMoveInDate(date);
			
			dao.saleUpdateMoveInDate(saleDto);
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}
	
	@Override
	public SaleResultDto saleUpdate(SaleDto saleDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			dao.saleUpdate(saleDto);
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto houseSearchByAddress(HouseDto dto) {
		System.out.println("saleServiceImpl - houseSearchByAddr");
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			int no = -1;
			String noStr = dao.houseSearchByAddress(dto);
			if(noStr != null) no = Integer.parseInt(noStr);
			
			saleResultDto.setNo(no);
			saleResultDto.setResult(SUCCESS);
			System.out.println(no);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto houseInsert(HouseDto dto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			dao.houseInsert(dto);
			saleResultDto.setNo(dto.getNo());
			System.out.println("saleServiceImpl - houseInsert " + saleResultDto);
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}
}
