package model;

/**----------------------------------------------------------------------*
 *■■■UserInfoDtoクラス■■■
 *概要：DTO（「user_info」テーブル）
 *----------------------------------------------------------------------**/
public class UserInfoDto {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private String userId;         //ユーザーID
	private String userName;       //ユーザー名
	private String passWord;       //ユーザーパスワード
	private String task;           //ユーザータスク


	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------
	
	//getter/setter（対象フィールド：userId）
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	//getter/setter（対象フィールド：userName）
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	//getter/setter（対象フィールド：passWord）
	public String getPassWord() { return passWord; }
	public void setPassWord(String passWord) { this.passWord = passWord; }
	
	//getter/setter（対象フィールド：task）
	public String getTask() { return task; }
	public void setTask(String task) { this.task = task; }

}
