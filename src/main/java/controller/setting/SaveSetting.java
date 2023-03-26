package controller.setting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserInfoDto;
import model.setting.SettingBL;

@WebServlet("/SaveSetting")
public class SaveSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveSetting() {
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

		boolean succesFlg = true; // 成功フラグ（true:成功/false:失敗）

			// パラメータのバリデーションチェック
			if (!(validatePrmId(request.getParameter("USER_ID")) 
					&& validatePrmName(request.getParameter("USER_NAME"))
					&& validatePrmPass(request.getParameter("PASSWORD"))
					&& validatePrmTask(request.getParameter("TASK")))) {

				// バリデーションNGの場合
				succesFlg = false;

			} else {

				// バリデーションOKの場合

				// リクエストパラメータを取得
				String id = request.getParameter("USER_ID");
				String name = request.getParameter("USER_NAME");
				String password = request.getParameter("PASSWORD");
				String task = request.getParameter("TASK");
				

				// アンケートデータ（SurveyDto型）の作成
				UserInfoDto dto = new UserInfoDto();
				dto.setUserId(id);
				dto.setUserName(name);
				dto.setPassWord(password);
				dto.setTask(task);

				// アンケートデータをDBに登録
				SettingBL logic = new SettingBL();
				succesFlg = logic.SettingSave(dto);

			}

			// 成功/失敗に応じて表示させる画面を振り分ける
			if (succesFlg) {

				// 成功した場合、Homeを表示する
				response.sendRedirect("ExecuteLogout");

			} else {

				// 失敗した場合、エラー画面（timeLine_error.html）を表示する
				response.sendRedirect("htmls/timeLine_error.html");

			}
		}

	/**
	 * ----------------------------------------------------------------------*
	 * ■■■validatePrmNameクラス■■■ 概要：バリデーションチェック 詳細：入力値（名前）の検証を行う
	 * ----------------------------------------------------------------------
	 **/
	private boolean validatePrmId(String pr) {

		boolean validateResult = true;

		// 入力値がnullまたは空白の場合はエラーとする
		if (pr == null || pr.equals("")) {
			validateResult = false;
		}

		return validateResult;
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
	 * ■■■validatePrmNameクラス■■■ 概要：バリデーションチェック 詳細：入力値（名前）の検証を行う
	 * ----------------------------------------------------------------------
	 **/
	private boolean validatePrmPass(String pr) {

		boolean validateResult = true;

		// 入力値がnullまたは空白の場合はエラーとする
		if (pr == null || pr.equals("")) {
			validateResult = false;
		}

		return validateResult;
	}

	/**
	 * ----------------------------------------------------------------------*
	 * ■■■validatePrmNameクラス■■■ 概要：バリデーションチェック 詳細：入力値（名前）の検証を行う
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

}
