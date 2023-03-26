package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**----------------------------------------------------------------------*
 *■■■UserInfoDaoクラス■■■
 *概要：DAO（「user_info」テーブル）
 *----------------------------------------------------------------------**/
public class UserInfoDao {
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

	/**----------------------------------------------------------------------*
	 *■doSelectメソッド
	 *概要　：引数のユーザー情報に紐づくユーザーデータを「user_info」テーブルから抽出する
	 *引数①：ユーザーID（ユーザー入力）
	 *引数②：ユーザーパスワード（ユーザー入力）
	 *戻り値：「user_info」テーブルから抽出したユーザーデータ（UserInfoDto型）
	 *----------------------------------------------------------------------**/
	public UserInfoDto doSelect(String inputUserId, String inputPassWord) {

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

		//抽出データ（UserInfoDto型）格納用変数
		UserInfoDto dto = new UserInfoDto();

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
			buf.append(" SELECT              ");
			buf.append("   USER_ID  ,        "); 
			buf.append("   USER_NAME,        ");
			buf.append("   PASSWORD,         ");
			buf.append("   TASK              ");
			buf.append(" FROM                ");
			buf.append("   USER_INFO         ");
			buf.append(" WHERE               ");
			buf.append("   USER_ID  = ? AND  ");  //第1パラメータ
			buf.append("   PASSWORD = ?      ");  //第2パラメータ

			//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString( 1, inputUserId   );  //第1パラメータ：ユーザーID（ユーザー入力）
			ps.setString( 2, inputPassWord );  //第2パラメータ：ユーザーパスワード（ユーザー入力）

			//SQL文の送信＆戻り値としてResultSet（SQL抽出結果）を取得
			rs = ps.executeQuery();

			//--------------------------------------------------------------------------------
			//ResultSetオブジェクトからユーザーデータを抽出
			//--------------------------------------------------------------------------------
			if (rs.next()) {
				//ResultSetから1行分のレコード情報をDTOへ登録
				dto.setUserId(   rs.getString("USER_ID")   );    //ユーザーID
				dto.setUserName( rs.getString("USER_NAME") );    //ユーザー名
				dto.setPassWord( rs.getString("PASSWORD")  );    //ユーザーパスワード
				dto.setTask( rs.getString("TASK")          );    //ユーザータスク
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

		//抽出したユーザーデータを戻す
		return dto;
	}
	
	//----------------------------------------------------------------------------------------------------------------------
		/**----------------------------------------------------------------------*
		 *■createAccountメソッド
		 *概要　：「user_info」テーブルに対象の新規ユーザーデータを挿入する
		 *引数　：対象のアンケートデータ（UserInfoDto型）
		 *戻り値：実行結果（真：成功、偽：例外発生）
		 *----------------------------------------------------------------------**/
		public boolean createAccount(UserInfoDto dto) {

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
				buf.append("INSERT INTO USER_INFO (  ");
				buf.append("  USER_ID,               ");
				buf.append("  USER_NAME,             ");
				buf.append("  PASSWORD,              ");
				buf.append("  TASK                   ");
				buf.append(") VALUES (               ");
				buf.append("  ?,                     ");
				buf.append("  ?,                     ");
				buf.append("  ?,                     ");   
				buf.append("  ?                      ");
				buf.append(")                        ");

				//PreparedStatementオブジェクトを生成＆発行するSQLをセット
				ps = con.prepareStatement(buf.toString());

				//パラメータをセット
				ps.setString(    1, dto.getUserId()            ); //第1パラメータ：更新データ（ID）
				ps.setString(    2, dto.getUserName()          ); //第2パラメータ：更新データ（名前）
				ps.setString(    3, dto.getPassWord()          ); //第3パラメータ：更新データ（パスワード）
				ps.setString(    4, dto.getTask()              ); //第4パラメータ：更新データ（タスク）

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
				 *■SettingAccountメソッド
				 *概要　：「user_info」テーブルのユーザーデータを更新する
				 *引数　：対象のデータ（UserInfoDto型）
				 *戻り値：実行結果（真：成功、偽：例外発生）
				 *----------------------------------------------------------------------**/
				public boolean SettingAccount(UserInfoDto dto) {

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
						buf.append(" UPDATE user_info      ");
						buf.append("   SET                 ");
						buf.append("   user_name = ? ,     ");
						buf.append("   password = ? ,      ");
						buf.append("   task = ?            ");
						buf.append("   WHERE               ");
						buf.append("   user_id =  ?        ");

						//PreparedStatementオブジェクトを生成＆発行するSQLをセット
						ps = con.prepareStatement(buf.toString());

						//パラメータをセット
						ps.setString(    1, dto.getUserName()          ); //第1パラメータ：更新データ（名前）
						ps.setString(    2, dto.getPassWord()          ); //第2パラメータ：更新データ（パスワード）
						ps.setString(    3, dto.getTask()              ); //第3パラメータ：更新データ（タスク）
						ps.setString(    4, dto.getUserId()            ); //第4パラメータ：更新データ（ID）

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

}
