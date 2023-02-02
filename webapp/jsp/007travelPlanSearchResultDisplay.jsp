<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.PlanDto"%>
<%@page import="dao.PlanDao" %>
<%@page import="dao.HotelphotoDao"%>
<%
ArrayList<PlanDto> pdtolist = (ArrayList<PlanDto>) application.getAttribute("PLANLIST");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/images/favicon.png">
<title>旅行プラン検索結果表示</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/tPSRD.css">
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
		<section id="reSearch">
			<h3>再検索</h3>
			<form id="research" method="post"
				action="<%=request.getContextPath()%>/Search">
				<p class="select">宿泊地</p>
				<select name="prefecture" id="selectbox" class="select">
					<option value="">選択してください</option>
					<option value="北海道">北海道</option>
					<option value="東京都">東京</option>
					<option value="京都府">京都</option>
					<option value="大阪府">大阪</option>
					<option value="沖縄県">沖縄</option>
				</select>

				<p class="select">移動手段</p>
				<select name="transportation" id="selectbox" class="select">
					<option value="">選択してください</option>
					<option value="電車">電車</option>
					<option value="新幹線">新幹線</option>
					<option value="飛行機">飛行機</option>
				</select>

				<p class="select">宿泊日</p>
				<select name="date" class="select">
					<option value="0">選択してください</option>
					<%
                    for(int i = 1;i <= 5 ;i++){ 
                    %>
                    <option value="0<%=i %>"><%=i %>泊</option>
                    <%} %>
				</select>

				<p class="select">参加人数</p>
				<select name="guests" class="select">
					<option value="0">選択してください</option>
					<option value="1">1人</option>
					<option value="2">2人</option>
					<option value="3">3人</option>
					<option value="4">4人</option>
				</select><br>
				<section class="buttonArea">
					<button type="submit" name="button" class="button"
						id="searchButton">検索</button>
				</section>
			</form>
		</section>

		<h3>検索結果</h3>
		<div id="searchResults">
			<p class="select"><%=pdtolist.size() %>件のうち、</p>
			<p class="select"><%=pdtolist.size() %>件表示</p>
		</div>

		<%
		HotelphotoDao hdao = new HotelphotoDao();
		String photo = "";
		for (int i = 0; i < pdtolist.size(); i++) {
			PlanDto pdto = pdtolist.get(i);
			photo = hdao.selectExe(pdto.getHotelId());
		%>
		 <form id="register" method="post" action="<%=request.getContextPath() %>/TravelPlanSearchResultDisplay">
		<section id="printPlan">
			<div id="planImg">
				<img
					src="<%=request.getContextPath()%>/images/<%=pdto.getHotelId()%>/<%=photo%>.jpg"
					alt="プランイメージ画像">
			</div>
			<div id="overview">
				<h2 class="title">
					<a><%=pdto.getPlanName()%></a>
				</h2>
				<p id="discription"><%=pdto.getPlanDescription()%></p>
				<%if(pdto.getTransportation().equals("電車")){ %>
				<label><input type="checkbox" value="電車" name="planWay" disabled="disabled" checked><span>電車</span></label>
				<%}else{ %>
				<label><input type="checkbox" value="電車" name="planWay" disabled="disabled"><span>電車</span></label>
				<%} %>
				
				<%if(pdto.getTransportation().equals("新幹線")){ %>
				<label><input type="checkbox" value="新幹線" name="planWay" disabled="disabled" checked><span>新幹線</span></label>
				<%}else{ %>
				<label><input type="checkbox" value="新幹線" name="planWay" disabled="disabled"><span>新幹線</span></label>
				<%} %>
				
				<%if(pdto.getTransportation().equals("飛行機")){ %>
				<label><input type="checkbox" value="飛行機" name="planWay" disabled="disabled" checked><span>飛行機</span></label>
				<%}else{ %>
				<label><input type="checkbox" value="飛行機" name="planWay" disabled="disabled"><span>飛行機</span></label>
				<%} %>
				
				
				<p id="money"><%=pdto.getMinAmount()%>～<%=pdto.getMaxAmount()%> 
					円
				</p>
				<input type="text" name="plan_id" id="naisyo" value="<%=pdto.getPlanId()%>">
				
				<section id="buttonArea2">
					<button type="submit" name="button" class="button" id="confirmButton">確認</button>
				</section>
			</div>
			
			
		</section>
		</form>
		<%
		}
		%>
		<div id="page">
			<a href="#">前へ</a> <a href="#">次へ</a>
		</div>


	</main>

	<footer>
		<small>
			&copy;2023 Special Willingness And Travels.
		</small>
	</footer>

	</div>

</body>

</html>