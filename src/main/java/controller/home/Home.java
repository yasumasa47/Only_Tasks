package controller.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TimeLineDto;
import model.UserInfoDto;
import model.Home.HomeBL;

/**
 * ----------------------------------------------------------------------*
 * ■■■Homeクラス■■■ 概要：サーブレット 詳細：「Time_Line」テーブルのデータを全件抽出してHome画面を出力する。
 * ----------------------------------------------------------------------
 **/
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Home() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");

		// セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		// ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			// ログイン済：回答一覧画面を出力

			// 「survey」テーブルのデータを全件抽出
			List<TimeLineDto> list = new ArrayList<TimeLineDto>();
			HomeBL logic = new HomeBL();
			list = logic.executeTimeLine();

			// アンケートリストをリクエストスコープに保存
			request.setAttribute("HOME_LIST", list);

			// Viewにフォワード（フォワード先：home.jsp）
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/home.jsp");
			dispatch.forward(request, response);

		} else {
			// 未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
