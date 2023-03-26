package model.timeLine;

import javax.servlet.annotation.WebServlet;

import model.TimeLineDao;
import model.TimeLineDto;

/**----------------------------------------------------------------------*
 *■■■SaveTimeLineBLクラス■■■
 *概要：ビジネスロジック（TimeLineデータの登録）
 *----------------------------------------------------------------------**/
@WebServlet("/SaveTimeLineBL")
public class SaveTimeLineBL {

	/**----------------------------------------------------------------------*
	 *■executeInsertSurveyメソッド
	 *概要　：対象のTimeLineデータを登録する
	 *引数　：対象のTimeLineデータ（SurveyDto型）
	 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
	 *----------------------------------------------------------------------**/
	public boolean executeInsertSurvey(TimeLineDto dto) {

		boolean succesInsert = false ;  //DB操作成功フラグ（true:成功/false:失敗）

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆対象のユーザーデータを登録するよう依頼
		TimeLineDao dao = new TimeLineDao();
		succesInsert = dao.doInsert(dto);

		return succesInsert;
	}

}
