<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="map.dto.*" %> 
<%
    String contextPath = request.getContextPath();
	UserDto userDto = (UserDto)session.getAttribute("userDto");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/css/header.css" />

</head>
<body>
    <nav class="navbar navbar-light bg-light">

      <div id="nav-menu">
			
			<div id="nav-menu-left">
		 		<button class="btn btn-sm btn-outline-secondary" id="homeBtn"><a href="<%= contextPath %>/jsp/index.jsp">Home</a></button>
			</div>
  			<div id="nav-menu-right">
  				<% if( userDto == null) { %>
	  			<button class="btn btn-sm btn-outline-secondary" type="button" id="login-btn" ><a href="<%=contextPath %>/jsp/index.jsp">login</a></button>
		       	<button class="btn btn-sm btn-outline-secondary" type="button" id="register-btn" ><a href="<%=contextPath %>/jsp/register.jsp">register</a></button>
		        <% } else { %>
	    
	            <button class="btn btn-sm btn-outline-secondary parking" ><a  id="searchBtn" onclick="search()" href="./map/mapMain.jsp">Search</a> </button>
                <button class="btn btn-sm btn-outline-secondary parking"> <a  aria-current="page" id="parking" href="<%=contextPath %>/jsp/map/mapParking.jsp">Favorite Area Parking Info</a></button>
                <button class="btn btn-sm btn-outline-secondary"><a  aria-current="page" id="logout"href="#">logout</a></button>
                <button class="btn btn-sm btn-outline-secondary"><a  aria-current="page" id="myPage" href="<%=contextPath %>/user/userMain">mypage</a></button>
            
  				<% } %>
  			</div>
            <!-- 로그인 전 -->	
   		</div>
    </nav>
    

</body>
<script>
	document.querySelector("#logout").onclick = function(){
		logout();
	}
	
	async function logout(){
	let url = "<%= contextPath%>/logout";
	
	try{
	    let response = await fetch( url ); 
	    let data = await response.json(); 
	    if( data.result == "fail"){ 
	      window.location.href = "<%= contextPath%>/jsp/login.jsp";
	    }else if( data.result == "fail"){
	    	alertify.alert("오류",'로그아웃 과정에서 오류가 발생했습니다.');
	    }            	
	}catch(error){
		alertify.alert("오류",'로그아웃 과정에서 오류가 발생했습니다.');
	}
	}
</script>
</html>