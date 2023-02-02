<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.UserDto"%>
<%
UserDto udto = (UserDto) application.getAttribute("USER");
Integer sum = 1;
application.setAttribute("ERRORSUM", sum);
%>
<!DOCTYPE html>
<html lang= "ja">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title>マイページ</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/mypage.css">
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
        <h1>マイページ</h1>

        <section id="userContent">
            <dl>
                <dt>氏名</dt>
                <dd>:　　<%=udto.getName()%></dd>

                <dt>氏名(フリガナ)</dt>
                <dd>:　　<%=udto.getFurigana()%></dd>

                <dt>電話番号</dt>
                <dd>:　　<%=udto.getPhoneNumber()%></dd>

                <dt>現在のランク</dt>
                <dd>:　　<%=udto.getRank()%></dd>

                <dt>現在のマイル数</dt>
                <dd>:　　<%=udto.getMile()%></dd>

            </dl>

        </section>

        <section class="buttonArea">
            <a href="<%=request.getContextPath() %>/jsp/006home.jsp" class="button" id="homeButton">ホームへ戻る</a>
            <a href="<%=request.getContextPath() %>/Exit" class="button" id="exitButton">退会する</a>
            <a href="<%=request.getContextPath() %>/Reservation" class="button" id="reserveButton">予約情報確認</a>
            <a href="<%=request.getContextPath() %>/jsp/004update.jsp" class="button" id="updateButton">登録情報変更</a>

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
