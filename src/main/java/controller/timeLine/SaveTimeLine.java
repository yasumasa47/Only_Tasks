package controller.timeLine;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TimeLineDto;
import model.UserInfoDto;
import model.timeLine.SaveTimeLineBL;

/**
 * ----------------------------------------------------------------------*
 * ■■■SaveTimeLineクラス■■■ 
 * 概要：サーブレット 
 * 詳細：リクエストを「TimeLine」テーブルに登録し、画面遷移する。
 * ＜遷移先＞登録成功：Home／登録失敗：エラー画面（error.html）
 * ----------------------------------------------------------------------
 **/
@WebServlet("/SaveTimeLine")
public class SaveTimeLine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveTimeLine() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8"); // 文字コードをUTF-8で設定
		// リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8"); // 文字コードをUTF-8で設定

		// セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		// ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			// ログイン済：登録処理＆結果画面への遷移を実施

			boolean succesFlg = true; // 成功フラグ（true:成功/false:失敗）

			// パラメータのバリデーションチェック
			if (!(validatePrmName(request.getParameter("NAME")) 
					&& validatePrmTask(request.getParameter("TASK"))
					&& validatePrmstate(request.getParameter("STATE")))) {

				// バリデーションNGの場合
				succesFlg = false;

			} else {

				// バリデーションOKの場合

				// リクエストパラメータを取得
				String name = request.getParameter("NAME"); // リクエストパラメータ（NAME）
				String task = request.getParameter("TASK");
				String state = request.getParameter("STATE");

				// アンケートデータ（SurveyDto型）の作成
				TimeLineDto dto = new TimeLineDto();
				dto.setName(name);
				dto.setTask(task);
				dto.setState(state);
				dto.setTime(new Timestamp(System.currentTimeMillis())); // 現在時刻を更新時刻として設定

				// TimeLineデータをDBに登録
				SaveTimeLineBL logic = new SaveTimeLineBL();
				succesFlg = logic.executeInsertSurvey(dto); // 成功フラグ（true:成功/false:失敗）

			}

			// 成功/失敗に応じて表示させる画面を振り分ける
			if (succesFlg) {

				// 成功した場合、Homeを表示する
				response.sendRedirect("Home");

			} else {

				// 失敗した場合、エラー画面（error.html）を表示する
				response.sendRedirect("htmls/timeLine_error.html");

			}

		} else {
			// 未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}

	/**
	 * ----------------------------------------------------------------------*
	 * ■■■validatePrmNameクラス■■■ 概要：バリデーションチェック 詳細：入力値（名前）の検証を行う
	 * ----------------------------------------------------------------------
	 **/
	private boolean validatePrmName(String pr) {

		boolean validateResult = true;

		// 入力値がnullまたは空白の場合はエラーとする
		if (pr == null || pr.equals("")) {
			validateResult = false;
		}

		return validateResult;
	}

	/**
	 * ----------------------------------------------------------------------*
	 * ■■■validatePrmTaskクラス■■■ 概要：バリデーションチェック 詳細：入力値（タスク）の検証を行う
	 * ----------------------------------------------------------------------
	 **/
	private boolean validatePrmTask(String pr) {

		boolean validateResult = true;

		// 入力値がnullまたは空白の場合はエラーとする
		if (pr == null || pr.equals("")) {
			validateResult = false;
		}

		return validateResult;
	}

	/**
	 * ----------------------------------------------------------------------*
	 * ■■■validatePrmStateクラス■■■ 概要：バリデーションチェック 詳細：入力値（状態）の検証を行う
	 * ----------------------------------------------------------------------
	 **/
	private boolean validatePrmstate(String pr) {

		boolean validateResult = true;

		// 入力値がnullまたは空白の場合はエラーとする
		if (pr == null || pr.equals("")) {
			validateResult = false;
		}

		return validateResult;
	}

}
