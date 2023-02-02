<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Integer sum = (Integer)application.getAttribute("ERRORSUM");
if(sum == null){
	sum = 1;
}
%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.png">
        <title>ログイン</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css">
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
            <div id="login">
            <form id="home" method="post" action="<%=request.getContextPath() %>/Login">
                <h1>ログイン</h1>
                <dl>
                    <dt>ID</dt>
                    <dd>
                        <input type="text" name="userId" size="40" id="txt" maxlength="16" required class="left">
                    </dd>
                    <dt>パスワード</dt>
                    <dd>
                        <input type="password" name="password" size="40" id="txt" maxlength="8" placeholder="半角英数字8ケタ"
                pattern="[a-zA-Z0-9]+" title="パスワードは半角英数字で入力してください。" required class="left"><br>
                    </dd>
                    <%if(sum%2 == 0){ %>
            <dt></dt>
            <dd class="attention">＊IDもしくは、パスワードが違います</dd>
            <%} %>
                </dl>
                <p></p>
                <br>
                <section class="buttonArea">
                    <a href="<%=request.getContextPath() %>/jsp/001register.jsp" class="button" id="registerButton">会員登録</a>
                    <button type = "submit" class="button" id="loginButton">ログイン</button>
                </section>
                </form>
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