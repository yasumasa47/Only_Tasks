<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.UserInfoDto"%>

<%
//セッションからユーザーデータを取得
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
String userName = userInfoOnSession.getUserName();
String userPass = userInfoOnSession.getPassWord();
String userTask = userInfoOnSession.getTask();
String userId = userInfoOnSession.getUserId();
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Setting</title>
<link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<link rel="stylesheet" href="css/setting.css">
</head>

<body>

	<h1>Setting</h1>

	<form action="SaveSetting" method="post" >
		<p>
			ID:<%=userId%>
			<input type="hidden" name="USER_ID" value="<%=userId%>" id="ID_ID">
		</p><br/>
		<p>
			NAME: <input type="text" name="USER_NAME" value="<%=userName%>"
				maxlength="20" id="ID_NAME">
		</p>

		<p>
			PASSWORD: <input type="text" name="PASSWORD" value="<%=userPass%>"
				id="ID_PASSWORD">
		</p>

		<p>
			TASK:
			<textarea name="TASK" cols="30" rows="2" id="ID_TASK"><%=userTask%></textarea>
		</p>
		
		<input type="submit" value="保存" id="ID_SUBMIT">

	</form>
	<script type="text/javascript" src="js/account.js"></script>



</body>
</html>
