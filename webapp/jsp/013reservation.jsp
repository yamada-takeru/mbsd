<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.ReservationDto"%>
<%@page import="dto.PlanDto"%>
<%@page import="dto.HotelDto"%>
<%@page import="dto.CarrentalDto"%>
<% ArrayList<ReservationDto> rdtolist = (ArrayList<ReservationDto>) application.getAttribute("RESERVATION");%>
<% ArrayList<PlanDto> pdtolist = (ArrayList<PlanDto>) application.getAttribute("RES_PLANLIST");%>
<% ArrayList<HotelDto> hdtolist = (ArrayList<HotelDto>) application.getAttribute("RES_HOTELLIST");%>
<% ArrayList<CarrentalDto> cdtolist = (ArrayList<CarrentalDto>) application.getAttribute("CARRENTALLIST");%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title>予約情報確認</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reservation.css">
</head>

<body>
	<div class = container>
    <header>
        <section class="imgArea">
            <a href="<%=request.getContextPath() %>/jsp/006home.jsp">
                <img src="<%=request.getContextPath() %>/images/logo.png" alt="SWAT">
            </a>
        </section>
    </header>
    
    <main>
    <h1>予約情報</h1>
    
<%
for(int i = 0;i < rdtolist.size();i++){ 
	ReservationDto rdto = rdtolist.get(i);
	PlanDto pdto = pdtolist.get(i);
	HotelDto hdto = hdtolist.get(i);
	CarrentalDto cdto = cdtolist.get(i);
%>   
		<form method="post" action="<%=request.getContextPath() %>/ReservationUpdate">
	
	       <section id="printReservation">
		
        <dl>
            <dt>プラン名</dt>
            <dd>:　<%=pdto.getPlanName() %></dd>

            <dt>プラン情報</dt>
            <dd class="description">:　<%=pdto.getPlanDescription() %></dd>

            <dt>ホテル名</dt>
            <dd>:　<a href="<%=hdto.getHotelUrl() %>" id="hotelUrl" target="blank"><%=hdto.getHotelName() %></a></dd>

            <dt>ホテル住所</dt>
            <dd>:　<%=hdto.getHotelAddress() %></dd>

            <dt>ホテル説明</dt>
            <dd class="description">:　<%=hdto.getHotelDescription() %><dd>

            <dt>チェックイン日</dt>
            <dd>:　<%=rdto.getCheckInDay() %></dd>

            <dt>チェックイン時間</dt>
            <dd>:　<%=rdto.getCheckInTime() %>　時</dd>

            <dt>値段</dt>
            <dd>:　<%=rdto.getFixedPrice() %>　円</dd>

            <dt>宿泊日数</dt>
            <dd>:　<%=rdto.getAccommodationDate() %></dd>

            <dt>予約人数(大人)</dt>
            <dd>:　<%=rdto.getAdultGuests() %>　人</dd>

            <dt>予約人数(子供)</dt>
            <dd>:　<%=rdto.getChildGuests() %>　人</dd>
		
    		<%if(cdto.getCarId()!=0){ %>
            <dt>レンタカー名</dt>
            <dd>:　<%=cdto.getCarName() %></dd>
       	    <%} %>
        </dl>
        
        <section class="buttonArea">
        	<input type="checkbox" name="reservation" id="naisyo" value="<%=rdto.getReservationId() %>" checked>
        	<input type="submit" value="キャンセル" class="button" id="cancelButton">
        </section>
		
        </section>
        
        </form>
        
<%} %>  
 
        <section class="buttonArea">
            <a href="<%=request.getContextPath() %>/jsp/009mypage.jsp" class="button" id="returnButton" >
    		マイページに戻る
    		</a>
        </section>
        
    </main>

    <footer>
		<small>
			&copy;2023 Special Willingness And Travels.
		</small>
	</footer>

    </div>

</body>
</html>