<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：login.jsp■■■
概要：JSP
詳細：HTML文書（ログイン画面）を出力する。>>　formタグで入力し、ExecuteLoginへ
-------------------------------------------------------------------------------------------------
--%>

<html>
<head>
  <title>ログイン画面</title>
  <link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
  <link rel="stylesheet"  href="css/login.css">
  
</head>
<body>
  <h1>Only</h1>
  <h1>Tasks</h1>
  <form action="ExecuteLogin" method="post">
    <p>ID：<br>
      <input type="text" name="USER_ID" maxlength="20" id="ID_USER_ID">
    </p>
    <p>PASS：<br>
      <input type="password" name="PASSWORD" maxlength="20" id="ID_PASSWORD"> 
    </p>
    <input type="submit" value="login" id="ID_SUBMIT">
  </form>
  <script type="text/javascript" src="js/login.js"></script>
  <a href="NewAccount" class="new-link">新規アカウント</a>
</body>
</html>
