<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@page import="dto.UserDto" %>
<%@page import="dao.UserDao" %>
<%@page import="dto.CarrentalDto" %>
<%@page import="dao.CarrentalDao" %>
<%@page import="dto.ReservationDto" %>
<%@page import="dao.ReservationDao" %>
<% UserDto udto = (UserDto) application.getAttribute("USER"); %>
<%ReservationDto rdto = (ReservationDto)application.getAttribute("RESERVATIONNEO");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title>旅行プラン購入確認</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/tPPC.css">
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
        <h1>購入確認画面</h1>
        <form id="register" method="post" action="<%=request.getContextPath() %>/TravelPlanPurchaseConfirmation">
            <h3>予約者情報</h3>
            <dl>
                <dt>氏名</dt>
                <dd>:　<%=udto.getName() %></dd>

                <dt>ID</dt>
                <dd>:　<%=udto.getUserId() %></dd>

<%
String a = String.valueOf(rdto.getCheckInDay());
String year = a.substring(0,4);
String month = a.substring(4,6);
String day = a.substring(6,8);
%>
                <dt>チェックイン日</dt>
                <dd>:　<label id="y"><%=year %>年</label>
                        <label id="m"><%=month %>月</label>
                        <label id="d"><%=day %>日</label>
                </dd>

                <dt>泊数</dt>
                <dd>:　<%=rdto.getAccommodationDate() %>泊</dd>

                <dt>チェックイン予定時間</dt>
                <dd>:　<%=rdto.getCheckInTime() %>時</dd>

                <dt>宿泊人数</dt>
                <dd>:　<%=rdto.getAdultGuests() + rdto.getChildGuests() %>人</dd>

                <dt>大人</dt>
                <dd>:　<%=rdto.getAdultGuests() %>人</dd>

                <dt>子供</dt>
                <dd>:　<%=rdto.getChildGuests() %>人</dd>

<%
if(rdto.getCarId() != 0){ 
	CarrentalDao cdao = new CarrentalDao();
	CarrentalDto cdto = cdao.selectExe(rdto.getCarId());
%>
                <dt>レンタカー</dt>
                <dd>:　<%=cdto.getCarName() %></dd>

                <section id="printCar">
               	
        	    <img
				    src="<%=request.getContextPath() %>/images/rentalcar/<%=cdto.getCarId() %>/<%=cdto.getCarPhotoPath() %>"
				    alt="車イメージ画像">
                <dl>
                    <dt>会社</dt>
                    <dd>:　<%=cdto.getCompanyName() %></dd>
    
                    <dt>サイズ</dt>
                    <dd>:　<%=cdto.getSize() %></dd>
    
                    <dt>乗車定員</dt>
                    <dd>:　<%=cdto.getHoldPeople() %>　人</dd>
    
                    <p><%=cdto.getCarDescription() %></p>
    
                    <dt>価格</dt>
                    <dd>:　<%=cdto.getRentalPrice() %>　円</dd>
    
                </dl>

                </section>
<%} %>
            </dl>

            <label class="left">キャンセル規定</label>
            <ol>
                <li>キャンセル料金は90日前から対象とする</li>
                <li>自己都合のキャンセルは全額支払う</li>
                <li>キャンセルを行った場合、マイルは全没収とする</li>
                <li>そもそもキャンセルするな</li>
            </ol>
            <section class="buttonArea">
        	    <a href="#" class="button" id="fixButton" onclick="history.back(); return false;">
        		    修正
        	    </a>
        	    <a href="<%=request.getContextPath() %>/TravelPlanPurchaseConfirmation">
                    <input type="submit" value="完了" class="button" id="completeButton">
        	    </a>
        </section>
        </form>
    </main>

    <footer>
		<small>
			&copy;2023 Special Willingness And Travels.
		</small>
	</footer>

    </div>
    
</body>

</html>