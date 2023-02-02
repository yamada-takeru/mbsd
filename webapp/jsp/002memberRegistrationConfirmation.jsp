<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.UserDto"%>
<%
UserDto udto = (UserDto)application.getAttribute("USER");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title>会員登録確認</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/mRConfirm.css">
</head>

<body>
	<div class = container>

<header>
    
        <a href="<%=request.getContextPath() %>/jsp/006home.jsp">
            <img src="<%=request.getContextPath() %>/images/logo.png" alt="SWAT">
        </a>
    
</header>

<main>
        <h1>会員登録確認</h1>

        <section id="confirmArea">
            
            <dl>
                <dt>氏名</dt>
                <dd>
                    :　<%=udto.getName()%>
                </dd>

                <dt>氏名(フリガナ)</dt>
                <dd>
                    :　<%=udto.getFurigana()%>
                </dd>

                <dt>電話番号</dt>
                <dd>
                    :　<%=udto.getPhoneNumber()%>
                </dd>
            
                <dt>ID</dt>
                <dd>
                    :　<%=udto.getUserId()%>
                </dd>

                <dt>パスワード</dt>
                <dd>
                    :　<input type = "password" value = "<%=udto.getPassword()%>" disabled>
                    
                    
                </dd>

            </dl>

            <section class="buttonArea">
                <a href="#" class="button" id="fixButton" onclick="history.back(); return false;">
                    修正
                </a>
                <a href="<%=request.getContextPath() %>/MembershipRegistrationComplete" class="button" id="completeButton">
                    完了
                </a>
            </section>

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