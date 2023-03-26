package model.newAccount;

import javax.servlet.annotation.WebServlet;

import model.UserInfoDao;
import model.UserInfoDto;

/**----------------------------------------------------------------------*
 *■■■NewAccountBLクラス■■■
 *概要：ビジネスロジック（ユーザーデータの登録）
 *----------------------------------------------------------------------**/
@WebServlet("/NewAccountBL")
public class NewAccountBL {
	
	public boolean NewAccountSave(UserInfoDto dto) {

		boolean succesInsert = false ;  //DB操作成功フラグ（true:成功/false:失敗）

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆対象のユーザーデータを登録するよう依頼
		UserInfoDao dao = new UserInfoDao();
		succesInsert = dao.createAccount(dto);

		return succesInsert;
	}

}
