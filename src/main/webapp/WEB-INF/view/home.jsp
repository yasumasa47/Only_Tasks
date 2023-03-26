<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.TimeLineDto"%>

<%@ page import="model.UserInfoDto"%>

<%@ page import="java.text.SimpleDateFormat"%>





<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：home.jsp■■■
概要：JSP
詳細：HTML文書（Home画面）を出力する。
-------------------------------------------------------------------------------------------------
--%>


<%
//セッションからユーザーデータを取得
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
String userName = userInfoOnSession.getUserName();
String userTask = userInfoOnSession.getTask();
String userId = userInfoOnSession.getUserId();
%>

<%
//「Time_Line」テーブルからデータを全件抽出
List<TimeLineDto> list = (List<TimeLineDto>) request.getAttribute("HOME_LIST");
%>


<html>

<head>
<title>TimeLine</title>
<link
	href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<link rel="stylesheet" href="css/home.css">

</head>

<body>
	<h1>Only Tasks</h1>


	<div class="hamburger-menu">
		<div class="bar"></div>
		<div class="bar"></div>
		<div class="bar"></div>
	</div>

	<div class="side">
		<a href="Home" class="close-menu">×</a> <a href="Setting"
			class="setting-link">設定</a> <a href="ExecuteLogout"
			class="logout-link">logout</a>
	</div>

	<script type="text/javascript" src="js/hamburgerMenu.js"></script>

	<div class="Task">
		<form action="SaveTimeLine" method="post">

			<p>
				<input type="hidden" name="NAME" value="<%=userName%>">
			</p>

			<h3 class="MyTask">My Task：</h3>
			<p class="userTask"><%=userTask%></p>
			<input type="hidden" name="TASK" value="<%=userTask%>">


			<p>
				<input type="hidden" name="STATE" value="完了">
			</p>

			<input type="submit" value="完了">

		</form>
	</div>


	<div class="calendar-container">
		<a href="Calendar" class="calendar-open"></a>
	</div>

	<h3 class="timeline">Time Line</h3>

	<%
	for (int i = 0; i < list.size(); i++) {
		TimeLineDto dto = list.get(i);
		SimpleDateFormat date = new SimpleDateFormat("MM/dd"); // 表示フォーマットの指定
		SimpleDateFormat time = new SimpleDateFormat("HH:MM"); // 表示フォーマットの指定
	%>



	<%
	if (userName.equals(dto.getName())) {
	%>

	<div class="balloon_r">
		<div class="faceicon">
			<img
				src="https://stand-4u.com/stand-4u/wp-content/uploads/2019/09/s4man.png"
				alt="">
		</div>

		<p class="says">
			<%=replaceEscapeChar(dto.getName())%>がタスクを完了しました。
		</p>
	</div>
	<div class="date-r"><%=date.format(dto.getTime())%></div>
	<div class="time-r"><%=time.format(dto.getTime())%></div>

	<%
	} else {
	%>
	<div class="balloon_l">
		<div class="faceicon">
			<img
				src="https://stand-4u.com/stand-4u/wp-content/uploads/2019/09/s4man.png"
				alt="">
		</div>

		<div class="says">
			<%=replaceEscapeChar(dto.getName())%>がタスクを完了しました。<br />
			<div class="timline-task">
				Task:
				<%=replaceEscapeChar(dto.getTask())%>
			</div>
		</div>
	</div>
	<div class="date-l"><%=date.format(dto.getTime())%></div>
	<div class="time-l"><%=time.format(dto.getTime())%></div>

	<%
	}
	%>
	</div>
	<%
	}
	%>



	<br>



</body>
</html>

<%!String replaceEscapeChar(String inputText) {

		String charAfterEscape = inputText; //エスケープ後の文字列データ

		// 「&」を変換
		charAfterEscape = charAfterEscape.replace("&", "&amp;");
		// 「<」を変換
		charAfterEscape = charAfterEscape.replace("<", "&lt;");
		// 「>」を変換
		charAfterEscape = charAfterEscape.replace(">", "&gt;");
		// 「"」を変換
		charAfterEscape = charAfterEscape.replace("\"", "&quot;");
		// 「'」を変換
		charAfterEscape = charAfterEscape.replace("'", "&#039;");

		return charAfterEscape;
	}%>