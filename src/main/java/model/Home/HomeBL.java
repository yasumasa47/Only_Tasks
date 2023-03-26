package model.Home;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import model.TimeLineDao;
import model.TimeLineDto;

/**----------------------------------------------------------------------*
 *■■■HomeBLクラス■■■
 *概要：ビジネスロジック
 *----------------------------------------------------------------------**/
@WebServlet("/HomeBL")
public class HomeBL {

	/**----------------------------------------------------------------------*
	 *■executeTimeLineメソッド
	 *概要　：TimeLineデータを全件抽出する
	 *引数　：なし
	 *戻り値：抽出結果（DTOリスト）
	 *----------------------------------------------------------------------**/
	public List<TimeLineDto> executeTimeLine() {

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆対象のユーザーデータを登録するよう依頼
		TimeLineDao dao = new TimeLineDao();
		List<TimeLineDto> dtoList= dao.doSelect();

		return dtoList;
	}

}
