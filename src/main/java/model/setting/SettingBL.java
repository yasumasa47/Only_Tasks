package model.setting;

import javax.servlet.annotation.WebServlet;

import model.UserInfoDao;
import model.UserInfoDto;

@WebServlet("/SettingBL")
public class SettingBL {
	public boolean SettingSave(UserInfoDto dto) {

		boolean succesInsert = false; // DB操作成功フラグ（true:成功/false:失敗）

		// -------------------------------------------
		// データベースへの接続を実施
		// -------------------------------------------

		// DAOクラスをインスタンス化＆対象のユーザーデータを登録するよう依頼
		UserInfoDao dao = new UserInfoDao();
		succesInsert = dao.SettingAccount(dto);

		return succesInsert;

	}

}
