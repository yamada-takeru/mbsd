<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang= "ja">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/complete.css">
</head>

<body>
	<div class = container>

    <!--ヘッダー-->
<header>
    
        <a href="<%=request.getContextPath() %>/jsp/006home.jsp">
            <img src="<%=request.getContextPath() %>/images/logo.png" alt="SWAT"></a>
    
</header>
<!--/ヘッダー-->

<main>
    <p>ご予約ありがとうございました。<br>
    <i>Thank you for your reservation. Enjoy！</i></p>
  
    <a href="<%=request.getContextPath() %>/jsp/006home.jsp">
        <input type="button" name="home" value="ホームへ戻る" class="button" id="returnButton">
    </a>
    
</main>

<footer>
    <small>
        &copy;2023 Special Willingness And Travels.
    </small>
</footer>

</div>

</body>

</html>