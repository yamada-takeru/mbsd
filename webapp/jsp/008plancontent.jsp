<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.PlanDto"%>
<%@page import="dto.HotelDto"%>
<%@page import="dto.ScheduleDto"%>
<%@page import="dao.HotelphotoDao"%>

<%
PlanDto pdto = (PlanDto)application.getAttribute("PDTO");
HotelDto hdto = (HotelDto)application.getAttribute("HDTO");
ArrayList<String> con = (ArrayList<String>)application.getAttribute("CON");
ArrayList<String> sights = (ArrayList<String>)application.getAttribute("SIGHTS");
ArrayList<ScheduleDto> sdtolist = (ArrayList<ScheduleDto>)application.getAttribute("SCHLIST");
%>
<!DOCTYPE html>
<html lang= "ja">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title>プラン内容</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/plancontent.css">
</head>

<body>
	<div class = container>

    <!--ヘッダー-->
<header>
    <section class="imgArea">
        <a href="<%=request.getContextPath() %>/jsp/006home.jsp">
            <img src="<%=request.getContextPath() %>/images/logo.png" alt="SWAT">
        </a>
    </section>
</header>
<!--/ヘッダー-->

<main>
    <%
    HotelphotoDao hpdao = new HotelphotoDao();
    String photo = hpdao.selectExe(pdto.getHotelId());
    %>
    <h2><%=pdto.getPlanName() %></h2>
    <section id="planImg">
        <img src="<%=request.getContextPath() %>/images/<%=pdto.getHotelId()%>/<%=photo %>.jpg" alt="プラン写真">

    </section>

    <aside id="sideArea1">
        <dl id="contentArea">
            <dt>参加人数<dt>
            <dd>:　<%=pdto.getMinGuests() %>～<%=pdto.getMaxGuests() %>人</dd>
            
            <dt>金額</dt>
            <dd>:　<%=pdto.getMinAmount() %>円<br>　　～<%=pdto.getMaxAmount() %>円</dd>
            
            <dt>貯まるマイルポイント</dt>
            <dd>:　<%=pdto.getMaxAmount()/100 %>ポイント</dd>
        </dl>

    </aside>

    <section id="scheduleArea">
    <%
    for(int i = 0;i < sdtolist.size();i++){
    	ScheduleDto sd = sdtolist.get(i);
    %>
    <h3>スケジュールパターン<%=sd.getSequentialNumber() %></h3>
        <p><%=sd.getSchedule() %></p>
		<%} %>
    </section>

    <h3>プラン内容</h3>
    <section id="planDescription">
        <p><%=pdto.getPlanDescription() %></p>
    
    </section>

    <section id="hotelNameArea">
        <a href="<%=hdto.getHotelUrl() %>" target="blank" id="hotelName"><%=hdto.getHotelName() %></a>

    </section>

    <aside id="sideArea2">

        <img src="<%=request.getContextPath() %>/images/<%=pdto.getHotelId()%>/<%=photo %>.jpg" alt="ホテル写真">

    </aside>

    <section id="mapArea">
        <h3>周辺アクセス</h3>
        	<iframe src="<%=hdto.getHotelPath() %>"
        		width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade">
        	</iframe>

    </section>

    <section id="conArea">
        <h3>周辺コンビニ</h3>
        <%for(int i = 0;i < con.size();i++){%>
        <p>・<%=con.get(i) %></p>
		<%} %>
    </section>

    <section id="spotArea">
        <h3>周辺観光スポット</h3>
        <%for(int i = 0;i < sights.size();i++){%>
        <p>・<%=sights.get(i) %></p>
		<%} %>

    </section>

    <section id="buttonArea">
    	<a href="#" class="button" id="returnButton" onclick="history.back(); return false;">
    		検索結果に戻る
    	</a>
        <a href="<%=request.getContextPath() %>/Plancontent" class="button" id="goButton">
            予約に進む
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