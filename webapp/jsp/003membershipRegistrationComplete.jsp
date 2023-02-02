<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/images/favicon.png">
<title>登録完了</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mRComplete.css">

</head>

<body>
	<div class = container>

	<header>
		<a href="<%=request.getContextPath()%>/jsp/006home.jsp"> 
		<img src="<%=request.getContextPath()%>/images/logo.png" alt="SWAT">
		</a>
	</header>

	<main>
		<h1>登録完了しました</h1>
		<section class="buttonArea">
			<a href="<%=request.getContextPath()%>/jsp/005login.jsp" class="button" id="loginButton">ログイン画面へ</a>
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