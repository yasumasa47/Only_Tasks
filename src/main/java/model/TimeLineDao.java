package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**----------------------------------------------------------------------*
 *■■■TimeLineDaoクラス■■■
 *概要：DAO（「survey」テーブル）
 *----------------------------------------------------------------------**/
public class TimeLineDao {
	//-------------------------------------------
	//データベースへの接続情報
	//-------------------------------------------
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	String JDBC_URL    = "jdbc:mysql://localhost/only_tasks_db";
	String USER_ID     = "root";
	String USER_PASS   = "yasu0407";


	//----------------------------------------------------------------
	//メソッド
	//----------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------
	/**----------------------------------------------------------------------*
	 *■doInsertメソッド
	 *概要　：「Time_Line」テーブルに対象のデータを挿入する
	 *引数　：対象のアンケートデータ（TimeLineDto型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/
	public boolean doInsert(TimeLineDto dto) {

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

		//実行結果（真：成功、偽：例外発生）格納用変数
		boolean isSuccess = true ;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（INSERT）
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT INTO TIME_LINE (  ");
			buf.append("   NAME,                  ");
			buf.append("   TASK,                  ");
			buf.append("   STATE,                 ");
			buf.append("   TIME                   ");
			buf.append(" ) VALUES (               ");
			buf.append("   ?,                     ");
			buf.append("   ?,                     ");
			buf.append("   ?,                     ");
			buf.append("   ?                      ");
			buf.append(" )                        ");

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString(    1, dto.getName()              ); //第1パラメータ：更新データ（名前）
			ps.setString(       2, dto.getTask()           ); //第2パラメータ：更新データ（タスク）
			ps.setString(       3, dto.getState()          ); //第3パラメータ：更新データ（状態）
			ps.setTimestamp( 4, dto.getTime()              ); //第6パラメータ：更新データ（更新時刻）

			//SQL文の実行
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false ;

		} finally {
			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if(isSuccess){
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}else{
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		//実行結果を返す
		return isSuccess;
	}


//----------------------------------------------------------------------------------------------------------------------

	/**----------------------------------------------------------------------*
	 *■doSelectメソッド
	 *概要　：「Time_Line」テーブルのデータを全件抽出する
	 *引数　：なし
	 *戻り値：抽出結果（DTOリスト）
	 *----------------------------------------------------------------------**/
	public List<TimeLineDto> doSelect() {

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

		//抽出結果格納用DTOリスト
		List<TimeLineDto> dtoList = new ArrayList<TimeLineDto>();

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT          ");
			buf.append("   NAME,         ");
			buf.append("   TASK,         ");
			buf.append("   STATE,        ");
			buf.append("   TIME          ");
			buf.append(" FROM            ");
			buf.append("   TIME_LINE     ");
			buf.append(" ORDER BY        ");
			buf.append("   TIME          ");
			buf.append("   DESC          ");

			ps = con.prepareStatement(buf.toString());
			rs = ps.executeQuery();

			//ResultSetオブジェクトからDTOリストに格納
			while (rs.next()) {
				TimeLineDto dto = new TimeLineDto();
				dto.setName(  rs.getString(    "NAME"     ) );
				dto.setTask(  rs.getString(    "TASK"     ) );
				dto.setState( rs.getString(    "STATE"    ) );
				dto.setTime(  rs.getTimestamp( "TIME"     ) );
				dtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//ResultSetオブジェクトの接続解除
			if (rs != null) {    //接続が確認できている場合のみ実施
				try {
					rs.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		//抽出結果を返す
		return dtoList;
	}

}
