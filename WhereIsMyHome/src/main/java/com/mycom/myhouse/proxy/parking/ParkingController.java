package com.mycom.myhouse.proxy.parking;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.map.dto.ParkingLotDto;
import com.mycom.myhouse.user.dto.UserDto;

@RestController
public class ParkingController {
	
	private final String SERVICE_URL = "http://openapi.seoul.go.kr:8088/"; 
    private final String SERVICE_KEY = "62576d4147626e753131314d6c744679"; 
	
	@GetMapping(value="/parking-list")
	public ResponseEntity<List<ParkingLotDto>> parkingList(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		List<ParkingLotDto> parkingList = null;
		
		// 이후 고치기 관심지역코드(interestCode) 이용
		String dongName = "상계동";
		
		String START_INDEX = "1";
		String END_INDEX = "200";
		
		String SERVICE = "GetParkInfo";
		
		String TYPE = "xml";
		
		StringBuilder sb = new StringBuilder();
        sb.append(SERVICE_URL).append(SERVICE_KEY)
            .append("/").append(TYPE)
            .append("/").append(SERVICE)
            .append("/").append(START_INDEX)
            .append("/").append(END_INDEX)
            .append("/").append(dongName);
	        
		try {
			URL url = new URL(sb.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			System.out.println(conn.getResponseCode());
			
			BufferedReader br = null;
	        if(conn.getResponseCode() == 200) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        }else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        // api response => StringBuilder result에 넣는다
	        StringBuilder result = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            result.append(line);
	        }
	        
	        SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			ParkingSAXHandler handler = new ParkingSAXHandler();
			// 한글처리에 대한 부분
			InputStream is = new ByteArrayInputStream(result.toString().getBytes(StandardCharsets.UTF_8));
			parser.parse(is, handler);
			
			parkingList = handler.getParkingList();

//			System.out.println(parkingList);
	        
	        br.close();
	        conn.disconnect();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<ParkingLotDto>>(parkingList, HttpStatus.OK);
	}
}
