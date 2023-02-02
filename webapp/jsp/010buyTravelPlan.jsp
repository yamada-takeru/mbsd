<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.CarrentalDto" %>
<%@page import="dao.CarrentalDao" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.UserDto" %>
<%@page import="dao.UserDao" %>
<%@page import="dao.PlanDao" %>
<%
UserDto udto = (UserDto) application.getAttribute("USER");
ArrayList<CarrentalDto> cdtolist = (ArrayList<CarrentalDto>)application.getAttribute("CARLIST");
int planId = (int)application.getAttribute("PLANID");
String today = (String)application.getAttribute("TODAY");
%>
<!DOCTYPE html>
<html lang= "ja">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title>プラン購入</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/buyTP.css">
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
    <h1>購入画面</h1>
    <form id="buyTravelPlan" method="post" action="<%=request.getContextPath() %>/BuyTravelPlan">
        <h3>予約者情報</h3>
        <dl>
            <dt>氏名</dt>
            <dd>:　<%=udto.getName() %></dd>

            <dt>ID</dt>
            <dd>:　<%=udto.getUserId() %></dd>

            <dt><label>チェックイン日</label></dt>
            <dd>:　<input type="date" name="checkInDay" id="date" min = "<%=today %>"></dd>

            <dt><label>泊数</label></dt>
            <dd>:　
                <select name="accommodationDate" id="selectbox">
                    <option value="">選択してください</option>
                    <%
                    PlanDao pd = new PlanDao();
                    int a = pd.MaxDateExe(planId);
                    for(int i = 1;i <= a ;i++){ 
                    %>
                    <option value="0<%=i %>"><%=i %>泊</option>
                    <%} %>
                </select>
            </dd>

            <dt><label>チェックイン予定時間</label></dt>
            <dd>:　
                <select name="checkInTime" id="selectbox">
                    <option value="">時間を選択</option>
                    <%for(int i = 14;i <= 24;i++){ %>
                    <option value="<%=i %>"><%=i %>時</option>
                    <%} %>
                </select>
            </dd>
        
        </dl>

        <label>宿泊人数</label>

        <dl>

            <dt>大人</dt>
            <dd>:　
                <select name="adult" id="selectbox">
                 <%for(int i = 1;i <= 3;i++){ %>
                    <option value="<%=i %>"><%=i %>人</option>
                    <%} %>
                </select>
            </dd>

            <dt>子供</dt>
            <dd>:　
                <select name="kids" id="selectbox">
					<%for(int i = 0;i <= 3;i++){ %>
                    <option value="<%=i %>"><%=i %>人</option>
                    <%} %>
                </select>
            </dd>
    
            <dt><label>レンタカー</label></dt>
            <dd id="choiceCar">:　
                <label>
                    <input type="checkbox" name="check" id="rentCheck" onclick="radioDeselection()" class="button">
                    <span>車を選ぶ</span>
                </label>
            </dd>
        
        <div id="rentCarArea">
        
        <%
        for(int i = 0;i < cdtolist.size();i++){
        CarrentalDto cd = cdtolist.get(i);	
        %>
        <section id="printCar">
            <p id="carName"><%=cd.getCarName() %></p>
            <img
		    	src="<%=request.getContextPath() %>/images/rentalcar/<%=cd.getCarId() %>/<%=cd.getCarPhotoPath() %>"
			    alt="車イメージ画像">
            
            <dl>
                <dt>会社</dt>
                <dd>:　<%=cd.getCompanyName() %></dd>

                <dt>サイズ</dt>
                <dd>:　<%=cd.getSize() %></dd>

                <dt>乗車定員</dt>
                <dd>:　<%=cd.getHoldPeople() %>　人</dd>

                <dt>価格</dt>
                <dd>:　<%=cd.getRentalPrice() %>　円</dd>

                <dt id="leftDes"></dt>
                <dd class="description"><%=cd.getCarDescription() %></dd>

            </dl>

            <section class="buttonArea">
                <label><input type = "radio" name="carid" value="<%=cd.getCarId() %>" id="carButton" class="button"><span>これにする！</span></label>
            </section>

        </section>
        <%} %>
        </div>

        </dl>

        <label>キャンセル規定</label>
        <ol>
            <li>キャンセル料金は90日前から対象とする</li>
            <li>自己都合のキャンセルは全額支払う</li>
            <li>キャンセルを行った場合、マイルは全没収とする</li>
            <li>そもそもキャンセルするな</li>
        </ol>

        <section class="buttonArea">
        	<a href="#" class="button" id="returnButton" onclick="history.back(); return false();">
        		戻る
        	</a>
        	<input type="submit" value="確認" class="button" id="confirmButton">
        </section>
        </form>
    </main>

    <footer>
        <small>
            &copy;2023 Special Willingness And Travels.
        </small>
    </footer>

    </div>

    <script src = "<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script src = "<%=request.getContextPath()%>/js/buyTP.js"></script>

</body>

</html>