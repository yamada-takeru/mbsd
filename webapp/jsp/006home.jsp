<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.UserDto" %>
<% 
UserDto user = (UserDto) application.getAttribute("USER"); 
boolean a = false;
application.setAttribute("ANGELS", a);
boolean loginkekka = false;
try{
    loginkekka = (boolean) application.getAttribute("LOGINKEKKA"); 
}catch(Exception e){
	loginkekka = false;
}
Integer sum = 1;
application.setAttribute("ERRORSUM", sum);
%>
<!DOCTYPE html>
<html lang= "ja">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title>ホーム</title>
    <link rel = "stylesheet" type="text/css" href = "<%=request.getContextPath() %>/slick/slick.css">
    <link rel = "stylesheet" type="text/css" href = "<%=request.getContextPath()%>/slick/slick-theme.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/home.css">
</head>

<body>
	<div class = container>

    <header>
        <section class="imgArea">
            <a href="<%=request.getContextPath()%>/jsp/006home.jsp">
                <img src="<%=request.getContextPath() %>/images/logo.png" alt="SWAT">
            </a>
        </section>
        <div id="headerArea">
        <section id="header_01">
            <%
            try{
            if(user.getName() != null){	
            %>
            <label>ようこそ！<%=user.getName() %> さん✈</label>
            <%
            }else{
            %>
            	<label>ログインしてください</label>
            <%}
            	}catch(Exception e){ 
            %>
            <label>ログインしてください</label>
            <%} %>
            <a href="<%=request.getContextPath() %>/Mypage" class="button" id="myPageButton">マイページ</a>
        </section>
        <section id="header_02">
        <%if(loginkekka == false){ %>
            <a href="<%=request.getContextPath()%>/jsp/005login.jsp" class="button" id="loginButton">ログイン</a>
            <a href="<%=request.getContextPath()%>/jsp/001register.jsp" class="button" id="registerButton">新規会員登録</a>
            <%}else{ %>
            <a href="<%=request.getContextPath()%>/Logout" class="button" id="logoutButton">ログアウト</a>
            <%} %>
            
        </section>
        
        </div>
    </header>
    
    <main>
        <section id="kensaku">
            <h2>プラン検索</h2>
            <form id="home" method="post" action="<%=request.getContextPath()%>/Search">
                <dl>
                    <dt>宿泊地</dt>
                    <dd>
                        <select name="prefecture" id="selectbox">
                            <option value="">選択してください</option>
                            <option value="北海道">北海道</option>
                            <option value="東京都">東京</option>
                            <option value="京都府">京都</option>
                            <option value="大阪府">大阪</option>
                            <option value="沖縄県">沖縄</option>
                        </select>
                    </dd>
                    <dt>移動手段</dt>
                    <dd>
                        <select name="transportation" id="selectbox">
                            <option value="">選択してください</option>
                            <option value="電車">電車</option>
                            <option value="新幹線">新幹線</option>
                            <option value="飛行機">飛行機</option>
                        </select>
                    </dd>
                    <dt>宿泊日</dt>
                    <dd>
                        <select name="date" id="selectbox">
                            <option value="0">選択してください</option>
                            <option value="1">一泊</option>
                            <option value="2">二泊</option>
                            <option value="3">三泊</option>
                        </select>
                    </dd>
                    <dt>参加人数</dt>
                    <dd>
                        <select name="guests" id="selectbox">
                            <option value="0">選択してください</option>
                            <option value="1">1人</option>
                            <option value="2">2人</option>
                            <option value="3">3人</option>
                            <option value="4">4人</option>
                        </select>
                    </dd>
                </dl>

                <section class="buttonArea">
                    <button type="submit" name="button" class="button" id="searchButton">検索</button>

                </section>

            </form>

        </section>

        <section>
            <aside class="slideshow">
                <div id="bigImg"><img src="<%=request.getContextPath()%>/images/homeSlide/01_hokkaido.jpg" name="スライドショー" ></div>
                <div id="img1"><img src="<%=request.getContextPath()%>/images/homeSlide/02_okinawa.jpg" name="img1"></div>
                <div id="img2"><img src="<%=request.getContextPath()%>/images/homeSlide/03_kyoto.jpg" name="img2"></div>
                <div id="img3"><img src="<%=request.getContextPath()%>/images/homeSlide/04_osaka.jpg" name="img3"></div>
                <div id="img4"><img src="<%=request.getContextPath()%>/images/homeSlide/05_tokyo.jpg" name="img4"></div>
            </aside>
        </section>

    </main>

    <footer>
        <small>
            &copy;2023 Special Willingness And Travels.
        </small>
    </footer>

    </div>

    <script src = "<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script src = "<%=request.getContextPath()%>/slick/slick.min.js"></script>
    <script src = "<%=request.getContextPath()%>/js/home.js"></script>
</body>

</html>