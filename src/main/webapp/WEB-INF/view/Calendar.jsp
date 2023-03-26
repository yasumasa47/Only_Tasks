<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.TimeLineDto"%>

<%@ page import="model.UserInfoDto"%>
<%@ page import="model.CalendarDto"%>



<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：show_all_survey.jsp■■■
概要：JSP
詳細：HTML文書（回答一覧画面）を出力する。
-------------------------------------------------------------------------------------------------
--%>

<%
CalendarDto mc = (CalendarDto) request.getAttribute("mc");
%>

<%
//セッションからユーザーデータを取得
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
String userName = userInfoOnSession.getUserName();
String userTask = userInfoOnSession.getTask();
%>

<%
//「survey」テーブルからデータを全件抽出
List<TimeLineDto> list = (List<TimeLineDto>) request.getAttribute("ALL_SURVEY_LIST");
%>


<html>
<head>
<title>TimeLine</title>
<link
	href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<link rel="stylesheet" href="css/calendar.css">
</head>
<body>
	<h1>Only Tasks</h1>


	<div class="hamburger-menu">
		<div class="bar"></div>
		<div class="bar"></div>
		<div class="bar"></div>
	</div>

	<div class="side">
		<a href="Calendar" class="close-menu">×</a> <a href="Setting"
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

	<div class="Home-container">
		<a href="Home" class="Home-open"></a>
	</div>

	<h3 class="calendar">Calendar</h3>
	<div id="container">
		<h3><%=mc.getYear()%>/<%=mc.getMonth()%></h3>
		
		<table>
			<tr>
				<th>日</th>
				<th>月</th>
				<th>火</th>
				<th>水</th>
				<th>木</th>
				<th>金</th>
				<th>土</th>
			</tr>
			<%
			for (String[] row : mc.getData()) {
			%>
			<tr>
				<%
				for (String col : row) {
				%>
				<%
				if (col.startsWith("*")) {
				%>
				<td class="today"><%=col.substring(1)%></td>
				<%
				} else {
				%>
				<td><%=col%></td>
				<%
				}
				%>
				<%
				}
				%>
			</tr>
			<%
			}
			%>
		</table>
		
		<p>
			<a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth() - 1%>" class="next-month"></a>
			<a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth() + 1%>" class="past-month"></a>
		</p>
		
	</div>



</body>
</html>

<%!/**----------------------------------------------------------------------*
			 *■■■replaceEscapeCharクラス■■■
			 *概要：文字列データのエスケープを行う
			 *----------------------------------------------------------------------**/
	String replaceEscapeChar(String inputText) {

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