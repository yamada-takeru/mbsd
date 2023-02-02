<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Integer sum = (Integer)application.getAttribute("ERRORSUM");
if(sum == null){
	sum = 1;
}
%>
<!DOCTYPE html>
<html lang= "ja">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
    <title>会員情報更新</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/update.css">
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
<h1>会員情報更新</h1>

    <form id="update" method="post" action="<%=request.getContextPath() %>/Updatereturn">
        <dl>
            <dt>氏名</dt>
            <dd>
                性 <input type="text" name="sei" id="sei" maxlength="15" required>
                名 <input type="text" name="mei" id="mei" maxlength="15" required>  
            </dd>

            <dt>氏名（フリガナ）</dt>
            <dd>セイ<input type="text" name="hurigana_sei" id="hurigana_sei" maxlength="30" required>
                メイ<input type="text" name="hurigana_mei" id="hurigana_mei" maxlength="30" required>
            </dd>

            <dt>電話番号</dt>
            <dd>
                <input type="tel" name="phone_number" id="phone_number" maxlength="11" required class="left" placeholder = "ハイフンなし">
            </dd>

            <%if(sum%3 == 0){ %>
            <dt></dt>
            <dd class="attention">＊電話番号を記入してください</dd>
            <%} %>
            <dt>ID</dt>
            <dd>
                <input type="text" name="user_id" id="user_id" maxlength="16" required class="left" placeholder = "8字～16字">
            </dd>
            <%if(sum%5 == 0){ %>
            <dt></dt>
            <dd class="attention">＊IDを入力してください</dd>
            <%} %>
            <%if(sum%7 == 0){ %>
            <dt></dt>
            <dd class="attention">＊すでにIDが使われています</dd>
            <%} %>
            <dt>パスワード</dt>
            <dd>
                <input type="password" name="password" id="password" maxlength="8" placeholder="半角英数字8ケタ"
                pattern="[a-zA-Z0-9]+" title="パスワードは半角英数字で入力してください。" required class="left">
            </dd>
            <dt>パスワード(再入力)</dt>
            <dd>
                <input type="password" name="repassword" id="repassword" maxlength="8" placeholder="半角英数字8ケタ"
                pattern="[a-zA-Z0-9]+" title="パスワードは半角英数字で入力してください。" required class="left">
            </dd>
    		<%if(sum%2 == 0){ %>
            <dt></dt>
            <dd class="attention">＊パスワードが一致していません</dd>
            <%} %>
        </dl>

        <section class="buttonArea">
        	<a href="#" class="button" id="returnButton" onclick="history.back(); return false;">
    			戻る
    		</a>
            <button type="submit" name="button" class="button" id="confirmButton">確認</button>
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