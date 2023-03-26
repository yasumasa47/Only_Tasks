<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.UserInfoDto"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NewAccount</title>
<link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
  <link rel="stylesheet"  href="css/new.css">
</head>
<body>
<h1>New</h1>
<h1>Account</h1>

	<form action="SaveAccount" method="post">

		<p>　ID:<br/>
			<input type="text" name="USER_ID" maxlength="20" id="ID_ID">
		</p>
		
		<p>　NAME:<br/>
			<input type="text" name="USER_NAME" maxlength="20" id="ID_NAME">
		</p>
		
		<p>　PASSWORD:<br/>
			<input type="password" name="PASSWORD" id="ID_PASSWORD">
		</p>
		
		<p>　TASK:<br/>
			<textarea name="TASK" cols="30" rows="2" id="ID_TASK"></textarea>
		</p>
		

		<input type="submit" value="アカウント作成" id="ID_SUBMIT">

	</form>
	
	<a href="Login" class="login-link">Login画面へ</a>
	
	<script type="text/javascript" src="js/account.js"></script>
	

</body>
</html>